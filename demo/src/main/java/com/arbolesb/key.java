package com.arbolesb;

public class key {
    int value;
    int previousValue;
    int nextValue;

    public key(int value) {
        this.value = value;
        this.previousValue = -1; 
        this.nextValue = -1; 
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
