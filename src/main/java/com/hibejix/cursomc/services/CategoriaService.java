/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Categoria;
import com.hibejix.cursomc.repositories.CategoriaRepository;
import com.hibejix.cursomc.services.exceptions.CategoriaNotFoundException;

/**
 * @author msalvador
 *
 */
@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;
	
	public Categoria buscar(final Integer id) {
		
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new CategoriaNotFoundException(
				"Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	/**
	 * @param categoria
	 * @return
	 */
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}
}
