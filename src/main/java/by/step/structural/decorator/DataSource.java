package by.step.structural.decorator;

public interface DataSource {
    void writeData(String data);

    String readData();
}