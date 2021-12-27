package ru.geekbrain.behavioral.command;

public interface Command {
    void execute();
    void undo();
    void redo();
}
