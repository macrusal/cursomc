/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Endereco;

/**
 * @author marcelo
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
