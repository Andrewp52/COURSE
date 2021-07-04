package Homeworksix.Animals;

public class Dog extends Animal {
    private static int counter = 0;

    public Dog(){
        super(10,500);
        counter++;
    }

    public Dog(String name) {
        this();
        this.setName(name);
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void run(int distance) {
        if (this.checkRunLimit(distance)){
            System.out.printf("%s runs %d m\r\n", this.getName(), distance);
            return;
        }
        System.out.printf("%s can't run so far\r\n", this.getName());
    }

    @Override
    public void swim(int distance) {
        if (this.checkSwimLimit(distance)){
            System.out.printf("%s swims %d m\r\n", this.getName(), distance);
            return;
        }
        System.out.printf("%s can't swim so far\r\n", this.getName());
    }

}
