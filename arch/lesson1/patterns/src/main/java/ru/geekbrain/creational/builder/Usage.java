package ru.geekbrain.creational.builder;

public class Usage {

    public static void main(String[] args) {
        Burger burger = new Burger.Builder()
                .withSize(10)
                .withCheese(false)
                .withLettuce(false)
                .withPepperoni(true)
                .withTomato(true)
                .build();

        System.out.println(burger.toString());
    }
}
