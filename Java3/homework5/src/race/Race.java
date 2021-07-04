package race;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Race {
    private ArrayList<Stage> stages;
    private final CyclicBarrier barrier;
    private final CountDownLatch finish;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(int carsCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.barrier = new CyclicBarrier(carsCount + 1);
        this.finish = new CountDownLatch(carsCount);
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public CountDownLatch getFinish() {
        return finish;
    }
}
