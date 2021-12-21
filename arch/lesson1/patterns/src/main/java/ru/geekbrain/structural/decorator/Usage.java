package ru.geekbrain.structural.decorator;

public class Usage {
    public static void main(String[] args) {
        Coffee coffee = new VanillaCoffee(new MilkCoffee(new SimpleCoffee()));

        System.out.println(coffee.getCost());
        System.out.println(coffee.getDescription());
    }
}
