package ru.geekbrain.behavioral.memento;

public class Usage {
    public static void main(String[] args) {
        Editor editor = new Editor();

        editor.type("This is the first sentence.");
        editor.type("This is second.");

        // Save point
        EditorMemento saved = editor.save();

        editor.type("And this is third.");
        System.out.println(editor.getContent());

        // Rollback
        editor.restore(saved);
        System.out.println(editor.getContent());
    }
}
