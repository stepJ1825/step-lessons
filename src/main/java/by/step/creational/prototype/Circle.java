package by.step.creational.prototype;

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
