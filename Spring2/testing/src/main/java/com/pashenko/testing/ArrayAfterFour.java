package com.pashenko.testing;

import java.util.Arrays;

public class ArrayAfterFour {
    public static int[] getArrayAfterFour(int[] arr) throws RuntimeException{
        int fourIndex;
        if(arr.length == 0 || (fourIndex = getFourIndex(arr)) < 0){
            throw new RuntimeException("Incorrect array data");
        }
        return Arrays.copyOfRange(arr, fourIndex + 1, arr.length);
    }

    private static int getFourIndex(int[] arr){
        for (int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] == 4){
                return i;
            }
        }
        return -1;
    }
}
