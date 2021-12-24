package ru.geekbrain.creational.prototype;

public class Sheep {
    private String name;
    private String category;

    public Sheep(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Sheep clone(){
        return new Sheep(this.name, this.category);
    }
}
