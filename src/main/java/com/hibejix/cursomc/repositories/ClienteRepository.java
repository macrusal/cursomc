/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Cliente;

/**
 * @author marcelo
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
