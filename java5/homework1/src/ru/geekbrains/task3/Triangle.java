package ru.geekbrains.task3;

public class Triangle extends Figure{
    private final Double ab;
    private final Double bc;
    private final Double ca;

    public Triangle(Double ab, Double bc, Double ca) {
        this.ab = ab;
        this.bc = bc;
        this.ca = ca;
    }

    @Override
    public void showParams() {
        System.out.println(String.format("%s %.2f %.2f %.2f", this.getClass().getSimpleName(), this.ab, this.bc, this.ca));
    }

    @Override
    public void showArea() {
        Double halfPerim = (this.ab + this.bc + this.ca) / 2;
        Double area = Math.sqrt(halfPerim * (halfPerim - this.ab) * (halfPerim - this.bc) * (halfPerim - this.ca));
        System.out.println("Area = " + area);
    }

    @Override
    public void showPerimeter() {
        System.out.println("Perimeter = " + (this.ab + this.bc + this.ca));
    }
}
