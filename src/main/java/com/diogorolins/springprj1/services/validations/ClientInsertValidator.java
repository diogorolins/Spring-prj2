package com.diogorolins.springprj1.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.dto.ClientNewDTO;
import com.diogorolins.springprj1.domain.enums.ClientType;
import com.diogorolins.springprj1.repositories.ClientRepository;
import com.diogorolins.springprj1.resources.handler.FieldMessage;
import com.diogorolins.springprj1.services.validations.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository repository;
	
	@Override
	public void initialize(ClientInsert ann) {
	} 

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getClientType().equals(ClientType.PESSOAFISICA.getId()) && 
				!BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
		}
		
		if(objDto.getClientType().equals(ClientType.PESSOAJURIDICA.getId()) && 
				!BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
		}	
		
		Client cli = repository.findByEmail(objDto.getEmail());
		if(cli != null) {
			list.add(new FieldMessage("Email", "Email já cadastrado."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}