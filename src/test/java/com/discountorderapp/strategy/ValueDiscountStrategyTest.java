package com.discountorderapp.strategy;

import com.discountorderapp.discount.DiscountAmountStrategy;
import com.discountorderapp.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ValueDiscountStrategyTest {

    private final DiscountAmountStrategy discountAmountStrategy = new DiscountAmountStrategy(BigDecimal.valueOf(10));

    @Test
    void shouldReturnPriceDiscountedByDefinedValue() {
        var definedDiscountValue = BigDecimal.valueOf(10);
        var amount = Money.of(BigDecimal.valueOf(100));
        var discountedValue = discountAmountStrategy.applyDiscount(amount);
        var expectedValue = amount.subtract(Money.of(definedDiscountValue));
        assertThat(discountedValue).isEqualTo(expectedValue);
    }

    @Test
    void shouldReturnZeroAfterDisocunt() {
        var amount = Money.of(BigDecimal.valueOf(10));
        var discountedValue = discountAmountStrategy.applyDiscount(amount);
        var expectedValue = Money.of(BigDecimal.ZERO);
        assertThat(discountedValue).isEqualTo(expectedValue);
    }

    @Test
    void shouldThrowExceptionDiscountExceedsPrice() {
        var amount = Money.of(BigDecimal.valueOf(9));
        var discountedValue = discountAmountStrategy.applyDiscount(amount);
        assertThat(discountedValue).isEqualTo(Money.of(BigDecimal.ZERO));
    }

}
