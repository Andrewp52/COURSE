package ru.geekbrains.task3;

public class Circle extends Figure{
    private final Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    @Override
    public void showParams() {
        System.out.println(this.getClass().getSimpleName() + " " + this.radius);
    }

    @Override
    public void showArea() {
        System.out.println("Area = " + (Math.PI * Math.pow(this.radius, 2)));
    }

    @Override
    public void showPerimeter() {
        System.out.println("Perimeter = " + (2 * Math.PI * this.radius));
    }
}
