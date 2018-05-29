/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.Categoria;
import com.hibejix.cursomc.repositories.CategoriaRepository;
import com.hibejix.cursomc.services.exceptions.CategoriaNotFoundException;
import com.hibejix.cursomc.services.exceptions.DataIntegrityException;

/**
 * @author msalvador
 *
 */
@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;
	
	/**
	 * @param id
	 * @return
	 */
	public Categoria find(final Integer id) {
		
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

	/**
	 * @param categoria
	 * @return
	 */
	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repository.save(categoria);
	}

	/**
	 * @param id
	 * @return
	 */
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Categoria que possui Produtos associados!");
		}
	}

	/**
	 * @param 
	 * @return
	 */
	public List<Categoria> findAll() {
		return repository.findAll();
	}
}
