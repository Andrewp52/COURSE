package ru.geekbrain.behavioral.command;

public class Usage {
    public static void main(String[] args) {
        Bulb bulb = new Bulb();
        TurnOn turnOn = new TurnOn(bulb);
        TurnOff turnOff = new TurnOff(bulb);

        RemoteControl remote = new RemoteControl();
        remote.submit(turnOn); // Bulb has been lit!
        remote.submit(turnOff); // Darkness!
    }
}
