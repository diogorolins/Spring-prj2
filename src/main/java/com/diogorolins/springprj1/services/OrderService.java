package com.diogorolins.springprj1.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.diogorolins.springprj1.domain.Client;
import com.diogorolins.springprj1.domain.Order;
import com.diogorolins.springprj1.domain.OrderItem;
import com.diogorolins.springprj1.domain.PaymentBoleto;
import com.diogorolins.springprj1.domain.enums.PaymentStatus;
import com.diogorolins.springprj1.exceptions.AuthorizationException;
import com.diogorolins.springprj1.exceptions.ObjectNotFoundException;
import com.diogorolins.springprj1.repositories.OrderItemRepository;
import com.diogorolins.springprj1.repositories.OrderRepository;
import com.diogorolins.springprj1.repositories.PaymentRepository;
import com.diogorolins.springprj1.security.UserSS;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Integer id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Resource not found: " + Order.class.getSimpleName() + " id " + id));
	}

	public Order insert(Order obj) {
		System.out.println("Começou a inserir");
		obj = configureOrder(obj);
		emailService.sendOrderConfirmationMail(obj);
		return obj;
	}

	private Order configureOrder(Order obj) {
		System.out.println("Entrou no metodo");
		obj.setId(null);
		obj.setInstant(new Date());
		System.out.println("Instanciou a data");
		obj.setClient(clientService.findById(obj.getClient().getId()));
		System.out.println("Inseriu cliente");
		obj.getPayment().setPaymentStatus(PaymentStatus.WAITING_PAYMENT);
		System.out.println("inseriu status pagamento");
		obj.getPayment().setOrder(obj);
		obj = repository.save(obj);
		System.out.println("salvou pedido");
		if(obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto pay = (PaymentBoleto) obj.getPayment();
			paymentService.fillPaymentBoleto(pay, obj.getInstant());
		}
		System.out.println("inseriu tipo pagamento");
		paymentRepository.save(obj.getPayment());
		for(OrderItem i : obj.getItems()) {
			i.setDiscount(0.0);
			i.setProduct(productService.findById(i.getProduct().getId()));
			i.setPrice(i.getProduct().getPrice());
			i.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		System.out.println("Salvou tudo");
		return obj;
	}
	
	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction ){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Access denied");
		}		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.findById(user.getId());
		return repository.findByClient(client, pageRequest);
	}
}
