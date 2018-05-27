/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Pagamento;

/**
 * @author msalvador
 *
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
