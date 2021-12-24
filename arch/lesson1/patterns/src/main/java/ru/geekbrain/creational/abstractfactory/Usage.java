package ru.geekbrain.creational.abstractfactory;

public class Usage {
    public static void main(String[] args) {
        // Wooden
        DoorFactory factory = new WoodenDoorFactory();
        Door door = factory.makeDoor();
        DoorFittingExpert expert = factory.makeDoorFittingExpert();

        System.out.println(door.getDescription());
        System.out.println(expert.getDescription());

        // Iron
        factory = new IronDoorFactory();
        door = factory.makeDoor();
        expert = factory.makeDoorFittingExpert();

        System.out.println(door.getDescription());
        System.out.println(expert.getDescription());

    }
}
