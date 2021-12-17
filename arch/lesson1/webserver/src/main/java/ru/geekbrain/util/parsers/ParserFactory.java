package ru.geekbrain.util.parsers;

public class ParserFactory {
    public static Parser getParser(){
        return new SimpleParser();
    }
}
