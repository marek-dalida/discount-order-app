package com.discountorderapp.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@EqualsAndHashCode
public class Money {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @Getter
    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount.setScale(SCALE, ROUNDING_MODE);
    }

    public static Money of(BigDecimal amount) {
        validateAmount(amount);
        return new Money(amount);
    }

    public Money subtract(Money other) {
        Objects.requireNonNull(other, "Argument cannot be null");
        BigDecimal result = this.amount.subtract(other.amount);
        validateAmount(result);
        return new Money(result);
    }

    public Money subtractToZeroMin(Money other) {
        Objects.requireNonNull(other, "Argument cannot be null");
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            return Money.of(BigDecimal.ZERO);
        } else {
            return new Money(result);
        }
    }

    public Money add(Money other) {
        Objects.requireNonNull(other, "Argument cannot be null");
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(BigDecimal multiplier) {
        Objects.requireNonNull(multiplier, "Multiplier cannot be null");
        BigDecimal result = this.amount.multiply(multiplier);
        validateAmount(result);
        return new Money(result);
    }

    public Money multiply(double multiplier) {
        return multiply(BigDecimal.valueOf(multiplier));
    }

    public Money applyPctDiscount(Integer pctDiscount) {
        var discountMultiplier = BigDecimal.valueOf(pctDiscount).divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);

        var discountAmount = new Money(this.amount.multiply(discountMultiplier));
        return this.subtract(discountAmount);
    }

    private static void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount has to be positive or zero: " + amount);
        }
    }

}