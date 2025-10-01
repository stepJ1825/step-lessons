package by.step;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.stream.Stream;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class StackCustomTest {

    private StackCustom stackCustom1;
    private StackCustom stackCustom2;
    private StackCustom stackCustom3;

    @BeforeEach
    void init() {
        stackCustom1 = new StackCustom<Integer>();
        stackCustom1.push(4);
        stackCustom1.push(1);
        stackCustom1.push(2);
        stackCustom2 = new StackCustom<String>();
        stackCustom2.push("aaa");
        stackCustom2.push("bbb");
        stackCustom2.push("ccc");
        stackCustom2.push("AAA");
        stackCustom3 = new StackCustom<>();
        stackCustom3.push(new Person(10, "first"));
        stackCustom3.push(new Person(20, "second"));

    }

    @Test
    void pushSuccessCheckWithPeek() {
        StackCustom stackCustom = new StackCustom<>();
        Person first = new Person(10, "first");
        stackCustom.push(first);
        Person peekedPerson = (Person) stackCustom.peek();
        Assertions.assertThat(peekedPerson).isEqualTo(first);
    }

    @Test
    void pushSuccessCheckWithReflection() throws NoSuchFieldException, IllegalAccessException {
        StackCustom<Integer> stackCustom = new StackCustom<>();
        stackCustom.push(1000);
        Class<?> aClass = stackCustom.getClass();
        Field dataField = aClass.getDeclaredField("data");
        dataField.setAccessible(true);
        LinkedList<Integer> data = (LinkedList<Integer>) dataField.get(stackCustom);
        Assertions.assertThat(data).isNotEmpty();
    }

    @Test
    void pushIncorrect() {
        StackCustom stackCustom = new StackCustom<Integer>();
        stackCustom.push(100);
        org.junit.jupiter.api.Assertions.assertThrows(ClassCastException.class,
                () -> stackCustom.push(1000D));
        //https://www.baeldung.com/assertj-exception-assertion
        Assertions.assertThatThrownBy(
                        () -> stackCustom.push(1000D))
                .isInstanceOf(ClassCastException.class);
    }

    @Test
    void pop() {
        StackCustom stackCustom = new StackCustom<>();
        Person first = new Person(10, "first");
        stackCustom.push(first);

        Person popped = (Person) stackCustom.pop();
        Person peeked = (Person) stackCustom.peek();
        Person poppedSecond = (Person) stackCustom.pop();
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(popped).isEqualTo(first);
            softAssertions.assertThat(peeked).isNotEqualTo(first);
            softAssertions.assertThat(poppedSecond).isNull();
        });
    }

    @ParameterizedTest
    @MethodSource("sourceForGetMinCheck")
    void getMin(StackCustom stackCustom,
                Object minElement,
                Object lowerThanMin) {
        Object minElementFromStack = stackCustom.getMin();
        stackCustom.push((Comparable) lowerThanMin);
        Object minElementFromStack2 = stackCustom.getMin();
        Object popped = stackCustom.pop();
        Object minElementFromStack3 = stackCustom.getMin();
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(minElementFromStack).isEqualTo(minElement);
            softAssertions.assertThat(minElementFromStack2).isEqualTo(lowerThanMin);
            softAssertions.assertThat(popped).isEqualTo(lowerThanMin);
            softAssertions.assertThat(minElementFromStack3).isEqualTo(minElement);
        });
    }

    public static Stream<Arguments> sourceForGetMinCheck() {
        StackCustom<Integer> integerStackCustom = new StackCustom<>();
        integerStackCustom.push(4);
        integerStackCustom.push(3);
        integerStackCustom.push(5);
        integerStackCustom.push(1);
        integerStackCustom.push(9);
        integerStackCustom.push(3);
        integerStackCustom.push(2);
        integerStackCustom.push(6);

        StackCustom<Person> personStackCustom = new StackCustom<>();
        personStackCustom.push(new Person(10, "first"));
        personStackCustom.push(new Person(20, "second"));
        Person third = new Person(5, "third");
        Person fifth = new Person(0, "fifth");
        personStackCustom.push(third);
        personStackCustom.push(new Person(15, "fourth"));

        StackCustom<String> stringStackCustom = new StackCustom<>();
        stringStackCustom.push("aaa");
        stringStackCustom.push("bbb");
        stringStackCustom.push("ccc");
        stringStackCustom.push("AAA");

        return Stream.of(
                Arguments.of(integerStackCustom, 1, -1),
                Arguments.of(personStackCustom, third, fifth),
                Arguments.of(stringStackCustom, "AAA", "AA"));
    }

    @Test
    void getMinFromEmptyStack() {
        StackCustom integerStackCustom = new StackCustom<>();
        Object min = integerStackCustom.getMin();
        Assertions.assertThat(min).isNull();
    }

}