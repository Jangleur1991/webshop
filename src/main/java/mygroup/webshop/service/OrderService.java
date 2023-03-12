package mygroup.webshop.service;

import lombok.AllArgsConstructor;
import mygroup.webshop.model.*;
import mygroup.webshop.repository.CustomerRepository;
import mygroup.webshop.repository.OrderPositionRepository;
import mygroup.webshop.repository.OrderRepository;
import mygroup.webshop.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderService {

    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderPositionRepository orderPositionRepository;

    public CreateOrderResult createOrder(OrderRequest request) throws Exception {
        Optional<CustomerResponse> customer = customerRepository.findById(request.getCustomerId());
        return customer.isEmpty()
                ? errorResult()
                : nonErrorResult(request);
//        customerRepository.findById(request.getCustomerId())
//                .orElseThrow(() -> new Exception("Customer not found!"));
//        return orderRepository.save(request);
    }

    private CreateOrderResult nonErrorResult(OrderRequest request) {
        return new CreateOrderResult(
                orderRepository.save(request),
                Collections.emptyList(),
                HttpStatus.OK
        );
    }

    private CreateOrderResult errorResult() {
        ErrorResponse errorResponse = new ErrorResponse("Customer not found!");
        return new CreateOrderResult(null, List.of(errorResponse), HttpStatus.BAD_REQUEST);
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
