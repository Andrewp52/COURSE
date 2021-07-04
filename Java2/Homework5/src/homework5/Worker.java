package homework5;

public class Worker extends Thread{
    private float[] part;
    private final int offset;                                          // Original array index presenting by the part[0]

    public Worker(float[] part, int offset) {
        this.part = part;
        this.offset = offset;
    }

    @Override
    public void run() {
        int k;
        for (int i = 0; i < part.length; i++) {
            k = offset + i;
            part[i] = (float)(part[i] * Math.sin(0.2f + k / 5) * Math.cos(0.2f + k / 5) * Math.cos(0.4f + k / 2));
        }
    }

    public float[] getPart() {
        return this.part;
    }

    public int getOffset() {
        return this.offset;
    }
}
