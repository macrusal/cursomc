/**
 * 
 */
package com.hibejix.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hibejix.cursomc.domain.PagamentoComBoleto;

/**
 * @author msalvador
 *
 */
@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagtoBoleto, Date instanteDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instanteDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagtoBoleto.setDataVencimento(calendar.getTime());
	}

}
