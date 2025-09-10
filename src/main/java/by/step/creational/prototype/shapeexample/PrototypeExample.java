package by.step.creational.prototype.shapeexample;

public class PrototypeExample {
    public static void main(String[] args) {
        Shape originalCircle = new Circle(5);
        Shape clonedCircle = originalCircle.clone(); // Клонирование

        System.out.println("Оригинальный круг: " + originalCircle);
        System.out.println("Клонированный круг: " + clonedCircle);

        Shape originalRectangle = new Rectangle(10, 20);
        Shape clonedRectangle = originalRectangle.clone(); // Клонирование
        Shape nextOriginalRectangle = new Rectangle(10,20);

        System.out.println("Оригинальный прямоугольник: " + originalRectangle);
        System.out.println("Клонированный прямоугольник: " + clonedRectangle);
    }
}

