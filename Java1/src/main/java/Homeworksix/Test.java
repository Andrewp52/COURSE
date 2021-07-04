package Homeworksix;

import Homeworksix.Animals.*;

public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat();                        // Cat as a child type
        Animal barsik = new Cat("Barsik");
        Animal sharik = new Dog("Sharik");

        barsik.run(95);
        barsik.run(205);
        barsik.swim(1);
        cat.run(10);
        System.out.println();

        sharik.run(300);
        sharik.run(505);
        sharik.swim(5);
        sharik.swim(11);
        System.out.println();

        System.out.println("Animals - " + Animal.getCounter());     // 3
        System.out.println("Dogs - " + Dog.getCounter());           // 1
        System.out.println("Cats - " + Cat.getCounter());           // 2
    }
}
