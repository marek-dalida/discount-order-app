package com.discountorderapp.order;

import com.discountorderapp.config.AppProperties;
import com.discountorderapp.discount.DiscountStrategy;
import com.discountorderapp.discount.NoDiscountStrategy;
import com.discountorderapp.model.Money;
import com.discountorderapp.service.OrderRepository;
import com.discountorderapp.service.OrderService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    private final OrderRepository orderRepository = new OrderRepository();
    private final DiscountStrategy noDiscountStrategy = new NoDiscountStrategy();
    private final AppProperties appProperties = new AppProperties();
    private final OrderService orderService = new OrderService(orderRepository, noDiscountStrategy, appProperties);

    @Test
    void shouldCreatOrder() {
        var orderCode = "ABC2121";
        var totalPrice = new BigDecimal("123.45");
        orderService.addOrder(orderCode, totalPrice);

        var resultOrder = orderRepository.getOrder(orderCode);

        assertThat(resultOrder)
                .isNotNull()
                .hasFieldOrPropertyWithValue("orderCode", orderCode)
                .hasFieldOrPropertyWithValue("priceBeforeDiscount", Money.of(totalPrice))
                .hasFieldOrPropertyWithValue("priceAfterDiscount", Money.of(totalPrice));
    }
}
