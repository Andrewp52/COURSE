package homework1;

public class Human  implements Runner {
    private int maxRunDistance;
    private int maxJumpHeight;

    public Human(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if(distance <= this.maxRunDistance){
            System.out.println("Human runs " + distance);
            return true;
        }
        System.out.println("Human can't run " + distance);
        return false;
    }

    @Override
    public boolean jump(int height) {
        if (height <= this.maxJumpHeight){
            System.out.println("Human jumps " + height);
            return true;
        }
        System.out.println("Human can't jump " + height);
        return false;
    }
}
