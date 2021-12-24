package ru.geekbrain.behavioral.observer;

import java.util.LinkedList;
import java.util.List;

public class EmploymentAgency {
    private List<Observer> observers = new LinkedList<>();

    public void attach(Observer observer)
    {
        this.observers.add(observer);
    }

    public void addJob(JobPost jobPosting)
    {
        this.notify(jobPosting);
    }

    private void notify(JobPost jobPosting) {
        observers.forEach(observer -> observer.onJobPosted(jobPosting));
    }

}
