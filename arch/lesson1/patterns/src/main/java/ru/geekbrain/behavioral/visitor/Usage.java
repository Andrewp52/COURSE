package ru.geekbrain.behavioral.visitor;

public class Usage {
    public static void main(String[] args) {
        Animal monkey = new Monkey();
        Animal lion = new Lion();
        Animal dolphin = new Dolphin();

        AnimalOperation speak = new Speak();
        AnimalOperation jump = new Jump();

        monkey.accept(speak);
        monkey.accept(jump);

        lion.accept(speak);
        lion.accept(jump);

        dolphin.accept(speak);
        dolphin.accept(jump);
    }
}
