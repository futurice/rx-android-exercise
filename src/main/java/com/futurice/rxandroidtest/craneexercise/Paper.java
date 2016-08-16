package com.futurice.rxandroidtest.craneexercise;

/**
 * Created by ttuo on 30/09/15.
 */
public class Paper {
    private final String color;
    private final int processingTimeMs;

    public Paper(String color, int processingTimeMs) {
        this.color = color;
        this.processingTimeMs = processingTimeMs;
    }

    public int getProcessingTimeMs() {
        return processingTimeMs;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color;
    }
}
