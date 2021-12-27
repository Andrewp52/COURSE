package ru.geekbrain.behavioral.strategy;

public class Usage {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        Sorter sorter = new Sorter(new BubbleSortStrategy());
        sorter.sort(arr);

        sorter = new Sorter(new QuickSortStrategy());
        sorter.sort(arr);
    }
}
