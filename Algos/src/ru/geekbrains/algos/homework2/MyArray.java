package ru.geekbrains.algos.homework2;

public class MyArray {
    private int[] arr;
    private int capacity;

    //new int[5];
    public MyArray(int size) {
        this.capacity = 0;
        this.arr = new int[size];
    }

    // = {1,2,3,4,5};
    public MyArray(int[] init) {
        this.capacity = init.length;
        this.arr = init;
    }

    void display() {
        for (int i = 0; i < this.capacity; ++i) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    public int get(int idx) {
        return arr[idx];
    }

    public void set(int value, int idx) {
        arr[idx] = value;
    }

    boolean delete(int value) {
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] == value) {
                System.arraycopy(this.arr, i + 1, this.arr, i, this.capacity - i - 1);
                --capacity;
                return true;
            }
        }
        return false;
    }

    void append(int value) {
        if (this.capacity == this.arr.length) {
            int[] old = this.arr;
            this.arr = new int[old.length * 2];
            System.arraycopy(old, 0, arr, 0, old.length);
        }
        this.arr[this.capacity++] = value;
    }

    public boolean isInArray(int value) { // O(n)
        for (int i = 0; i < this.capacity; i++)
            if (this.arr[i] == value)
                return true;
        return false;
    }

    //O(log(N))
    public boolean hasValue(int value) {
        int low = 0;
        int high = this.capacity - 1;
        int mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (value == this.arr[mid]) {
                return true;
            } else {
                if (value < this.arr[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }

    private void swap(int a, int b) {
        int tmp = this.arr[a];
        this.arr[a] = this.arr[b];
        this.arr[b] = tmp;
    }

    // O(n^2)
    public void sortBubble() {
        int iterations = 0;
        for (int iter = 0; iter < capacity; iter++){
            iterations++;
            for (int idx = 0; idx < capacity - 1 - iter; idx++) { // decrease size of inner loop on each outer iteration
                iterations++;
                if (this.arr[idx] > this.arr[idx + 1])
                    swap(idx, idx + 1);
            }
        }
        System.out.printf("Bubble: Array with %d elements - %d iterations\n", capacity, iterations);
    }

    // O(n^2)
    public void sortSelect() {
        int iterations = 0;
        for (int idx = 0; idx < capacity; idx++) {
            iterations++;
            int curr = idx;
            for (int srch = idx + 1; srch < capacity; srch++){
                iterations++;
                if (this.arr[srch] < this.arr[curr])
                    curr = srch;
            }
            if (curr != idx)
                swap(idx, curr);
        }
        System.out.printf("Selection: Array with %d elements - %d iterations\n", capacity, iterations);
    }

    // O(n^2)
    public void sortInsert() {
        int iterations = 0;
        for (int curr = 1; curr < capacity; curr++) {
            iterations++;
            int temp = this.arr[curr];
            int move = curr;
            while (move > 0 && this.arr[move - 1] >= temp) {
                iterations++;
                this.arr[move] = this.arr[move - 1];
                move--;
            }
            this.arr[move] = temp;
        }
        System.out.printf("Insertion: Array with %d elements - %d iterations\n", capacity, iterations);
    }

    // HOMEWORK TASKS 1, 2, 3, 5

    // task 1
    public void deleteAll(int value){
        int count = 0;
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if(arr[i] == value){
                if(index < 0){
                    index = i;
                }
                count++;
                continue;
            }
            if(index >= 0){
                arr[index] = arr[i];
                index++;
            }
        }
        capacity -=count;
    }

    // task 2
    public void deleteAll(){
        capacity = 0;
        if(arr.length > 20){       // shrink array (20 - cuz why not?)
            arr = new int[arr.length / 2];
        }
    }

    // task 3 (var 1)
    void insert1(int idx, int value){
        if(arr.length == capacity){
            expand();
        }
        for (int i = capacity; i > idx ; i--) {
            arr[i] = arr[i - 1];
        }
        arr[idx] = value;
        capacity++;
    }

    //task 3 (var 2)
    void insert2(int idx, int value){
        if(arr.length == capacity){
            expand();
        }
        System.arraycopy(arr, idx, arr, idx + 1, capacity - idx);
        arr[idx] = value;
        capacity++;
    }

    //task 5
    // O(n+k)
    // n - number of elements, k - biggest value
    void sortCount(){
        int min = 0;
        int max = 0;
        int[] countedArray;
        int iterations = 0;
        for (int i = 0; i < capacity; i++) {
            if (arr[i] < min)
                min = arr[i];
            if (arr[i] > max)
                max = arr[i];
        }
        if(min < 0)
            min *= -1;

        countedArray = new int[min + max + 1];
        for (int i = 0; i < capacity; i++)                 // Counting
        {
            iterations++;
            countedArray[min + arr[i]]++;
        }
        int arrIndex = 0;
        for (int i = 0; i < countedArray.length; i++) {    // Filling array
            iterations++;
            if(countedArray[i] > 0){
                for (int j = 0; j < countedArray[i]; j++) {
                    arr[arrIndex++] = i - min;
                }
            }
        }
        System.out.printf("Counting: Array with %d elements - %d iterations\n", capacity, iterations);
    }

    private void expand(){
        int[] newArray = new int[arr.length * 2];
        System.arraycopy(arr, 0, newArray, 0, arr.length);
        arr = newArray;
    }
}
