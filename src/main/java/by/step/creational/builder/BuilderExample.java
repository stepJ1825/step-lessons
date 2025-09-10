package by.step.creational.builder;

public class BuilderExample {
    public static void main(String[] args) {
        // Создаем объект Computer, используя Builder
        Computer gamingPC = new Computer.ComputerBuilder()
                .setCpu("Intel i9")
                .setRam(32)
                .setStorage("1TB SSD")
                .setHasGraphicsCard(true)
                .build(); // Вызываем build() для получения готового объекта

        Computer officePC = new Computer.ComputerBuilder()
                .setCpu("Intel i5")
//                .setRam(16)
//                .setStorage("512GB SSD")
                // hasGraphicsCard по умолчанию false
                .build();

        System.out.println("Игровой ПК: " + gamingPC.getCpu() + ", " + gamingPC.getRam() + "GB RAM");
        System.out.println("Офисный ПК: " + officePC.getCpu() + ", " + officePC.getRam() + "GB RAM");

        ComputerWithLombok computerWithLombok =
                ComputerWithLombok
                        .builder()
                        .setCpu("Intel i9")
                        .setRam(32)
                        .setStorage("1TB SSD")
                        .setHasGraphicsCard(true)
                        .build();
    }
}

