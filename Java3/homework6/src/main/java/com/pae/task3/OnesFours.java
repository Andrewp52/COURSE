package com.pae.task3;
// TASK 3
public class OnesFours {

    public static boolean checkOnesFours(int[] arr){
        if(arr == null || arr.length < 2){
            return false;
        }
        boolean one =false, four = false;
        for (int i: arr) {
            if(i != 1 && i != 4){
                return false;
            }
            if(!(one && four)){
                if(i == 1){
                    one = true;
                }
                if(i == 4){
                    four = true;
                }
            }
        }
        return one && four;
    }
}
