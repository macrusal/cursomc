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

import com.hibejix.cursomc.domain.Pedido;
import com.hibejix.cursomc.services.PedidoService;

/**
 * @author msalvador
 *
 */
@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService service;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable final Integer id) {
	
		Pedido pedido = service.buscar(id);
		return ResponseEntity.ok().body(pedido);
	}

}