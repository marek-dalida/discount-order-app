package com.discountorderapp.discount;

import com.discountorderapp.model.Money;

public interface DiscountStrategy {

    Money applyDiscount(Money money);
}
