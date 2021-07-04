package Homeworkseven;

public class Cat {
    private String name;
    private int appetite;
    private boolean wellFed = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate){
        if(plate.decreaseFood(appetite)){
            this.wellFed = true;
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean isWellFed() {
        return this.wellFed;
    }
}
