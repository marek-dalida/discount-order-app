package com.discountorderapp.strategy;

import com.discountorderapp.discount.DiscountPctStrategy;
import com.discountorderapp.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PctDiscountStrategyTest {

    @Test
    void shouldDiscountMoneyByDefinedPercent() {
        var pctDiscountStrategy = new DiscountPctStrategy(10);
        var amount = Money.of(BigDecimal.valueOf(90));
        var discountedValue = pctDiscountStrategy.applyDiscount(amount);
        var expectedValue = amount.multiply(0.9);
        assertThat(discountedValue.getAmount()).isEqualTo(expectedValue.getAmount());
    }
}
