package by.step.structural.composite;

import java.awt.*;
import java.io.Serializable;

public class CircleWithName extends Circle implements AutoCloseable {
    private String name;

    public CircleWithName(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public int getWidth() {
        int width = super.getWidth();
        if (width == 0) throw new IllegalArgumentException();
        return width;
    }
}
