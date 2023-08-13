package mygroup.webshop.repository;

import mygroup.webshop.model.ProductRequest;
import mygroup.webshop.model.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    List<ProductResponse> products = new ArrayList<>(Arrays.asList(
            new ProductResponse("1", "AMD XYZ", "A description 1", 7790,
                    Arrays.asList("AMD", "Processor")),
            new ProductResponse("2", "NVIDIA X", "A description 2", 8890,
                    Arrays.asList("NVIDIA", "Processor"))
    ));

    public List<ProductResponse> findAll(String tag) {
        if (null == tag) {
            return products;
        } else {
            String lowercaseTag = tag.toLowerCase();
            return products.stream()
                    .filter(p -> lowercaseTags(p).contains(lowercaseTag))
                    .collect(Collectors.toList());
        }
    }

    private List<String> lowercaseTags(ProductResponse p) {
        return p.getTags().stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void deleteById(String id) {
        this.products = products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductRequest request) {
        ProductResponse productResponse = new ProductResponse(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags()
        );
        products.add(productResponse);
        return productResponse;
    }
}
