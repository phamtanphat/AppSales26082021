package com.example.appsales26082021;

import javax.inject.Inject;

public class Wheel {
    private int count;

    @Inject
    public Wheel(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "count=" + count +
                '}';
    }
}
