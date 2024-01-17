package com.info;

public class ArrayFindProc {
    private int[] bytes;
    private int position;

    public ArrayFindProc(int[] bytes) {
        this.bytes = bytes;
    }

    public boolean verify(int val) {
        boolean res = false;
        if (val == bytes[position]) {
            position++;
            res = true;
        }

        return res;
    }

    public boolean isCompleted() {
        return position == bytes.length;
    }

    public void reset(CircularBuffer buffer) {
        if (position < bytes.length) {
            for (int i = 0; i < position; i++) {
                buffer.add(bytes[i]);
            }
        }

        position = 0;
    }

    public void resetPosition() {
        position = 0;
    }
}
