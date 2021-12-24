package ru.geekbrain.creational.factorymethod;

public class DevelopmentManager extends HiringManager{

    @Override
    protected Interviewer getInterviewer(){
        return new Developer();
    }
}
