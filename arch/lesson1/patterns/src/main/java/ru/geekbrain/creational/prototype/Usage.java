package ru.geekbrain.creational.prototype;

public class Usage {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("dolly", "original");
        Sheep clone = sheep.clone();

        clone.setCategory("cloned sheep");

        System.out.println(sheep.getName() + " " + sheep.getCategory());
        System.out.println(clone.getName() + " " + clone.getCategory());
    }
}
