package com.discountorderapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {

    private String orderCode;
    private Money priceBeforeDiscount;
    private Money priceAfterDiscount;

}
