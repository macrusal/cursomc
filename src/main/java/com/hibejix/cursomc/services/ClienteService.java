/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Cidade;
import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.domain.Endereco;
import com.hibejix.cursomc.domain.enums.TipoCliente;
import com.hibejix.cursomc.dto.ClienteDTO;
import com.hibejix.cursomc.dto.ClienteNewDTO;
import com.hibejix.cursomc.repositories.CidadeRepository;
import com.hibejix.cursomc.repositories.ClienteRepository;
import com.hibejix.cursomc.repositories.EnderecoRepository;
import com.hibejix.cursomc.services.exceptions.ClienteNotFoundException;
import com.hibejix.cursomc.services.exceptions.DataIntegrityException;

/**
 * @author msalvador
 *
 */
@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Cliente find(final Integer id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ClienteNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	public Cliente update(Cliente cliente) {
		Cliente clienteNovo = find(cliente.getId());
		updateData(clienteNovo, cliente);
		return clienteRepository.save(clienteNovo);
	}

	/**
	 * @param id
	 * @return
	 */
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente que possui associações!");
		}
	}

	/**
	 * @param 
	 * @return
	 */
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	/**
	 * @param 
	 * @return
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	/**
	 * @param 
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(),dto.getEmail(), null, null, null);
	}
	
	/**
	 * @param 
	 * @return
	 */
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(),dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()), bCryptPasswordEncoder.encode(dto.getSenha()));
		Optional<Cidade> cidade = cidadeRepository.findById(dto.getCidadeId());
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade.orElse(null));
		cliente.getEnderecos().addAll(Arrays.asList(endereco));
		cliente.getTelefones().addAll(Arrays.asList(dto.getTelefone1()));
		if(dto.getTelefone2() != null) {
			cliente.getTelefones().addAll(Arrays.asList(dto.getTelefone2()));
		}
		if(dto.getTelefone3() != null) {
			cliente.getTelefones().addAll(Arrays.asList(dto.getTelefone3()));
		}
		
		return cliente; 
	}
	
	private void updateData(Cliente clienteNovo, Cliente cliente) {
		clienteNovo.setNome(cliente.getNome());
		clienteNovo.setEmail(cliente.getEmail());
	}
}
