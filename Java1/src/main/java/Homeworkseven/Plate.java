package Homeworkseven;

public class Plate {
    private int foodCount;

    public Plate(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getFoodCount() {
        return this.foodCount;
    }

    public void addFood(int quant) {
        if (quant > 0){
            this.foodCount += quant;
        }
    }
    // Decreases food with overdraft prevention. Returns operation state (success / fail).
    public boolean decreaseFood(int quant) {
        if (this.foodCount >= quant) {
            this.foodCount -= quant;
            return true;
        }
        return false;
    }
}
