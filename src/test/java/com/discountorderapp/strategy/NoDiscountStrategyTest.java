package com.discountorderapp.strategy;

import com.discountorderapp.discount.NoDiscountStrategy;
import com.discountorderapp.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class NoDiscountStrategyTest {

    @Test
    void shouldReturnOriginalPrice() {
        var noDiscountStrategy = new NoDiscountStrategy();
        var originalPrice = Money.of(BigDecimal.valueOf(37.37));

        var resultPrice = noDiscountStrategy.applyDiscount(originalPrice);
        assertThat(resultPrice).isSameAs(originalPrice);
    }
}
