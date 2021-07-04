package race;
import java.util.concurrent.Semaphore;
public class Tunnel extends Stage {
    private Semaphore semaphore;
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    public Tunnel(int bandwidth){
        this();
        this.semaphore = new Semaphore(bandwidth);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                this.semaphore.acquire();                   // Seizing the semaphore
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                this.semaphore.release();                   // Releasing the semaphore after the stage is passed.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
