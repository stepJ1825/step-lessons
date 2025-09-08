package by.step.creational.builder;

// Класс, который мы будем строить
class Computer {
    // Необходимые поля (можно сделать приватными)
    private String cpu;
    private int ram;
    private String storage;
    private boolean hasGraphicsCard; // Пример опционального компонента

    // Приватный конструктор, который принимает Builder
    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    // Методы для доступа к полям (сеттеры не нужны, если объект неизменяемый)
    public String getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public boolean hasGraphicsCard() {
        return hasGraphicsCard;
    }

    // Вложенный статический класс Builder
    public static class ComputerBuilder {
        // Те же поля, что и у внешнего класса
        private String cpu;
        private int ram;
        private String storage;
        private boolean hasGraphicsCard;

        // Методы для установки параметров (возвращают сам Builder)
        public ComputerBuilder setCpu(String cpu) {
            this.cpu = cpu;
            return this; // Возвращаем себя для цепочки вызовов
        }

        public ComputerBuilder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public ComputerBuilder setHasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }

        // Метод build() для создания объекта Computer
        public Computer build() {
            return new Computer(this);
        }
    }
}
