package ru.geekbrain.creational.factorymethod;

public abstract class HiringManager {

    protected abstract Interviewer getInterviewer();

    public String takeInterview(){
        Interviewer interviewer = this.getInterviewer();
        return interviewer.askQuestions();
    }
}
