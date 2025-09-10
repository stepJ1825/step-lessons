package by.step.creational.factorymethod;

class CarFactory extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
