package ru.geekbrains.algos.homework2;

public class SortTest {
    public static void main(String[] args) {

        int[] testArray = new int[]{7,5,6,4,-3,-1,2};
        MyArray arr = new MyArray(testArray.clone());
        arr.sortBubble();   // 28 iters. (Optimized)     - O(n^2)
        arr = new MyArray(testArray.clone());
        arr.sortSelect();   // 28 iters.                 - O(n^2)
        arr = new MyArray(testArray.clone());
        arr.sortInsert();   // 25 iters                  - O(n^2)
        arr = new MyArray(testArray.clone());
        arr.sortCount();    // 18 iters                  - O(n+k)
        arr.display();

    }
}
