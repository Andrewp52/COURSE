package race;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Car implements Runnable {
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static int PLACE;           // Finish counter
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
        PLACE = 1;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            race.getBarrier().await();                  // Waiting for start.
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
                                                        // Determining finish position.
        rwLock.writeLock().lock();
        System.out.printf("%s%s %d - место.\n", this.name, PLACE == 1 ? " Победа!" : "", PLACE++);
        rwLock.writeLock().unlock();
        race.getFinish().countDown();
    }
}
