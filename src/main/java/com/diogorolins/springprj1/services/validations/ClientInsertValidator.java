package com.diogorolins.springprj1.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.diogorolins.springprj1.domain.dto.ClientNewDTO;
import com.diogorolins.springprj1.domain.enums.ClientType;
import com.diogorolins.springprj1.resources.handler.FieldMessage;
import com.diogorolins.springprj1.services.validations.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	@Override
	public void initialize(ClientInsert ann) {
	} 

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getClientType().equals(ClientType.PESSOAFISICA.getId()) && 
				!BR.isValidCPF(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("CPF", "Invalid CPF"));
		}
		
		if(objDto.getClientType().equals(ClientType.PESSOAJURIDICA.getId()) && 
				!BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("CNPJ", "Invalid CNPJ"));
		}		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}