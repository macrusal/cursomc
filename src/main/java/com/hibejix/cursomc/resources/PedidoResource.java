/**
 * 
 */
package com.hibejix.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hibejix.cursomc.domain.Pedido;
import com.hibejix.cursomc.services.PedidoService;

/**
 * @author msalvador
 *
 */
@RestController
@RequestMapping(value="/hibejix-comercio/pedidos")
public class PedidoResource {
	
	@Autowired
	PedidoService service;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable final Integer id) {
	
		Pedido pedido = service.find(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
//		Pedido pedido = service.fromDTO(pedidoDTO);
		pedido = service.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {

		Page<Pedido> pedidos = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pedidos);

	}
}
