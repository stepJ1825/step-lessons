package by.step.creational.factorymethod;

abstract class VehicleCreator {
    // Фабричный метод
    public abstract Vehicle createVehicle();

    public void someOperation() {
        // Используем фабричный метод для создания объекта
        Vehicle vehicle = createVehicle();
        vehicle.drive();
    }
}
