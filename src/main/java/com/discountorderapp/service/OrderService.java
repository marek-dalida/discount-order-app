package com.discountorderapp.service;

import com.discountorderapp.discount.DiscountStrategy;
import com.discountorderapp.model.Money;
import com.discountorderapp.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DiscountStrategy discountStrategy;

    public void addOrder(String orderCode, BigDecimal totalPrice) {
        var totalOrderPrice = Money.of(totalPrice);
        var priceWithDiscount = discountStrategy.applyDiscount(totalOrderPrice);

        log.info("Order with code: {} befor deiscount: {}, after discount: {}",
                orderCode, totalOrderPrice.getAmount(), priceWithDiscount.getAmount());

        var order = new Order(orderCode, totalOrderPrice, priceWithDiscount);
        orderRepository.save(order);
    }
}
