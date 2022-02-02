package ru.geekbrains.taskadd.reverselist;

import java.util.Iterator;

public class SingleLinkList <T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    public void add(T value){
        if(head == null){
            head = new Node(value);
            tail = head;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != tail.next;
            }

            @Override
            public T next() {
                T val = current.value;
                current = current.next;
                return val;
            }
        };
    }

    public void reverse() {
        tail = head;
        Node<T> prev = tail;
        Node<T> curr = tail.next;
        tail.next = null;
        while (curr != null){
            head = curr;
            Node<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }


    private class Node<T>{
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }
}
