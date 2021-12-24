package ru.geekbrain.creational.singleton;

public class EagerLoad {
    private static final EagerLoad instance = new EagerLoad();

    private EagerLoad(){
    }

    public static EagerLoad getInstance(){
        return instance;
    }
}
