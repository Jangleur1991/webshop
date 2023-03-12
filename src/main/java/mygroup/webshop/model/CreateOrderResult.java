package mygroup.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateOrderResult {
    private OrderResponse result;
    private List<ErrorResponse> errors;

    private HttpStatus code;
}
