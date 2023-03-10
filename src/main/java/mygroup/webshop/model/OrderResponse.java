package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private String id;
    private String customerId;

    private LocalDate orderTime;

    private OrderStatus orderStatus;

    private List<OrderPosition> orderPositions;
}
