package ru.geekbrain.structural.decorator;

public class WhipCoffee implements Coffee{
    private Coffee coffee;

    public WhipCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public float getCost() {
        return this.coffee.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", whip";
    }
}
