package concurency;

public class Worker {
    private static boolean lock1 = false;     // false - unlocked
    private static boolean lock2 = true;      // true - locked

// Variant 1
    public synchronized void printA() throws InterruptedException {
        while (lock1){
            wait();
        }
        System.out.print("A");
        lock1 = true;
        lock2 = false;
        notifyAll();
    }

    public synchronized void printB() throws InterruptedException {
        while (lock2){
            wait();
        }
        System.out.print("B");
        lock2 = lock1 = true;
        notifyAll();
    }

    public synchronized void printC() throws InterruptedException {
        while (!lock1 || !lock2){
            wait();
        }
        System.out.print("C");
        lock1 = false;
        lock2 = true;
        notifyAll();
    }

// Variant 2
    public synchronized void print(String s) throws InterruptedException {
        if(s.equals("A")){
            while (lock1){
                wait();
            }
            lock1 = true;
            lock2 = false;
        } else if (s.equals("B")){
            while (lock2){
                wait();
            }
            lock2 = lock1 = true;
        } else if (s.equals("C")){
            while (!lock1 || !lock2){
                wait();
            }
            lock1 = false;
            lock2 = true;
        } else {
            throw new RuntimeException("Unexpected value: " + s);
        }
        System.out.print(s);
        notifyAll();
    }
}
