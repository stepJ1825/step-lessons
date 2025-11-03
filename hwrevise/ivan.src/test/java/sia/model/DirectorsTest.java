package sia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectorsTest {
    @Test
    void getFullName() {
        Directors director = new Directors();
        director.setFirstName("Ivan");
        director.setLastName("Dorn");
        String solution = director.getFullName();
        assertEquals("Ivan Dorn", solution);
    }
}
