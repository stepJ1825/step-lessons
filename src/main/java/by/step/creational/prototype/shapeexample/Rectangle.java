package by.step.creational.prototype.shapeexample;

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
