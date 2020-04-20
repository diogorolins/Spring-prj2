package com.diogorolins.springprj1.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.dto.ClientDTO;
import com.diogorolins.springprj1.repositories.ClientRepository;
import com.diogorolins.springprj1.resources.handler.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClientUpdate ann) {
	} 

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client cli = repository.findByEmail(objDto.getEmail());
		if(cli != null && !cli.getId().equals(id)) {
			list.add(new FieldMessage("Email", "Email duplicado."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}