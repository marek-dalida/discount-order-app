package com.discountorderapp.discount;


import com.discountorderapp.model.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(name = "discount.type", havingValue = "value")
public class DiscountAmountStrategy implements DiscountStrategy {

    private final Money discountValue;

    public DiscountAmountStrategy(@Value("${discount.value}") BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value cannot be negative");
        }
        this.discountValue = Money.of(value);
    }

    @Override
    public Money applyDiscount(Money money) {
        return money.subtractToZeroMin(discountValue);
    }
}
