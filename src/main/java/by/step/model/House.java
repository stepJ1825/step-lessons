package by.step.model;

import java.util.List;

public class House {
    private int id;
    private String buildingType;
    private List<Person> personList;

    public House() {
    }



    public House(int id, String buildingType, List<Person> personList) {
        this.id = id;
        this.buildingType = buildingType;
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "House{id=" + id + ", buildingType='" + buildingType + "', personList=" + personList + '}';
    }
}
