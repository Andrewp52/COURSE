package ru.geekbrain.creational.factory;

public class DoorFactory {
    public static Door getDoor(float width, float height){
        return new WoodenDoor(width, height);
    }
}
