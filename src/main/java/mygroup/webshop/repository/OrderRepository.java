package mygroup.webshop.repository;

import mygroup.webshop.model.OrderRequest;
import mygroup.webshop.model.OrderResponse;
import mygroup.webshop.model.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {

    private List<OrderResponse> orders = new ArrayList<>();
    public ResponseEntity<OrderResponse> save(OrderRequest request) {
        OrderResponse orderResponse = new OrderResponse(
                UUID.randomUUID().toString(),
                request.getCustomerId(),
                LocalDate.now(),
                OrderStatus.NEW,
                new ArrayList<>()
        );
        orders.add(orderResponse);
        return ResponseEntity.ok(orderResponse);
    }

}
