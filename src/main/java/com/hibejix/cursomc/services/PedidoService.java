/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.ItemPedido;
import com.hibejix.cursomc.domain.PagamentoComBoleto;
import com.hibejix.cursomc.domain.Pedido;
import com.hibejix.cursomc.domain.enums.EstadoPagamento;
import com.hibejix.cursomc.repositories.ItemPedidoRepository;
import com.hibejix.cursomc.repositories.PagamentoRepository;
import com.hibejix.cursomc.repositories.PedidoRepository;
import com.hibejix.cursomc.repositories.ProdutoRepository;
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
	
	public Pedido find(final Integer id) {
		
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new PedidoNotFoundException(
				"Pedido não encontrada! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public @Valid Pedido insert(@Valid Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
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
			item.setPreco(produtoRepository.findById(item.getProduto().getId()).get().getPreco());
			item.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}
}
