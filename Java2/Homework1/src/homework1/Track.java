package homework1;

public class Track implements Obstacle{
    private int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public boolean interact(Runner runner) {
        return runner.run(this.length);
    }
}
