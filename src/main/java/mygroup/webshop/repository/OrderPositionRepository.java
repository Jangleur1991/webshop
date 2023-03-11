package mygroup.webshop.repository;

import mygroup.webshop.model.OrderPositionRequest;
import mygroup.webshop.model.OrderPositionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderPositionRepository {
    List<OrderPositionResponse> positions = new ArrayList<>();
    public OrderPositionResponse save(OrderPositionRequest request) {
        OrderPositionResponse orderPositionResponse = new OrderPositionResponse(
                UUID.randomUUID().toString(),
                request.getProductId(),
                request.getQuantity()
        );
        positions.add(orderPositionResponse);
        return orderPositionResponse;
    }
}
