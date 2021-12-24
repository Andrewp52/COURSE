package ru.geekbrain.behavioral.strategy;

public class Sorter {
    SortStrategy sorter;

    public Sorter(SortStrategy sorter) {
        this.sorter = sorter;
    }

    public int[] sort(int[] dataset) {
        return sorter.sort(dataset);
    }
}
