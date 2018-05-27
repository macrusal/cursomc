/**
 * 
 */
package com.hibejix.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hibejix.cursomc.domain.Cliente;
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
	public ResponseEntity<?> find(@PathVariable final Integer id) {
	
		Cliente cliente = service.buscar(id);
		return ResponseEntity.ok().body(cliente);
	}

}
