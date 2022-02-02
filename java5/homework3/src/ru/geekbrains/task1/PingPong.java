package ru.geekbrains.task1;

public class PingPong {
    private boolean pong;

    public synchronized void ping() {
        while (pong){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PING");
        pong = true;
        notifyAll();
    }

    public synchronized void pong(){
        while (!pong){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("PONG\n");
        pong = false;
        notifyAll();
    }
}
