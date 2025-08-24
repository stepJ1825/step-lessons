package by.step.model;

public class Car {
    private int id;
    private String vin;
    private String carMake;
    private String carModel;
    private int releaseYear;
    private String color;
    private int mass; // kilograms
    private int price; // $

    public Car() {
    }

    public Car(int id, String vin, String carMake, String carModel, int releaseYear, String color, int mass, int price) {
        this.id = id;
        this.vin = vin;
        this.carMake = carMake;
        this.carModel = carModel;
        this.releaseYear = releaseYear;
        this.color = color;
        this.mass = mass;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", vin='" + vin + '\'' +
            ", carMake='" + carMake + '\'' +
            ", carModel='" + carModel + '\'' +
            ", releaseYear=" + releaseYear +
            ", color='" + color + '\'' +
            ", mass=" + mass +
            ", price=" + price +
            '}';
    }
}
