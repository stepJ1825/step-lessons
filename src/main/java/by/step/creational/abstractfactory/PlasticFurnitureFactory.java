package by.step.creational.abstractfactory;

// Конкретная фабрика для пластиковой мебели
class PlasticFurnitureFactory implements AbstractFurnitureFactory {
    @Override
    public Chair createChair() {
        return new PlasticChair();
    }

    @Override
    public Table createTable() {
        return new PlasticTable();
    }
}
