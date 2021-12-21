package ru.geekbrain.creational.factorymethod;

public class Usage {
    public static void main(String[] args) {
        HiringManager devManager = new DevelopmentManager();
        HiringManager marManager = new MarketingManager();

        System.out.println(devManager.takeInterview());
        System.out.println(marManager.takeInterview());

    }

}
