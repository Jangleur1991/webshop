package mygroup.webshop.controller;

import mygroup.webshop.model.CustomerRequest;
import mygroup.webshop.model.CustomerResponse;
import mygroup.webshop.model.ShoppingCartResponse;
import mygroup.webshop.repository.CustomerRepository;
import mygroup.webshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return repository.save(request);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomerById(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("customers/{id}/shoppingcart")
    public ResponseEntity<ShoppingCartResponse> getShoppingCartByCustomersId(@PathVariable String id) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartForCustomer(id));
    }
}
