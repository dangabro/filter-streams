package com.info;

import java.io.IOException;
import java.io.InputStream;

public class XmlTagFilterStream extends InputStream {

    private final InputStream input;
    private CircularBuffer buffer;
    private boolean inputDone;
    private boolean inTag;
    private final ArrayFindProc start = new ArrayFindProc(new int[]{'<', '?'});
    private final ArrayFindProc end = new ArrayFindProc(new int[]{'?', '>'});


    public XmlTagFilterStream(InputStream input, int maximum) {
        this.input = input;
        buffer = new CircularBuffer(maximum);

    }

    @Override
    public int read() throws IOException {
        while (!inputDone) {
            int val = input.read();

            if (val == -1) {
                inputDone = true;
                break;
            } else {
                if (inTag) {
                    if(end.verify(val)){
                        if (end.isCompleted()) {
                            inTag = false;
                            end.resetPosition();
                        }
                    } else {
                        end.resetPosition();
                    }
                } else {
                    if (start.verify(val)) {
                        if (start.isCompleted()) {
                            inTag = true;
                            start.resetPosition();
                        }
                    } else {
                        start.reset(buffer);
                        buffer.add(val);

                        break;
                    }
                }
            }
        }

        int res = -1;
        if (!buffer.isEmpty()) {
            res = buffer.remove();
        }

        return res;
    }
}
