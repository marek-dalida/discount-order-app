package com.discountorderapp.service;

import com.discountorderapp.config.AppProperties;
import com.discountorderapp.discount.DiscountStrategy;
import com.discountorderapp.model.Money;
import com.discountorderapp.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DiscountStrategy discountStrategy;
    private final AppProperties appProperties;

    public void addOrder(String orderCode, BigDecimal totalPrice) {
        var totalOrderPrice = Money.of(totalPrice);
        var priceWithDiscount = discountStrategy.applyDiscount(totalOrderPrice);

        log.info("Order with code: {} befor discount: {}, after discount: {}",
                orderCode, totalOrderPrice.getAmount(), priceWithDiscount.getAmount());

        var order = new Order(orderCode, totalOrderPrice, priceWithDiscount);
        orderRepository.save(order);
    }

    public List<Order> getInitOrders() {
        appProperties.getOrders().forEach(order -> addOrder(order.orderCode(), order.orderAmount()));
        return orderRepository.getOrders();
    }
}
