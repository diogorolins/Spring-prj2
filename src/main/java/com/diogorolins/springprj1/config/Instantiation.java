package com.diogorolins.springprj1.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diogorolins.springprj1.domain.Address;
import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.domain.City;
import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.Product;
import com.diogorolins.springprj1.domain.State;
import com.diogorolins.springprj1.domain.enums.ClientType;
import com.diogorolins.springprj1.repositories.AddressRepository;
import com.diogorolins.springprj1.repositories.CategoryRepository;
import com.diogorolins.springprj1.repositories.CityRepository;
import com.diogorolins.springprj1.repositories.ClientRepository;
import com.diogorolins.springprj1.repositories.ProductRepository;
import com.diogorolins.springprj1.repositories.StateRepository;

@Configuration
@Profile("dev")
public class Instantiation implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientReposotory;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Impressora", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", st1);
		City city2 = new City(null, "São Paulo", st2);
		City city3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(city1));
		st2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		System.out.println(city1);
		Client cli1 = new Client(null, "Maria Silva", "maria@mail,com", "19293949585", ClientType.PESSOAFISICA);
		cli1.getPhones().addAll(Arrays.asList("891898118","27272772"));
		Address ad1 = new Address(null, "Rua Flores", "300", "Apt 303", "Jardim", "22938293", cli1, city1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 803", "Centro", "32323939", cli1, city2);
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		
		clientReposotory.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		
	}

}
