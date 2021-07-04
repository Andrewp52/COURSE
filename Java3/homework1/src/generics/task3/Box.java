package generics.task3;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit>{
    private List<T> fruits = new ArrayList<>();

    // subtask: D
    public float getWeight(){
        float res = 0.0f;
        for (T item : this.fruits) {
            res += item.getWeight();
        }
        return res;
    }

    // subtask: E
    public boolean compare(Box<?> b){
        if(b == null){
            return false;
        }
        return b == this || Float.compare(this.getWeight(), b.getWeight()) == 0;
    }

    // subtask: F
    public void moveFruitsTo(Box<T> box){
        if(box == null || box == this || this.fruits.size() == 0) {
            return;
        }
        System.out.printf("Moving %ss: %d\r\n", fruits.get(0).getClass().getSimpleName() , fruits.size());
        box.getFruits().addAll(this.fruits);
        this.fruits.clear();
    }

    // subtask G
    public void addFruit(T item){
        if (item != null){
            fruits.add(item);
        }
    }

    public List<T> getFruits() {
        return fruits;
    }
}
