/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.dto.ClienteDTO;
import com.hibejix.cursomc.repositories.ClienteRepository;
import com.hibejix.cursomc.services.exceptions.ClienteNotFoundException;
import com.hibejix.cursomc.services.exceptions.DataIntegrityException;

/**
 * @author msalvador
 *
 */
@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;
	
	public Cliente find(final Integer id) {
		
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ClienteNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	/**
	 * @param cliente
	 * @return
	 */
	public Cliente update(Cliente cliente) {
		Cliente clienteNovo = find(cliente.getId());
		updateData(clienteNovo, cliente);
		return repository.save(clienteNovo);
	}

	/**
	 * @param id
	 * @return
	 */
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Cliente que possui associações!");
		}
	}

	/**
	 * @param 
	 * @return
	 */
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	/**
	 * @param 
	 * @return
	 */
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	/**
	 * @param 
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(),dto.getEmail(), null, null);
	}
	
	private void updateData(Cliente clienteNovo, Cliente cliente) {
		clienteNovo.setNome(cliente.getNome());
		clienteNovo.setEmail(cliente.getEmail());
	}
}
