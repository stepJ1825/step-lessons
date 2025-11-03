package sia;

import sia.controller.MainController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new MainController().start();
    }
}
