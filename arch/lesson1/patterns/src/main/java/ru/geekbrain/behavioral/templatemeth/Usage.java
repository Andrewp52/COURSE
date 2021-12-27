package ru.geekbrain.behavioral.templatemeth;

public class Usage {

    public static void main(String[] args) {
        Builder builder = new AndroidBuilder();

        builder.build();
        System.out.println();

        builder = new IosBuilder();
        builder.build();
    }
}
