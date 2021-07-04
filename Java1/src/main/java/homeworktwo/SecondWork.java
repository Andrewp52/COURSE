package homeworktwo;

import java.util.Arrays;

public class SecondWork {
    public static void main(String[] args) {
        // Task # 1
        int [] arrA = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arrA.length; i++) {
            if(arrA[i] == 0){
                arrA[i] = 1;
            } else {
                arrA[i] = 0;
            }
        }

        // Task # 2
        int [] arrB = new int[8];
        for (int i = 1; i < arrB.length ; i++) {
            arrB[i] = arrB[i - 1] + 3;
        }

        // Task # 3
        int[] arrC = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arrC.length; i++) {
            if (arrC[i] < 6){
                arrC[i] *= 2;
            }
        }

        // Task # 4
        int [][] arrD = new int[8][8];
        for (int i = 0; i < arrD.length; i++){
            arrD[i][i] = arrD[i][arrD.length - i - 1] = 1;
        }

        // Task # 5
        int[] arrE = {5, 3, 2, 11, 4, 1, 7, 15};
        int min = arrE[0];
        int max = arrE[0];
        for (int i = 1; i < arrE.length; i++) {
            if(arrE[i] < min){
                min = arrE[i];
                continue;
            }
            if (arrE[i] > max){
                max = arrE[i];
            }
        }

    }

    // Task # 6
    private static boolean checkBalance(int[] arr){
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < arr.length - 1 ; i++) {
            sumLeft += arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                sumRight += arr[j];
            }
            if(sumLeft == sumRight){
                return true;
            }
            sumRight = 0;
        }
        return false;
    }

    // Task # 7
    private static void shiftArray(int[] arr, int numPos){
        for (int i = 0; i < Math.abs(numPos); i++) {
            int temp;
            if(numPos > 0){
                temp = arr[arr.length - 1];
                for (int j = arr.length - 1; j > 0 ; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = temp;
            } else {
                temp = arr[0];
                for (int j = 0; j < arr.length - 1 ; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length - 1] = temp;
            }
        }
    }
    
}
