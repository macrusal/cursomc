/**
 * 
 */
package com.hibejix.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.domain.enums.TipoCliente;
import com.hibejix.cursomc.dto.ClienteNewDTO;
import com.hibejix.cursomc.repositories.ClienteRepository;
import com.hibejix.cursomc.resources.exceptions.FieldMessage;
import com.hibejix.cursomc.services.validation.utils.BR;

/**
 * @author msalvador
 *
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(clienteNewDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())
				&& !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido!"));
		}
		
		if(clienteNewDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
				&& !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalido!"));
		}
		
		Cliente cliente = clienteRepository.findByEmail(clienteNewDTO.getEmail());
		if(cliente != null) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}
		
		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName())
				.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	

}
