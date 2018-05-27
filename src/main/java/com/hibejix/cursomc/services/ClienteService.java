/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.repositories.ClienteRepository;
import com.hibejix.cursomc.services.exceptions.ClienteNotFoundException;

/**
 * @author msalvador
 *
 */
@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;
	
	public Cliente buscar(final Integer id) {
		
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ClienteNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
