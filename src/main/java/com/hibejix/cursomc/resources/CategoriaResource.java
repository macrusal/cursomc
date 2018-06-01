/**
 * 
 */
package com.hibejix.cursomc.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hibejix.cursomc.domain.Categoria;
import com.hibejix.cursomc.dto.CategoriaDTO;
import com.hibejix.cursomc.services.CategoriaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author msalvador
 *
 */
@Api(value="API REST - Categorias")
@RestController
@RequestMapping(value="/hibejix-comercio/categorias")
public class CategoriaResource {
	
	@Autowired
	CategoriaService service;
	
	@ApiOperation(value="Retorna a categoria baseada em um Id")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable final Integer id) {
	
		Categoria categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@ApiOperation(value="Cadastra uma nova categoria")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value="Atualiza a categoria baseada em um Id")
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable final Integer id) {
		Categoria categoria = service.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = service.update(categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Deleta a categoria baseada em um Id")
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value="Retorna uma lista com todas as categorias")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
	
		List<Categoria> categorias = service.findAll();
		List<CategoriaDTO> categoriasDTO = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList()); 
		return ResponseEntity.ok().body(categoriasDTO);
	}

	@ApiOperation(value="Retorna uma lista paginada com todas as categorias")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
	
		Page<Categoria> categorias = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriasDTO = categorias.map(categoria -> new CategoriaDTO(categoria)); 
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
	
}
