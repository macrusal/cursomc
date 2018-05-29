/**
 * 
 */
package com.hibejix.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.dto.ClienteDTO;
import com.hibejix.cursomc.services.ClienteService;

/**
 * @author msalvador
 *
 */
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService service;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable final Integer id) {
	
		Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable final Integer id) {
		Cliente cliente = service.fromDTO(clienteDTO);
		cliente.setId(id);
		cliente = service.update(cliente);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
	
		List<Cliente> clientes = service.findAll();
		List<ClienteDTO> clientesDTO = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(clientesDTO);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
	
		Page<Cliente> clientes = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clientesDTO = clientes.map(cliente -> new ClienteDTO(cliente)); 
		return ResponseEntity.ok().body(clientesDTO);
	}

}
