package ru.geekbrains.taskadd.reverselist;

public class SLMain {

    public static void main(String[] args) {
        SingleLinkList<String> list = new SingleLinkList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        list.forEach(System.out::println);
        System.out.println();
        list.reverse();
        list.forEach(System.out::println);

    }
}
