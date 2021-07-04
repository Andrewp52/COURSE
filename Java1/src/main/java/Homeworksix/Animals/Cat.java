package Homeworksix.Animals;

public class Cat extends Animal {
    private static int counter = 0;

    public Cat(){
        super(0, 200);
        counter++;
    }

    public Cat(String name) {
        this();
        this.setName(name);
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public void run(int distance) {
        if(this.checkRunLimit(distance)) {
            System.out.printf("%s runs %d m\r\n",this.getName(), distance);
            return;
        }
        System.out.printf("%s can't run so far\r\n", this.getName());
    }

    @Override
    public void swim(int distance) {
        System.out.println("Cat can't swim");
    }

}
