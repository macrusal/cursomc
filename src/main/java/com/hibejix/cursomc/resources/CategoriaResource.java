/**
 * 
 */
package com.hibejix.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hibejix.cursomc.domain.Categoria;
import com.hibejix.cursomc.services.CategoriaService;

/**
 * @author msalvador
 *
 */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable final Integer id) {
	
		Categoria categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable final Integer id) {
		categoria.setId(id);
		categoria = service.update(categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
