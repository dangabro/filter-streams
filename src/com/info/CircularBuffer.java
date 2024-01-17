package com.info;

public class CircularBuffer {
    private final int[] buffer;

    private int size;
    private int head;
    private int tail;

    public CircularBuffer(int capacity) {
        buffer = new int[capacity];

        size = 0;
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == buffer.length;
    }

    public void add(int data) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Buffer is full");
        }

        buffer[tail] = data;
        tail = (tail + 1) % buffer.length;
        size++;
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Buffer is empty");
        }

        int data = buffer[head];
        head = (head + 1) % buffer.length;
        size--;

        return data;
    }

    public int size() {
        return size;
    }

}
