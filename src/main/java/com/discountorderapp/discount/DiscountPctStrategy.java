package com.discountorderapp.discount;

import com.discountorderapp.model.Money;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "discount.type", havingValue = "pct")
public class DiscountPctStrategy implements DiscountStrategy {
    private final Integer discountPct;

    public DiscountPctStrategy(@Value("${discount.pct}") Integer discountPct) {

        if (discountPct < 0 || discountPct > 100) {
            throw new IllegalArgumentException("Invalid discount value");
        }
        this.discountPct = discountPct;
    }

    public Money applyDiscount(Money money) {
        return money.applyPctDiscount(discountPct);
    }
}
