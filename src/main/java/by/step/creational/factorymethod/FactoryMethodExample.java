package by.step.creational.factorymethod;

import java.util.Scanner;

public class FactoryMethodExample {
    private static VehicleCreator vehicleCreator;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * Приложение создаёт определённую фабрику в зависимости от конфигурации или окружения.
     */
    static void configure() {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "car", "c" -> vehicleCreator = new CarFactory();
            case "moto", "m" -> vehicleCreator = new MotorcycleFactory();
            default -> vehicleCreator = new TruckFactory();
        }
    }

    /**
     * Весь остальной клиентский код работает с фабрикой и продуктами только через общий интерфейс, поэтому для него
     * неважно какая фабрика была создана.
     *
     * @return
     */
    static void runBusinessLogic() {
        vehicleCreator.someOperation();
    }
}

