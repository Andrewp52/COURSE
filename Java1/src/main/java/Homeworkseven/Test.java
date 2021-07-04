package Homeworkseven;

public class Test {
    public static void main(String[] args) {
        Plate plate = new Plate(40);
        Cat[] cats = new Cat[]{
                new Cat("Murzik", 13),
                new Cat("Barsik",10),
                new Cat("Vasily", 14),
                new Cat("Pirate", 25)
        };

        System.out.printf("%d food units on the plate\r\n", plate.getFoodCount());

        for(Cat c : cats) {
            c.eat(plate);
            System.out.printf("%s is %s. %d food units on the plate\r\n",
                    c.getName(),
                    c.isWellFed() ? "well-fed" : "hungry",
                    plate.getFoodCount()
            );
        }
    }
}
