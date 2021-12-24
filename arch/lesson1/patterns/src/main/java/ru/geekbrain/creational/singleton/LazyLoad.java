package ru.geekbrain.creational.singleton;

public class LazyLoad {
    private static LazyLoad instance;

    private LazyLoad(){
    }

    public static LazyLoad getInstance(){
        if(instance == null){
            instance = new LazyLoad();
        }
        return instance;
    }
}
