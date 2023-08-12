package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ShoppingCartResponse {
    private String customerId;
    private List<OrderPositionResponse> orderPositions;
    private Long totalAmountInCent;
    private Long deliveryCostIntCent;
    private String deliveryOption;
}
