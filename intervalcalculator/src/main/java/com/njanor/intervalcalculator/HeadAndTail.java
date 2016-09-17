package com.njanor.intervalcalculator;

public class HeadAndTail {
    private String head;
    private String tail;

    public HeadAndTail(final String input) {
        int lastIndexOfFirstInterval = input.indexOf(',') - 1;
        if (lastIndexOfFirstInterval == -2)
            lastIndexOfFirstInterval = input.length() - 1;
        head = input.substring(0, lastIndexOfFirstInterval + 1).trim();

        if (hasRemainder(input, lastIndexOfFirstInterval)) {
            int indexOfStartOfNextInterval = input.indexOf(',', lastIndexOfFirstInterval) + 1;
            tail = input.substring(indexOfStartOfNextInterval).trim();
        }
        else {
            tail = "";
        }
    }

    private boolean hasRemainder(final String input, final int lastIndexOfFirstInterval) {
        return input.indexOf(',', lastIndexOfFirstInterval) > 0;
    }

    public String getHead() {
        return head;
    }

    public String getTail() {
        return tail;
    }
}