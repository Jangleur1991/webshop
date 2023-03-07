package mygroup.webshop.controller;

import mygroup.webshop.model.ProductResponse;
import mygroup.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tags) {
        return repository.findAll(tags);
    }

    @GetMapping("products/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}
