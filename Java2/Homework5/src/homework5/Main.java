package homework5;

import java.util.Arrays;

public class Main {
    static final int THREADS = 12;                                                 // My 6 phys cores - 12 threads
    static final int SIZE = 10000000;

    public static void main(String[] args) throws InterruptedException {
        singleThreadMethod();
        multiThreadMethod(THREADS);
    }

    private static void singleThreadMethod(){
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Single thread time ms: " + (System.currentTimeMillis() - startTime));
    }

    private static void multiThreadMethod(int threads) throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1f);
        Worker[] running = new Worker[threads];                                   // Threads array to observe their states
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            running[i] = new Worker(cutPart(arr, threads, i), (arr.length / threads) * i);
            running[i].start();
        }
        for (Worker w : running) {
            w.join();
            pushPart(arr, w.getPart(), w.getOffset());
        }
        System.out.println("Multi-thread time ms: " + (System.currentTimeMillis() - startTime));
    }

    private static float[] cutPart(float[] orig, int partsCount, int partNumber){
        int offset = orig.length / partsCount * partNumber;
        return partNumber < partsCount - 1 ?                                        // If is last part, return all to end
                Arrays.copyOfRange(orig, offset, offset + orig.length / partsCount) :
                Arrays.copyOfRange(orig, offset, orig.length);
    }

    private static void pushPart(float[] orig, float[] part, int offset){
        System.arraycopy(part, 0, orig, offset, part.length);
    }

}
