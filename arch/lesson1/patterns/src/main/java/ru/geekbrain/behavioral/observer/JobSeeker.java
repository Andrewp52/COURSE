package ru.geekbrain.behavioral.observer;

public class JobSeeker implements Observer{
    private String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void onJobPosted(JobPost job)
    {
        System.out.printf("Hi %s ! New job posted: %s\r\n", this.name, job.getTitle());
    }
}
