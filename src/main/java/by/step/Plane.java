package by.step;

public class Plane {
    private int speed;
    private int weight;

    public Plane(int speed, int weight) {
        this.speed = speed;
        this.weight = weight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;

        Plane plane = (Plane) o;

        if (getSpeed() != plane.getSpeed()) return false;
        return getWeight() == plane.getWeight();
    }

    @Override
    public int hashCode() {
        int result = getSpeed();
        result = 31 * result + getWeight();
        return result;
    }
}
