package ru.geekbrain.structural.adapter;

public class WildDogAdapter implements Lion{
    private WildDog dog;

    public WildDogAdapter(WildDog dog) {
        this.dog = dog;
    }

    @Override
    public String roar() {
        return dog.bark();
    }
}
