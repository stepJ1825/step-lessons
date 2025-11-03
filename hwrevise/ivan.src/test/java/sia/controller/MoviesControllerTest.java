package sia.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MoviesControllerTest {

    @Test
    void startTest() throws IOException {
        new MoviesController().start();
    }
}