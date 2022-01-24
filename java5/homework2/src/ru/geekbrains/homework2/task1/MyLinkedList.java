package ru.geekbrains.homework2.task1;

import ru.geekbrains.homework2.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList <T> implements MyList<T> {
    private int size;
    private ListNode head;
    private ListNode tail;

    public int size() {
        return size;
    }

    public void add(T value){
        if(head == null){
            this.head = new ListNode(value);
            this.tail = this.head;
        } else {
            ListNode node = new ListNode(value);
            node.previous = tail;
            this.tail.next = node;
            this.tail = node;
        }
        size++;
    }

    public void addFirst(T value){
        ListNode node = new ListNode(value);
        node.next = head;
        head = node;
        size++;
    }

    public void remove(int index){
        validateIndex(index);
        if(index == 0){
            removeFirst();
        } else if(index == this.size - 1){
            removeLast();
        } else {
            removeAtIndex(index);
        }
    }

    public void remove(T obj){
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()){
            if(iterator.next().equals(obj)){
                iterator.remove();
                return;
            }
        }
    }

    public void removeLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        this.tail = this.tail.previous;
        this.tail.next = null;
        this.size--;
    }

    public void removeFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        this.head = this.head.next;
        this.head.previous = null;
        this.size--;
    }

    public T get(int index){
        validateIndex(index);
        if(index == 0){
            return head.value;
        }
        if(index == size - 1){
            return tail.value;
        }
        Iterator<T> iterator = iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    public int indexOf(T obj){
        Iterator iterator = iterator();
        int index = 0;
        while (iterator.hasNext()){
            if(iterator.next().equals(obj)){
                return index;
            }
            index++;
        }
        return -1;
    }

    private void removeAtIndex(int index){
        Iterator<T> iterator = iterator();
        for (int i = 0; i < index + 1; i++) {
            iterator.next();
        }
        iterator.remove();
    }

    private void validateIndex(int index){
        if(index >= size || index < 0){
            throw new ArrayIndexOutOfBoundsException("index is out of bounds: " + index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ListNode current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T val = current.value;
                current = current.next;
                return val;
            }

            @Override
            public void remove() {
                if(current != null){
                    ListNode toRemove = current.previous;
                    ListNode trNext = toRemove.next;
                    ListNode trPrev = toRemove.previous;
                    if(toRemove == head){
                        removeFirst();
                    } else {
                        trNext.previous = trPrev;
                        trPrev.next = trNext;
                    }
                } else {
                    removeLast();
                }
                size--;
            }
        };
    }

    private class ListNode {
        private T value;
        private ListNode next;
        private ListNode previous;

        public ListNode(T value) {
            this.value = value;
        }

    }


}
