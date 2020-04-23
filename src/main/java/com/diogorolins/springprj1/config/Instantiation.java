package com.diogorolins.springprj1.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
import com.diogorolins.springprj1.domain.enums.Roles;
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
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama");
		Category cat4 = new Category(null, "Eletrônicos");
		Category cat5 = new Category(null, "Jardim");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		
		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Impressora", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		Product p4 = new Product(null, "Mesa", 300.0);
		Product p5 = new Product(null, "Toalha", 50.0);
		Product p6 = new Product(null, "Colcha", 2000.0);
		Product p7 = new Product(null, "TV", 1200.0);
		Product p8 = new Product(null, "Roçadeira", 800.0);
		Product p9 = new Product(null, "Abajour", 100.0);
		Product p10 = new Product(null, "Pendente", 180.0);
		Product p11 = new Product(null, "Shampoo", 90.0);
		
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City city1 = new City(null, "Uberlândia", st1);
		City city2 = new City(null, "São Paulo", st2);
		City city3 = new City(null, "Campinas", st2);
		
		st1.getCities().addAll(Arrays.asList(city1));
		st2.getCities().addAll(Arrays.asList(city2, city3));
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		Client cli1 = new Client(null, "Maria Silva", "diogorolins@gmail,com", "19293949585", ClientType.PESSOAFISICA, pe.encode("123456"));
		cli1.getPhones().addAll(Arrays.asList("891898118","27272772"));
		
		Client cli2 = new Client(null, "Ana Costa", "diogorolins@gmail,com", "09475414703", ClientType.PESSOAFISICA, pe.encode("123456"));
		cli2.addRole(Roles.ADMIN);
		cli2.getPhones().addAll(Arrays.asList("2313131","32131313"));
		
		Address ad1 = new Address(null, "Rua Flores", "300", "Apt 303", "Jardim", "22938293", cli1, city1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 803", "Centro", "32323939", cli1, city2);
		Address ad3 = new Address(null, "Avenida Dois", "2000", null, "Centro", "323213123", cli2, city2);
		
		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));
		cli2.getAddresses().addAll(Arrays.asList(ad3));
		
		clientReposotory.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, ad1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, ad2);
		
		Payment pay1 = new PaymentCard(null, PaymentStatus.PAID, ord1, 6);
		ord1.setPayment(pay1);
		
		Payment pay2 = new PaymentBoleto(null, PaymentStatus.WAITING_PAYMENT, ord2, sdf.parse("20/10/2017 00:00:00"), null);
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
