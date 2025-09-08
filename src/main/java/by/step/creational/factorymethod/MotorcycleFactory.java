package by.step.creational.factorymethod;

class MotorcycleFactory extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Motorcycle();
    }
}
