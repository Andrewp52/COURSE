package ru.geekbrains.task1;

public class PPMain {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        Thread t1 = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(500);
                    pingPong.ping();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(500);
                    pingPong.pong();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
