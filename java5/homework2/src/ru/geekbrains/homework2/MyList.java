package ru.geekbrains.homework2;

public interface MyList <T> extends Iterable<T>{
    int size();
    void add(T value);
    void addFirst(T value);
    void remove(int index);
    void remove(T obj);
    void removeLast();
    void removeFirst();
    T get(int index);
    int indexOf(T obj);

}
