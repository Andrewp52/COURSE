package homework1;

public class Cat implements Runner {
    private int maxRunDistance;
    private int maxJumpHeight;

    public Cat(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if(distance <= this.maxRunDistance){
            System.out.println("Cat runs " + distance);
            return true;
        }
        System.out.println("Cat can't run " + distance);
        return false;
    }


    @Override
    public boolean jump(int height) {
        if (height <= this.maxJumpHeight){
            System.out.println("Cat jumps " + height);
            return true;
        }
        System.out.println("Cat can't jump " + height);
        return false;
    }

}
