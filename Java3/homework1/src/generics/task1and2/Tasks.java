package generics.task1and2;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

    // Task : 1
    static <T> void exchange(T[] arr, int idx1, int idx2){
        if (arr == null){
            return;
        }
        T temp = arr[idx1];
         arr[idx1] = arr[idx2];
         arr[idx2] = temp;
    }

    // Task : 2
    static <E> List<E> getArrayList(E[] arr){
        if (arr == null){
            return null;
        }
        ArrayList<E> res = new ArrayList<>();
        for (E e : arr) {
             res.add(e);
        }
        return res;
    }
}
