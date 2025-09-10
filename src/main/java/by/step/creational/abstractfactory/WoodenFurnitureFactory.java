package by.step.creational.abstractfactory;

// Конкретная фабрика для деревянной мебели
class WoodenFurnitureFactory implements AbstractFurnitureFactory {
    @Override
    public Chair createChair() {
        return new WoodenChair();
    }

    @Override
    public Table createTable() {
        return new WoodenTable();
    }
}
