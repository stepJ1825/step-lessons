package by.step;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.*;

class StackCustomTest {

    private StackCustom stackCustom = new StackCustom();

    @Test
    void push() {
        stackCustom.push(4);
        stackCustom.push(1);
        stackCustom.push(2);
        Object min = stackCustom.getMin();
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(min).isNotNull();
            softAssertions.assertThat(min).isEqualTo(1);
        });

    }

    @Test
    void pop() {
        stackCustom.push(4);
        stackCustom.push(1);
        stackCustom.push(2);
        Object min1 = stackCustom.getMin();
        stackCustom.pop();
        Object min2 = stackCustom.getMin();
        stackCustom.pop();
        Object min3 = stackCustom.getMin();
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(min1).isEqualTo(min2);
            softAssertions.assertThat(min1).isNotEqualTo(min3);
        });
    }

    @Test
    void peek() {
    }

    @Test
    void getMin() {
    }
}