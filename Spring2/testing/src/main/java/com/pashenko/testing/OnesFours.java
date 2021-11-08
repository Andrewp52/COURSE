package com.pashenko.testing;

public class OnesFours {
    public static boolean checkOnesFoursArray(int[] arr){
        boolean one = false, four = false;
        for (int i = 0; i < arr.length; i++){
            one = arr[i] == 1 || one;
            four = arr[i] == 4 || four;
            if(one && four){
                return true;
            }
        }
        return false;
    }
}
