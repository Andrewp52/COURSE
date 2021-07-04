package homework1;

public class Robot implements Runner {
    private int maxRunDistance;
    private int maxJumpHeight;

    public Robot(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public boolean run(int distance) {
        if(distance <= this.maxRunDistance){
            System.out.println("Robot runs " + distance);
            return true;
        }
        System.out.println("Robot can't run " + distance);
        return false;
    }

    @Override
    public boolean jump(int height) {
        if (height <= this.maxJumpHeight){
            System.out.println("Robot jumps " + height);
            return true;
        }
        System.out.println("Robot can't jump " + height);
        return false;
    }

}
