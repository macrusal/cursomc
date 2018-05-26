/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Produto;

/**
 * @author msalvador
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
