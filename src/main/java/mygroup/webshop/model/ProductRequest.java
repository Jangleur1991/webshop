package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String description;
    private Integer priceInCent;
    private List<String> tags;
}
