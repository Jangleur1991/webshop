package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderPositionResponse {
    private String id;
    private String productId;
    private String quantity;
}
