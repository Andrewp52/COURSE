package ru.geekbrain.behavioral.strategy;

public class BubbleSortStrategy implements SortStrategy{
    @Override
    public int[] sort(int[] dataset) {
        System.out.println("Sorting bubble-sort");
        // Sorting
        return dataset;
    }
}
