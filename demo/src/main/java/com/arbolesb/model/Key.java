package com.arbolesb.model;

public class Key {

    Loaf leftPointer;
    Loaf rightPointer;
    int value;

    public Key(int value) {
        this.value = value;
        this.leftPointer = null;
        this.rightPointer = null;
    }
    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public Loaf getLeft() {
        return leftPointer;
    }

    public Loaf getRight() {
        return rightPointer;
    }

    public int getValue() {
        return value;   
    }

    public void setLeft(Loaf leftPointer) {
        this.leftPointer = leftPointer;
    }
    
    public void setRight(Loaf rightPointer) {
        this.rightPointer = rightPointer;
    }

}
