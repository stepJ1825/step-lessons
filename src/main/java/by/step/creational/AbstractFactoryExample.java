package by.step.creational;

public class AbstractFactoryExample {
    public static void main(String[] args) {
        FurnitureShop shop = new FurnitureShop();
        // Создаем деревянную мебель
        shop.makeFurniture(new WoodenFurnitureFactory()); // Вывод: Создана мебель: WoodenChair и WoodenTable
        // Создаем пластиковую мебель
        shop.makeFurniture(new PlasticFurnitureFactory()); // Вывод: Создана мебель: PlasticChair и PlasticTable
    }
}

interface AbstractFurnitureFactory {
    public Chair createChair();
    public Table createTable();
}

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

interface Chair {}
interface Table {}

class WoodenChair implements Chair {}
class WoodenTable implements Table {}
class PlasticChair implements Chair {}
class PlasticTable implements Table {}

class FurnitureShop {
    public void makeFurniture(AbstractFurnitureFactory factory) {
        Chair chair = factory.createChair();
        Table table = factory.createTable();
        // Использование созданных объектов
        System.out.println("Создана мебель: " + chair.getClass().getSimpleName() + " и " + table.getClass().getSimpleName());
    }
}
