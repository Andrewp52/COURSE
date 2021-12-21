package ru.geekbrain.creational.factory;

public class Usage {
    public static void main(String[] args) {
        Door door = DoorFactory.getDoor(1.5f, 2.5f);
        System.out.printf("Door: %s %s X %s", door.getClass().getSimpleName(), door.getWidth(), door.getHeight());
    }
}
