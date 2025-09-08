package by.step.structural.composite;

import java.awt.*;

public class Demo {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();

        Circle circle1 = new Circle(10, 10, 10, Color.BLUE);
        Circle circle2 = new Circle(10, 10, 15, Color.CYAN);
        Circle circle3 = new Circle(10, 10, 20, Color.CYAN);

        editor.loadShapes(

                circle1,

                new CompoundShape(circle1,new CompoundShape(circle2,circle3)),

                new CompoundShape(
                        new Circle(110, 110, 50, Color.RED),
                        new Dot(160, 160, Color.RED)
                ),

                new CompoundShape(
                        new Rectangle(250, 250, 100, 100, Color.GREEN),
                        new Dot(240, 240, Color.GREEN),
                        new Dot(240, 360, Color.GREEN),
                        new Dot(360, 360, Color.GREEN),
                        new Dot(360, 240, Color.GREEN)
                )


        );
    }
}