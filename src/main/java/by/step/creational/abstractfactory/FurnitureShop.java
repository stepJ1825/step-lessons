package by.step.creational.abstractfactory;

class FurnitureShop {
    public void makeFurniture(AbstractFurnitureFactory factory) {
        Chair chair = factory.createChair();
        Table table = factory.createTable();
        // Использование созданных объектов
        System.out.println("Создана мебель: " + chair.getClass().getSimpleName() + " и " + table.getClass().getSimpleName());
    }
}
