/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Estado;

/**
 * @author marcelo
 *
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
