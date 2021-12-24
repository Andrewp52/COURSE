package ru.geekbrain.structural.fasade;

public class Usage {
    public static void main(String[] args) {
        ComputerFasade fasade = new ComputerFasade(new Computer());
        fasade.turnOn();
        fasade.turnOff();
    }
}
