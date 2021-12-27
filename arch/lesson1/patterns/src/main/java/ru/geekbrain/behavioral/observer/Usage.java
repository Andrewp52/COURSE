package ru.geekbrain.behavioral.observer;

public class Usage {
    public static void main(String[] args) {
        Observer johnDoe = new JobSeeker("John Doe");
        Observer janeDoe = new JobSeeker("Jane Doe");

// Create publisher and attach subscribers
        EmploymentAgency agency = new EmploymentAgency();
        agency.attach(johnDoe);
        agency.attach(janeDoe);

// Add a new job and see if subscribers get notified
        agency.addJob(new JobPost("Software Engineer"));
    }
}
