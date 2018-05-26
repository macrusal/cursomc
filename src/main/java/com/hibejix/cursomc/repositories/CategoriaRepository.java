/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Categoria;

/**
 * @author msalvador
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
