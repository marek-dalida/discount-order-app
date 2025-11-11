package com.discountorderapp.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

    public Money applyPctDiscount(Integer pctDiscount) {
        var discountMultiplier = BigDecimal.valueOf(pctDiscount).divide(BigDecimal.valueOf(100), SCALE, ROUNDING_MODE);

        var discountAmount = new Money(this.amount.multiply(discountMultiplier));
        return this.subtract(discountAmount);
    }

    public Money subtract(Money other) {
        Objects.requireNonNull(other, "Argument cannot be null");
        BigDecimal result = this.amount.subtract(other.amount);
        validateAmount(result);
        return new Money(result);
    }

    private static void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount has to positive or zero: " + amount);
        }
    }

    public Money add(Money other) {
        Objects.requireNonNull(other, "Argument nie może być null");
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(BigDecimal multiplier) {
        Objects.requireNonNull(multiplier, "Mnożnik nie może być null");
        BigDecimal result = this.amount.multiply(multiplier);
        validateAmount(result);
        return new Money(result);
    }

    public Money multiply(double multiplier) {
        return multiply(BigDecimal.valueOf(multiplier));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.compareTo(money.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    /**
     * Formatuje kwotę z symbolem waluty
     */
    public String format(String currencySymbol) {
        return currencySymbol + " " + amount.toString();
    }
}