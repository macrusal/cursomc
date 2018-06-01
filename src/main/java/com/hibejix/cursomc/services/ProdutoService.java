/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Categoria;
import com.hibejix.cursomc.domain.Produto;
import com.hibejix.cursomc.repositories.CategoriaRepository;
import com.hibejix.cursomc.repositories.ProdutoRepository;
import com.hibejix.cursomc.services.exceptions.ProdutoNotFoundException;

/**
 * @author msalvador
 *
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(final Integer id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ProdutoNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.search(nome, categorias, pageRequest);
	}
}
