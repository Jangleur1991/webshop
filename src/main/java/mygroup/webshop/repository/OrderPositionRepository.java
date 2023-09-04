package mygroup.webshop.repository;

import mygroup.webshop.model.OrderPositionRequest;
import mygroup.webshop.model.OrderPositionResponse;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderPositionRepository {

    private Map<String, OrderPositionResponse> orderPositionResponseMap = new HashMap<>();

    public OrderPositionResponse save(String orderId, OrderPositionRequest request) {
        String key = orderId + "_" + request.getProductId();
        return orderPositionResponseMap.compute(key, (k, orderPos) -> //
                new OrderPositionResponse(
                        (null != orderPos) ? orderPos.getId() : UUID.randomUUID().toString(),
                        orderId,
                        request.getProductId(),
                        (null != orderPos) ? orderPos.getQuantity() + request.getQuantity() : request.getQuantity()
                ));
    }

    public List<OrderPositionResponse> findAllByOrdersId(List<String> orderIds) {
        return orderPositionResponseMap.values().stream()
                .filter(position -> orderIds.contains(position.getOrderId()))
                .collect(Collectors.toList());
    }
}
