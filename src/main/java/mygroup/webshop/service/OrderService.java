package mygroup.webshop.service;

import mygroup.webshop.model.*;
import mygroup.webshop.repository.CustomerRepository;
import mygroup.webshop.repository.OrderPositionRepository;
import mygroup.webshop.repository.OrderRepository;
import mygroup.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class OrderService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    public ResponseEntity<OrderResponse> createOrder(OrderRequest request) throws Exception {
        customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new Exception("Customer not found!"));
        return orderRepository.save(request);
    }

    public ResponseEntity<OrderPositionResponse> createPositionForOrder(
            String orderId,
            OrderPositionRequest request) throws Exception {

        OrderResponse orderResponse = orderRepository.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found!"));

        productRepository.findById(request.getProductId())
                .orElseThrow(() -> new Exception("Product not found!"));

        OrderPositionResponse orderPositionResponse = orderPositionRepository.save(request);
        orderResponse.getOrderPositions().add(orderPositionResponse);
        return ResponseEntity.ok(orderPositionResponse);
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll();
    }
}
