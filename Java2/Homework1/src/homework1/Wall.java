package homework1;

public class Wall implements Obstacle{
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean interact(Runner runner) {
        return runner.jump(this.height);
    }
}
