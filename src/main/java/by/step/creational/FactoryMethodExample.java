package by.step.creational;

import java.util.Scanner;

public class FactoryMethodExample {
    private static VehicleCreator vehicle;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * Приложение создаёт определённую фабрику в зависимости от конфигурации или окружения.
     */
    static void configure() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("car")) {
            vehicle = new CarFactory();
        } else {
            vehicle = new TruckFactory();
        }
    }

    /**
     * Весь остальной клиентский код работает с фабрикой и продуктами только через общий интерфейс, поэтому для него
     * неважно какая фабрика была создана.
     *
     * @return
     */
    static void runBusinessLogic() {
        vehicle.someOperation();
    }
}

interface Vehicle {
    void drive();
}

class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Едем на машине");
    }
}

class Truck implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Едем на грузовике");
    }
}

abstract class VehicleCreator {
    // Фабричный метод
    public abstract Vehicle createVehicle();

    public void someOperation() {
        // Используем фабричный метод для создания объекта
        Vehicle vehicle = createVehicle();
        vehicle.drive();
    }
}

class CarFactory extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

class TruckFactory extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Truck();
    }
}