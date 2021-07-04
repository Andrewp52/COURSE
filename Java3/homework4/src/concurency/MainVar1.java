package concurency;

public class MainVar1 {                                                                         // TASK 1 Variant 1
    static final int cycles = 5;
    static Worker worker = new Worker();
    public static void main(String[] args) {
        System.out.println("Variant 1 - multiple methods");

        new Thread(() -> {                              // Print A
            try {
                for (int i = 0; i < cycles; i++) {
                    worker.printA();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {                              // Print B
            try {
                for (int i = 0; i < cycles; i++) {
                    worker.printB();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {                              // Print C
            try {
                for (int i = 0; i < cycles; i++) {
                    worker.printC();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
