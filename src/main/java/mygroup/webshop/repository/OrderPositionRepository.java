package mygroup.webshop.repository;

import mygroup.webshop.model.OrderPositionRequest;
import mygroup.webshop.model.OrderPositionResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrderPositionRepository {
    List<OrderPositionResponse> positions = new ArrayList<>();
    public OrderPositionResponse save(String orderId, OrderPositionRequest request) {
        OrderPositionResponse orderPositionResponse = new OrderPositionResponse(
                UUID.randomUUID().toString(),
                orderId,
                request.getProductId(),
                request.getQuantity()
        );
        positions.add(orderPositionResponse);
        return orderPositionResponse;
    }

    public List<OrderPositionResponse> findAllByOrdersId(List<String> orderIds) {
        return positions.stream().filter(position -> orderIds.contains(position.getOrderId()))
                .collect(Collectors.toList());
    }
}
