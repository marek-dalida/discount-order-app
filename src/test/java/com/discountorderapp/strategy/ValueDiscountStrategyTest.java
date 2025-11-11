package com.discountorderapp.strategy;

import com.discountorderapp.discount.DiscountAmountStrategy;
import com.discountorderapp.model.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ValueDiscountStrategyTest {

    @Test
    void shouldReturnPriceDiscountedByDefinedValue() {
        var definedDiscountValue = BigDecimal.valueOf(10);
        var discountPctStartegy = new DiscountAmountStrategy(definedDiscountValue);
        var amount = Money.of(BigDecimal.valueOf(100));
        var discountedValue = discountPctStartegy.applyDiscount(amount);
        var expectedValue = amount.subtract(Money.of(definedDiscountValue));
        Assertions.assertEquals(expectedValue, discountedValue);
    }
}
