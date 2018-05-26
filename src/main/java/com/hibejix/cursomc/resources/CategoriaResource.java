/**
 * 
 */
package com.hibejix.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable final Integer id) {
	
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}

}
