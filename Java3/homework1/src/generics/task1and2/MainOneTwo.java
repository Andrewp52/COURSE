package generics.task1and2;
import java.util.Arrays;

import static generics.task1and2.Tasks.*;

// Test for tasks 1, 2

public class MainOneTwo {
    static String[] arrStr = {"A", "B", "C", "D"};
    static Integer[] arrInt = {1, 2, 3, 4};

    public static void main(String[] args) {
        exchange(arrStr, 1,3);                        // Test of exchanging the string array
        System.out.println(Arrays.toString(arrStr));

        exchange(arrInt, 0,2);                        // Test of exchanging the objects array
        System.out.println(Arrays.toString(arrInt));
        System.out.println("-----------------------------");

        System.out.println("ArrayLists");
        System.out.println(getArrayList(arrStr));              // Arraylist conversion and print.
        System.out.println(getArrayList(arrInt));
    }
}
