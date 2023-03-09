package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
