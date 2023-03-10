package mygroup.webshop.service;

import mygroup.webshop.model.CustomerResponse;
import mygroup.webshop.model.OrderRequest;
import mygroup.webshop.model.OrderResponse;
import mygroup.webshop.repository.CustomerRepository;
import mygroup.webshop.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    public ResponseEntity<OrderResponse> createOrder(OrderRequest request) throws Exception {
        Optional<CustomerResponse> customer = customerRepository.findById(request.getCustomerId());
        if (customer.isEmpty()) {
            throw new Exception("Customer not found!");
        }
        return orderRepository.save(request);
    }
}
