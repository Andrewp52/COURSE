package ru.geekbrains.task3;

public class Square extends Figure{
    private final Double ab;
    private final Double bc;

    public Square(Double ab, Double bc) {
        this.ab = ab;
        this.bc = bc;
    }

    @Override
    public void showParams() {
        System.out.println(String.format("%s %.2f %.2f", this.getClass().getSimpleName(), this.ab, this.bc));
    }

    @Override
    public void showArea() {
        System.out.println("Area = " + (this.ab * this.bc));
    }

    @Override
    public void showPerimeter() {
        System.out.println("Perimeter = " + (2 * (this.ab + this.bc)));
    }
}
