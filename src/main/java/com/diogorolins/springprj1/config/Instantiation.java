package com.diogorolins.springprj1.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diogorolins.springprj1.domain.Address;
import com.diogorolins.springprj1.domain.Category;
import com.diogorolins.springprj1.domain.City;
import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.Order;
import com.diogorolins.springprj1.domain.OrderItem;
import com.diogorolins.springprj1.domain.Payment;
import com.diogorolins.springprj1.domain.PaymentBoleto;
import com.diogorolins.springprj1.domain.PaymentCard;
import com.diogorolins.springprj1.domain.Product;
import com.diogorolins.springprj1.domain.State;
import com.diogorolins.springprj1.domain.enums.ClientType;
import com.diogorolins.springprj1.domain.enums.PaymentStatus;
import com.diogorolins.springprj1.domain.enums.PaymentType;
import com.diogorolins.springprj1.repositories.AddressRepository;
import com.diogorolins.springprj1.repositories.CategoryRepository;
import com.diogorolins.springprj1.repositories.CityRepository;
import com.diogorolins.springprj1.repositories.ClientRepository;
import com.diogorolins.springprj1.repositories.OrderItemRepository;
import com.diogorolins.springprj1.repositories.OrderRepository;
import com.diogorolins.springprj1.repositories.PaymentRepository;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
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
		
		Client cli1 = new Client(null, "Maria Silva", "maria@mail,com", "19293949585", ClientType.PESSOAFISICA);
		cli1.getPhones().addAll(Arrays.asList("891898118","27272772"));
		
		Address ad1 = new Address(null, "Rua Flores", "300", "Apt 303", "Jardim", "22938293", cli1, city1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 803", "Centro", "32323939", cli1, city2);
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		
		clientReposotory.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);
		
		Payment pay1 = new PaymentCard(null, PaymentStatus.PAID,PaymentType.CARD, ord1, 6);
		ord1.setPayment(pay1);
		
		Payment pay2 = new PaymentBoleto(null, PaymentStatus.WAITING_PAYMENT, PaymentType.BOLETO, ord2, sdf.parse("20/10/2017 00:00:00"), null);
		ord2.setPayment(pay2);
		
		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));
		
		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		OrderItem oi1 = new OrderItem(ord1, p1, 0.00, 1, p1.getPrice());
		OrderItem oi2 = new OrderItem(ord1, p3, 0.00, 2, p3.getPrice());
		OrderItem oi3 = new OrderItem(ord2, p2, 100.00, 1, p2.getPrice());
		
		ord1.getItems().addAll(Arrays.asList(oi1,oi2));
		ord2.getItems().addAll(Arrays.asList(oi3));
		
		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
		
	}

}
