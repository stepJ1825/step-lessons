package by.step.creational.factorymethod;

class TruckFactory extends VehicleCreator {
    @Override
    public Vehicle createVehicle() {
        return new Truck();
    }
}
