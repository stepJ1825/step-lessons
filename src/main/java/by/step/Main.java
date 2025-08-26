package by.step;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Car randomCar = getRandomCar();
        PriceCalculator calculator = null;
        if (randomCar instanceof Audi)  {
            calculator = new AudiPriceCalculator();
        } else if (randomCar instanceof BMW) {
            calculator = new BMWPriceCalculator();
        } else if (randomCar instanceof Porsche) {
            calculator = new PorschePriceCalculator();
        } else throw new Exception("There is no any car calculator.");

        double randomCarPrice = calculator.calculatePrice(randomCar);
        System.out.println(randomCarPrice);
    }

    private static Car getRandomCar() {
        Random random = new Random();
        int i = random.nextInt(0, 3);
        Car[] cars = new Car[]{new Audi(), new BMW(), new Porsche()};
        return cars[i];
    }
}
