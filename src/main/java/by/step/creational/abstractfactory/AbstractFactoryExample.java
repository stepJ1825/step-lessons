package by.step.creational.abstractfactory;

public class AbstractFactoryExample {
    public static void main(String[] args) {
        FurnitureShop shop = new FurnitureShop();
        // Создаем деревянную мебель
        shop.makeFurniture(new WoodenFurnitureFactory()); // Вывод: Создана мебель: WoodenChair и WoodenTable
        // Создаем пластиковую мебель
        shop.makeFurniture(new PlasticFurnitureFactory()); // Вывод: Создана мебель: PlasticChair и PlasticTable
    }
}

