package ru.geekbrains.taskadd.cycledlist;

import java.util.Iterator;

public class CycledList<T> implements Iterable<CycledList.Node<T>>{
    private boolean iteratorProtected;
    private Node<T> head;
    private Node<T> tail;

    public void add(Node<T> node){
        if(head == null){
            head = node;
        } else {
            if(isCycled()){
                node.next = tail.next;
            }
            tail.next = node;
        }
        tail = node;
    }

    public boolean isCycled(){
        return tail.next != null;
    }

    public Node<T> getCycledNode(){
        return isCycled() ? tail : null;
    }

    public void setIteratorProtected(boolean iteratorProtected) {
        this.iteratorProtected = iteratorProtected;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new Iterator<>() {
            Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Node<T> next() {
                Node<T> res = current;
                if(iteratorProtected){                  // ITERATOR PROTECTION FOR CYCLED LIST
                    if(current == tail && tail.next != null){
                        current = null;
                    } else {
                        current = current.next;
                    }
                } else {
                    current = current.next;
                }
                return res;
            }
        };
    }


    public static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public void setNext(Node<T> node){
            next = node;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }
    }
}
