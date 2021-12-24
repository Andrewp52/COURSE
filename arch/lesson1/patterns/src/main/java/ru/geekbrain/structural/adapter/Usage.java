package ru.geekbrain.structural.adapter;

public class Usage {
    public static void main(String[] args) {
        Hunter hunter = new Hunter();
        // Base
        System.out.println(hunter.hunt(new AsianLion()));
        System.out.println(hunter.hunt(new AfricanLion()));

        // Adapted
        WildDogAdapter dogAdapter = new WildDogAdapter(new WildDog());
        System.out.println(hunter.hunt(dogAdapter));
    }

}
