/**
 * 
 */
package com.hibejix.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibejix.cursomc.domain.ItemPedido;

/**
 * @author msalvador
 *
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
