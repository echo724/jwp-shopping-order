package cart.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {
    @Test
    @DisplayName("값을 음수로 생성한다.")
    void createByNegativeValue() {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Money(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값이 같은 다른 객체와 동등성을 비교한다.")
    void equalsByValue() {
        //given
        final int value = 10_000;
        final Money money = new Money(value);
        //when
        final boolean result = money.equals(new Money(value));
        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("다른 Money 객체를 더하면 값이 더해진 Money 객체를 반환하다.")
    void add() {
        //given
        final Money base = new Money(3000);
        final Money additive = new Money(1500);
        final Money expected = new Money(4500);

        //when
        final Money actual = base.add(additive);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("곱하기를 하면 원래 값에 비율을 곱한 Money 객체를 반환한다.")
    void multiply() {
        //given
        final Money base = new Money(10_000);
        final Money expected = new Money(1_000);
        //when
        final Money actual = base.multiply(BigDecimal.valueOf(0.1));
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("꽤나 precise한 비율을 곱한다.")
    void multiplyPrecise() {
        //given
        final Money base = new Money(1_000_000_000);
        final Money expected = new Money(111_211_111);
        //when
        final Money actual = base.multiply(BigDecimal.valueOf(0.111_211_111_111_1));
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("다른 Money를 빼면 차를 값으로 가진 Money 객체를 반환한다.")
    void subtract() {
        //given
        final Money base = new Money(10_000);
        final Money subtractive = new Money(1_000);
        //when
        final Money actual = base.subtract(subtractive);
        //then
        assertThat(actual).isEqualTo(new Money(9_000));
    }

    @Test
    @DisplayName("값이 작은 Money와 비교할 경우 true를 반환한다.")
    void isGreaterThan() {
        //given
        final Money money = new Money(10_000);
        final Money other = new Money(1_000);
        //when
        final boolean result = money.isGreaterThanOrEqual(other);
        //then
        assertThat(result).isTrue();
    }
}