package mygroup.webshop.repository;

import mygroup.webshop.model.CustomerRequest;
import mygroup.webshop.model.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {

    private List<CustomerResponse> customers = new ArrayList<>(Arrays.asList(
            new CustomerResponse(
                    "1",
                    "firstCustomer",
                    "lastName",
                    "customer1@web.de"
            ),
            new CustomerResponse(
                    "2",
                    "secondCustomer",
                    "lastName",
                    "customer2@web.de"
            )
    ));

    public List<CustomerResponse> findAll() {
        return customers;
    }

    public Optional<CustomerResponse> findById(String id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public ResponseEntity<CustomerResponse> save(CustomerRequest request) {
        CustomerResponse customerResponse = new CustomerResponse(
                UUID.randomUUID().toString(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
        );
        customers.add(customerResponse);
        return ResponseEntity.ok(customerResponse);
    }

    public void deleteById(String id) {
        this.customers = customers.stream()
                .filter(c -> !c.getId().equals(id))
                .collect(Collectors.toList());
    }
}
