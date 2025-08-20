package by.step.classwork2.model;


public class Worker {

    private String name;
    private String position;
    private Integer salary;

    public Worker(String name, String position, Integer salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public Integer getSalary() {
        return salary;
    }
}
