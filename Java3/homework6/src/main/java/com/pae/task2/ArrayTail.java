package com.pae.task2;

import java.util.Arrays;
// TASK 2
public class ArrayTail {
    public static int[] getTailAfterFour(int[] arr){
        int index;
        if(arr == null || arr.length == 0 || (index = getTailIndex(arr)) == -1){
            throw new RuntimeException("Invalid input array");
        }
        return Arrays.copyOfRange(arr, index, arr.length);
    }

    private static int getTailIndex(int[] arr){
        for (int i = arr.length - 1; i >= 0 ; i--) {
            if(arr[i] == 4){
                return i + 1;
            }
        }
        return -1;
    }
}
