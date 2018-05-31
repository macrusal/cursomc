/**
 * 
 */
package com.hibejix.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.hibejix.cursomc.domain.Cliente;
import com.hibejix.cursomc.dto.ClienteDTO;
import com.hibejix.cursomc.repositories.ClienteRepository;
import com.hibejix.cursomc.resources.exceptions.FieldMessage;

/**
 * @author msalvador
 *
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());
		if(cliente != null && !cliente.getId().equals(uriId)) {
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
