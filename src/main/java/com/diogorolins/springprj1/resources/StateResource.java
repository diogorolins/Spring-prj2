package com.diogorolins.springprj1.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diogorolins.springprj1.domain.City;
import com.diogorolins.springprj1.domain.State;
import com.diogorolins.springprj1.domain.dto.CityDTO;
import com.diogorolins.springprj1.domain.dto.StateDTO;
import com.diogorolins.springprj1.services.CityService;
import com.diogorolins.springprj1.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll(){
		List<State> list = service.findAll();
		List<StateDTO> listDto = list.stream().map( x -> new StateDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/cities")
	public ResponseEntity<List<CityDTO>> findCity(@PathVariable Integer id){
		List<City> list = cityService.findAllByState(id);
		List<CityDTO> listDto = list.stream().map( x -> new CityDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
}
