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
            var orginalPrice = Money.of(BigDecimal.valueOf(37.37));

            var resultPrice = noDiscountStrategy.applyDiscount(orginalPrice);
        assertThat(resultPrice).isSameAs(orginalPrice);
    }
}
