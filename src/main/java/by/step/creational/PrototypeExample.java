package by.step.creational;

public class PrototypeExample {
    public static void main(String[] args) {
        Shape originalCircle = new Circle(5);
        Shape clonedCircle = originalCircle.clone(); // Клонирование

        System.out.println("Оригинальный круг: " + originalCircle);
        System.out.println("Клонированный круг: " + clonedCircle);

        Shape originalRectangle = new Rectangle(10, 20);
        Shape clonedRectangle = originalRectangle.clone(); // Клонирование

        System.out.println("Оригинальный прямоугольник: " + originalRectangle);
        System.out.println("Клонированный прямоугольник: " + clonedRectangle);
    }
}

interface Shape {
    Shape clone();
}

class Circle implements Shape {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public Shape clone() {
        return new Circle(this.radius); // Создание новой копии
    }

    @Override
    public String toString() {
        return "Круг с радиусом " + radius;
    }
}

class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Shape clone() {
        return new Rectangle(this.width, this.height); // Создание новой копии
    }

    @Override
    public String toString() {
        return "Прямоугольник с шириной " + width + " и высотой " + height;
    }
}