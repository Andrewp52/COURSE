package ru.geekbrains.homework2.task2;

import ru.geekbrains.homework2.MyList;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
   private final static int INIT_SIZE = 3;
   private Object[] array = new Object[INIT_SIZE];
   private int tail;

    public T get(int index){
        validateIndex(index);
        return (T) array[index];
    }

    public void add(T value){
        validateIndex(this.tail);
        this.array[this.tail] = value;
        this.tail++;
    }

    @Override
    public void addFirst(T value) {
        validateIndex(tail);
        Object[] tailAfter = cutTailAfterIndex(0);
        array[0] = value;
        attachTailAt(1, tailAfter);
        tail++;
    }

    public void remove(int index){
        validateIndex(index);
        Object[] tailArr = cutTailAfterIndex(index + 1);
        attachTailAt(index, tailArr);
        this.tail--;
    }

    @Override
    public void remove(T obj) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(obj)){
                iterator.remove();
            }
        }
    }

    @Override
    public void removeLast() {
        tail--;
    }

    @Override
    public void removeFirst() {
        attachTailAt(0, cutTailAfterIndex(1));
        tail--;
    }

    public int indexOf(T obj){
        for (int i = 0; i < tail; i++){
            if(this.array[i].equals(obj)){
                return i;
            }
        }
        return -1;
    }

    public int size(){
        return this.tail;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index;
            @Override
            public boolean hasNext() {
                return this.index < tail;
            }

            @Override
            public T next() {
                T val = (T) array[index];
                this.index++;
                return val;
            }

            @Override
            public void remove() {
                Object[] tailArr = cutTailAfterIndex(index);
                attachTailAt(index - 1, tailArr);
                tail--;
            }
        };
    }

    private Object[] cutTailAfterIndex(int start){
        return Arrays.copyOfRange(this.array, start, this.tail);
    }

    private void attachTailAt(int index, Object[] tailArr){
        System.arraycopy(tailArr, 0, this.array, index, tailArr.length);
    }

    private void validateIndex(int index){
        if(this.array.length == this.tail){
            expand();
        }
        if(index < 0 || index > this.tail){
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }

    private void expand(){
        this.array = Arrays.copyOf(this.array, this.array.length + INIT_SIZE);
    }

}
