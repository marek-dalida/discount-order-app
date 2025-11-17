package com.discountorderapp.order;

import com.discountorderapp.model.Money;
import com.discountorderapp.service.OrderRepository;
import com.discountorderapp.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"discount.type=value", "discount.value=10"})
public class OrderServiceValueTypeTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${discount.value}")
    private int discountAmount;

    @Test
    void createOrderWithValueDiscount() {
        var orderCode = "XYZ2137";
        var price = new BigDecimal("21.21");
        var expectedPrice = Money.of(price).subtractToZeroMin(Money.of(BigDecimal.valueOf(discountAmount)));

        orderService.addOrder(orderCode, price);

        var order = orderRepository.getOrder(orderCode);

        assertThat(order)
                .isNotNull()
                .hasFieldOrPropertyWithValue("orderCode", orderCode)
                .hasFieldOrPropertyWithValue("priceBeforeDiscount", Money.of(price))
                .hasFieldOrPropertyWithValue("priceAfterDiscount", expectedPrice);
    }
}
