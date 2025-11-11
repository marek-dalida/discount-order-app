package com.discountorderapp.strategy;

import com.discountorderapp.discount.DiscountPctStrategy;
import com.discountorderapp.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PctDiscountStrategyTest {

    @Test
    void shouldDisocuntMoneyByDefinedPercent() {
        var pctDiscountStrategy = new DiscountPctStrategy(10);
        var amount = Money.of(BigDecimal.valueOf(90));
        var discountedValue = pctDiscountStrategy.applyDiscount(amount);
        var expectedValue = amount.multiply(0.9);
        Assertions.assertEquals(expectedValue, discountedValue);
    }
}
