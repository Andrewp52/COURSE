package ru.geekbrain.structural.proxy;

public class Usage {
    public static void main(String[] args) {
        // Base class
        Door door = new LabDoor();
        door.open();
        door.close();
        System.out.println();

        // With proxy
        SecuredDoor secured = new SecuredDoor(door);
        secured.open("wrong");
        secured.open("$ecr@t");
        secured.close();
    }
}
