package com.discountorderapp.money;


import com.discountorderapp.model.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @Test
    void shouldThrowExceptionWhenAmountIsNull() {
        assertThatThrownBy(() -> Money.of(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Amount cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenAmountIsNegative() {
        assertThatThrownBy(() -> Money.of(BigDecimal.valueOf(-21)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Amount has to be positive or zero");
    }

    @Test
    void shouldAddMoneyAmounts() {
        var money = Money.of(BigDecimal.valueOf(20));
        var money2 = Money.of(BigDecimal.valueOf(1.37));
        var res = money.add(money2);

        assertThat(res).isEqualTo(Money.of(BigDecimal.valueOf(21.37)));
    }

    @Test
    void shouldSubtractMoneyAmounts() {
        var money = Money.of(BigDecimal.valueOf(22.37));
        var money2 = Money.of(BigDecimal.valueOf(1.37));

        var res = money.subtract(money2);
        assertThat(res).isEqualTo(Money.of(BigDecimal.valueOf(21.00)));
    }

    @Test
    void shouldSubtractMoneyAmountsToMin() {
        var money = Money.of(BigDecimal.valueOf(22.37));
        var money2 = Money.of(BigDecimal.valueOf(221.37));

        var res = money.subtractToZeroMin(money2);
        assertThat(res).isEqualTo(Money.of(BigDecimal.ZERO));
    }

    @Test
    void shouldMultiplyMoneyAmounts() {
        var money = Money.of(BigDecimal.valueOf(22.22));

        var res = money.multiply(BigDecimal.TWO);
        assertThat(res).isEqualTo(Money.of(BigDecimal.valueOf(44.44)));
    }

    @Test
    void shouldApplyPctDiscount() {
        var money = Money.of(BigDecimal.valueOf(22.00));

        var res = money.applyPctDiscount(10);
        assertThat(res).isEqualTo(Money.of(BigDecimal.valueOf(19.80)));
    }

}
