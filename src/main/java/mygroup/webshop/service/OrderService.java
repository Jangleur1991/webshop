package mygroup.webshop.service;

import lombok.AllArgsConstructor;
import mygroup.webshop.model.OrderPositionRequest;
import mygroup.webshop.model.OrderPositionResponse;
import mygroup.webshop.model.OrderRequest;
import mygroup.webshop.model.OrderResponse;
import mygroup.webshop.repository.CustomerRepository;
import mygroup.webshop.repository.OrderPositionRepository;
import mygroup.webshop.repository.OrderRepository;
import mygroup.webshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderPositionRepository orderPositionRepository;

    public OrderResponse createOrder(OrderRequest request) throws Exception {
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
