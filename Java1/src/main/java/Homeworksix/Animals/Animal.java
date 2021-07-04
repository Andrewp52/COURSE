package Homeworksix.Animals;

public abstract class Animal {
    private static int counter = 0;
    private final int maxSwimDistance, maxRunDistance;
    private String name = "Nomad";

    public Animal(int maxSwimDistance, int maxRunDistance) {
        this.maxSwimDistance = maxSwimDistance;
        this.maxRunDistance = maxRunDistance;
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);

    final boolean checkRunLimit(int distance){
        return distance <= this.maxRunDistance;
    }

    final boolean checkSwimLimit(int distance){
        return distance <= this.maxSwimDistance;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String name) {
        this.name = name;
    }
}
