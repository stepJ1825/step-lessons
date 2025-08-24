package by.step.model;

public class CustomerAddress {
    private int zip;
    private String city;
    private String street;
    private String buildingNumber;
    private String flatNumber;

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public CustomerAddress() {
    }

    public static Builder newBuilder() {
        return new CustomerAddress().new Builder();
    }

    public final class Builder {

        private Builder() {
        }

        public Builder setZipCode(int zipCode) {
            CustomerAddress.this.zip = zipCode;
            return this;
        }

        public Builder setCity(String city) {
            CustomerAddress.this.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            CustomerAddress.this.street = street;
            return this;
        }

        public Builder setBuildingNumber(String buildingNumber) {
            CustomerAddress.this.buildingNumber = buildingNumber;
            return this;
        }

        public Builder setFlatNumber(String flatNumber) {
            CustomerAddress.this.flatNumber = flatNumber;
            return this;
        }

        public CustomerAddress build() {
            return CustomerAddress.this;
        }
    }

    @Override
    public String toString() {
        return "Address{" +
                "zip " + zip +
                ", city " + city +
                ", street " + street +
                "," + buildingNumber +
                "-" + flatNumber +
                '}';
    }
}
