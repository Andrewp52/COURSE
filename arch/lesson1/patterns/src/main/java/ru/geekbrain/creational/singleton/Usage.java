package ru.geekbrain.creational.singleton;

public class Usage {
    public static void main(String[] args) {
        // Always the same instance
        EagerLoad ins1 = EagerLoad.getInstance();
        EagerLoad ins2 = EagerLoad.getInstance();
        System.out.println(ins1 == ins2);
    }
}
