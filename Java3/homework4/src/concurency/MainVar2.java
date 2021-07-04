package concurency;

public class MainVar2 {                                                                    // TASK 1 Variant 2
    static final int cycles = 5;
    static final Worker worker = new Worker();
    public static void main(String[] args) {
        System.out.println("Variant 2 - single method");

        class Routine implements Runnable {
            String s;
            public Routine(String s){
                this.s = s;
            }
            @Override
            public void run() {
                try {
                    for (int i = 0; i < cycles; i++) {
                        worker.print(s);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        new Thread(new Routine("A")).start();
        new Thread(new Routine("B")).start();
        new Thread(new Routine("C")).start();
    }
}
