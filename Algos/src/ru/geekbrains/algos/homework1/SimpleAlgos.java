package ru.geekbrains.algos.homework1;

public class SimpleAlgos {

    public static void main(String[] args) {
        System.out.println(pow(2, 16));
        System.out.println(powWithParity(2, 16));
        System.out.println(sumProgression(0, 100));
    }

    // TASK 1: Simple powering
    private static int pow(int x, int p){
        int res = x;
        for (int i = 1; i < p; i++) {
            res *= x;
        }
        return res;
    }

    // TASK 2: Powering with parity checking
    private static int powWithParity(int x, int p){
        return p % 2 == 0 ? powWithParity(x * x, p / 2) : pow(x, p);
    }

    // TASK 3: Sum of progression with step 1
    private static int sumProgression(int start, int end){
        return (end + 1 - start) * (start + end) / 2;
    }
}


