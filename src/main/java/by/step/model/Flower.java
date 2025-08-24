package by.step.model;

import java.util.List;

public class Flower {
    private int id;
    private String commonName;
    private String plantFamily;
    private double waterConsumptionPerDay;
    private boolean shadePreferred;
    private String origin;
    private int price;
    private List<String> flowerVaseMaterial;

    public Flower() {
    }

    public Flower(int id, String commonName, String plantFamily, double waterConsumptionPerDay, boolean shadePreferred,
                  String origin, int price, List<String> flowerVaseMaterial) {
        this.id = id;
        this.commonName = commonName;
        this.plantFamily = plantFamily;
        this.waterConsumptionPerDay = waterConsumptionPerDay;
        this.shadePreferred = shadePreferred;
        this.origin = origin;
        this.price = price;
        this.flowerVaseMaterial = flowerVaseMaterial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getPlantFamily() {
        return plantFamily;
    }

    public void setPlantFamily(String plantFamily) {
        this.plantFamily = plantFamily;
    }

    public double getWaterConsumptionPerDay() {
        return waterConsumptionPerDay;
    }

    public void setWaterConsumptionPerDay(double waterConsumptionPerDay) {
        this.waterConsumptionPerDay = waterConsumptionPerDay;
    }

    public boolean isShadePreferred() {
        return shadePreferred;
    }

    public void setShadePreferred(boolean shadePreferred) {
        this.shadePreferred = shadePreferred;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getFlowerVaseMaterial() {
        return flowerVaseMaterial;
    }

    public void setFlowerVaseMaterial(List<String> flowerVaseMaterial) {
        this.flowerVaseMaterial = flowerVaseMaterial;
    }

    @Override
    public String toString() {
        return "Flower{" +
            "id=" + id +
            ", commonName='" + commonName + '\'' +
            ", plantFamily='" + plantFamily + '\'' +
            ", waterConsumptionPerDay=" + waterConsumptionPerDay +
            ", shadePreferred=" + shadePreferred +
            ", origin='" + origin + '\'' +
            ", price=" + price +
            ", flowerVaseMaterial=" + flowerVaseMaterial + '}';
    }
}
