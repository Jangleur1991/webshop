package mygroup.webshop.service;

import lombok.RequiredArgsConstructor;
import mygroup.webshop.model.OrderPositionResponse;
import mygroup.webshop.model.OrderResponse;
import mygroup.webshop.model.ProductResponse;
import mygroup.webshop.model.ShoppingCartResponse;
import mygroup.webshop.repository.OrderPositionRepository;
import mygroup.webshop.repository.OrderRepository;
import mygroup.webshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartService {
    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;
    private final ProductRepository productRepository;

    public ShoppingCartResponse getShoppingCartForCustomer(String customerId) {

        List<OrderResponse> orders = orderRepository.findAllByCustomerIdWhereStatusNew(customerId);

        List<String> orderIds = orders.stream().map(OrderResponse::getId).collect(Collectors.toList());

        List<OrderPositionResponse> orderPositions = orderPositionRepository.findAllByOrdersId(orderIds);

        long deliverCost = 500L;
        long totalAmount = calculateSumForCart(orderPositions, deliverCost);

        return new ShoppingCartResponse(customerId,
                orderPositions,
                totalAmount,
                deliverCost,
                "STANDARD");
    }

    public Long calculateSumForCart(List<OrderPositionResponse> orderPositions, long deliverCost) {
        int positionSum = orderPositions.stream().mapToInt(position -> {
            ProductResponse product = productRepository //
                    .findById(position.getProductId()) //
                    .orElseThrow(NoSuchElementException::new);
            return product.getPriceInCent() * position.getQuantity();
        }).sum();

        return positionSum+deliverCost;
    }
}
