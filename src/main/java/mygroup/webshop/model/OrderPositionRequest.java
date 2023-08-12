package mygroup.webshop.model;

import lombok.Getter;

@Getter
public class OrderPositionRequest {
    private String productId;
    private Integer quantity;
}
