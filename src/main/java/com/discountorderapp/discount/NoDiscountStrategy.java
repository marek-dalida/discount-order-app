package com.discountorderapp.discount;

import com.discountorderapp.model.Money;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public Money applyDiscount(Money money) {
        return money;
    }
}
