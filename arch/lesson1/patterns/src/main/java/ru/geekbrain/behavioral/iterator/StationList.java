package ru.geekbrain.behavioral.iterator;

import java.util.Iterator;

public class StationList implements Iterable<StationList.RadioStation>{
    private RadioStation head;
    private RadioStation tail;

    public void add(RadioStation station){
        if(this.head == null){
            this.head = station;
        } else {
            this.tail.next = station;
        }
        this.tail = station;
    }

    @Override
    public Iterator<RadioStation> iterator() {
        return new Iterator<RadioStation>() {
            RadioStation current = head;
            @Override
            public boolean hasNext() {
                return current != null && tail.next != current;
            }

            @Override
            public RadioStation next() {
                RadioStation res = current;
                current = current.next;
                return res;
            }
        };
    }

    public static class RadioStation {
        private float frequency;
        private RadioStation next;

        public RadioStation(float frequency) {
            this.frequency = frequency;
        }

        public float getFrequency() {
            return this.frequency;
        }
    }
}
