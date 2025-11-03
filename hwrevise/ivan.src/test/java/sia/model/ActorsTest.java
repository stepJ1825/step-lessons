package sia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorsTest {
    @Test
    void getFullNameTest() {
        Actors actor = new Actors();
        actor.setFirstName("Ivan");
        actor.setLastName("Dorn");
        String solution = actor.getFullName();
        assertEquals("Ivan Dorn", solution);
    }
}