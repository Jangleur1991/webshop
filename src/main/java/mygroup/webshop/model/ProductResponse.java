package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Integer priceInCent;
    private List<String> tags;

}
