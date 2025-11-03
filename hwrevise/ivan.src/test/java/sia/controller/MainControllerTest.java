package sia.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    @Test
    void startTest() throws IOException {
        new MainController().start();
    }
}