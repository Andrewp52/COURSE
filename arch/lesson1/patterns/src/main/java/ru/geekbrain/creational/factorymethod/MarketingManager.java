package ru.geekbrain.creational.factorymethod;

public class MarketingManager extends HiringManager{

    @Override
    protected Interviewer getInterviewer(){
        return new CommunityExecutive();
    }
}
