package mygroup.webshop.model;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductRequest {
    private String name;
    private String description;
    private Integer priceInCent;
    private List<String> tags;
}
