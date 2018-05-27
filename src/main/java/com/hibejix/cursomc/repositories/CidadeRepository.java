/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Cidade;

/**
 * @author marcelo
 *
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
