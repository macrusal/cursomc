/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.Pedido;

/**
 * @author msalvador
 *
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
