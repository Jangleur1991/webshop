package mygroup.webshop.repository;

import mygroup.webshop.model.ProductResponse;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    List<ProductResponse> products = Arrays.asList(
            new ProductResponse("1", "AMD XYZ", "A description 1", 7790,
                    Arrays.asList("AMD", "Processor")),
            new ProductResponse("2", "NVIDIA X", "A description 2", 8890,
                    Arrays.asList("NVIDIA", "Processor"))
    );

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
}
