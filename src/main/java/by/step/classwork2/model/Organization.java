package by.step.classwork2.model;

public class Organization {
    private String name;
    private String fullName;
    private String nn;

    public Organization(String name, String fullName, String nn) {
        this.name = name;
        this.fullName = fullName;
        this.nn = nn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNn() {
        return nn;
    }

    public void setNn(String nn) {
        this.nn = nn;
    }
}
