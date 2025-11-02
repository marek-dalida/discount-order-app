package com.discountorderapp.scheduling;


import com.discountorderapp.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Profile("random-order")
@AllArgsConstructor
public class RandomOrderSchedulerTask {

    private final OrderService orderService;

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void createRandomOrder() {
        var orderCode = UUID.randomUUID().toString();
        var totalPrice = BigDecimal.valueOf(Math.random() * 1000);
        orderService.addOrder(orderCode, totalPrice);
    }
}
