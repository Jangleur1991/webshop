package mygroup.webshop.controller;

import mygroup.webshop.model.*;
import mygroup.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) throws Exception {
        CreateOrderResult orderResult = orderService.createOrder(request);
        return (null != orderResult.getResult())
                ? ResponseEntity.ok(orderResult.getResult())
                : ResponseEntity.status(orderResult.getCode()).body(orderResult.getErrors());
    }

    @PostMapping("/orders/{id}/positions")
    public ResponseEntity<OrderPositionResponse> createOrderPosition(
            @PathVariable(name = "id") String orderId,
            @RequestBody OrderPositionRequest request
            ) throws Exception {
        return orderService.createPositionForOrder(orderId, request);
    }

    @GetMapping("/orders")
    public List<OrderResponse> getAllOrders() {
        return orderService.findAll();
    }

}
