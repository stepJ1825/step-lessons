package by.step;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListTest {

    static List<Integer> myIntegerList;
    static List<String> myStringList;
    static List<Object> myObjectList;

    @BeforeEach
    void init(){
        myIntegerList = Arrays.asList(1,3,5,7,10);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 10})
    void checkAddition(int x){
        ArrayList<String> strings = new ArrayList<>();
    }
}
