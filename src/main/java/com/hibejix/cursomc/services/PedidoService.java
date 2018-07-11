/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.hibejix.cursomc.domain.*;
import com.hibejix.cursomc.repositories.*;
import com.hibejix.cursomc.services.exceptions.ClienteNotFoundException;
import com.hibejix.cursomc.services.exceptions.ProdutoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.enums.EstadoPagamento;
import com.hibejix.cursomc.services.exceptions.PedidoNotFoundException;

/**
 * @author msalvador
 *
 */
@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	BoletoService boletoService;

	@Autowired
	ClienteRepository clienteRepository;
	
	public Pedido find(final Integer id) {
		
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new PedidoNotFoundException(
				"Pedido não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public @Valid Pedido insert(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(obterClientePorId(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagtoBoleto = (PagamentoComBoleto)pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagtoBoleto, pedido.getInstante());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for(ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(obterProdutoPorId(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		System.out.println(pedido);
		return pedido;
	}

	private Produto obterProdutoPorId(final Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ProdutoNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	private Cliente obterClientePorId(final Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ClienteNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
