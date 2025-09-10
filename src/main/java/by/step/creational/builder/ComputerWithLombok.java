package by.step.creational.builder;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(setterPrefix = "set",builderClassName = "MyComputerBuilder")
class ComputerWithLombok {
    // Необходимые поля (можно сделать приватными)
    private String cpu;
    private int ram;
    private String storage;
    private boolean hasGraphicsCard; // Пример опционального компонента

}
