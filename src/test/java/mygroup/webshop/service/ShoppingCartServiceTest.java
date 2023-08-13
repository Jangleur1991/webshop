package mygroup.webshop.service;

import mygroup.webshop.model.OrderPositionResponse;
import mygroup.webshop.model.ProductRequest;
import mygroup.webshop.model.ProductResponse;
import mygroup.webshop.repository.OrderPositionRepository;
import mygroup.webshop.repository.OrderRepository;
import mygroup.webshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShoppingCartServiceTest {

    private ProductRepository productRepository;
    private ProductResponse saveProduct;
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    void setup() {
        productRepository = new ProductRepository();

        saveProduct = productRepository.save(new ProductRequest("", "", 1000, new ArrayList<>()));

        shoppingCartService = new ShoppingCartService(
                new OrderRepository(),
                new OrderPositionRepository(),
                productRepository
        );
    }

    @Test
    void calculateSumForCartEmptyCartReturnsDeliveryCost() {
        //given
        long deliverCost = 500L;

        //when
        Long result = shoppingCartService.calculateSumForCart(Collections.emptyList(), deliverCost);

        //then
        assertThat(result).isEqualTo(deliverCost);
    }

    @Test
    void testThatCalculateSumForCartWithOneProductSumsPriceOfProduct() {
        //given

        List<OrderPositionResponse> orderpositions = new ArrayList<>();
        orderpositions.add(new OrderPositionResponse("1", "order-id", saveProduct.getId(), 1));

        //when
        Long result = shoppingCartService.calculateSumForCart(orderpositions, 500L);

        //then
        assertThat(result).isEqualTo(1500);
    }
}