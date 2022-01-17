package ru.geekbrains.task2;

public class Lorry extends Car implements OpenCloseable{

    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    @Override
    public void open() {
        System.out.println("Lorry is open");
    }

    @Override
    public void close() {
        System.out.println("Lorry is closed");
    }

    @Override
    public void move(){
        this.start();
        System.out.println("Car is moving");
    }

    @Override
    public void stop(){
        System.out.println("Car is stop");
    }
}
