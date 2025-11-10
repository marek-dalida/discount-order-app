package com.discountorderapp.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public record OrderDto(String orderCode, BigDecimal orderAmount) {
}
