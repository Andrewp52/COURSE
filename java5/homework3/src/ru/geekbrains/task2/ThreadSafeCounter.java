package ru.geekbrains.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeCounter {
    Lock lock = new ReentrantLock();
    int counter;

    public void increment(){
        lock.lock();
        counter++;
        lock.unlock();
    }

    public void decrement(){
        lock.lock();
        counter--;
        lock.unlock();
    }

    public int read(){
        return counter;
    }
}
