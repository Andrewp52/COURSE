package ru.geekbrains.task2;

public class Engine {
    private String name;

    public Engine(String name) {
        this.name = name;
    }

    Engine getCopy() {
        return new Engine(this.name);
    }

    public String getName() {
        return name;
    }
}
