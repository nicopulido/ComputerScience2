package com.arbolesb.model;


public class Key {

    int value;
    Key next;
    Key previous;

    public Key(int value) {
        this.value = value;
    }

    public void setNext(Key next) {
        this.next = next;
    }

    public void setPrevious(Key previous) {
        this.previous = previous;
    }

    public int getValue() {
        return value;
    }

    public Key next() {
        return next;
    }

    public Key previous() {
        return previous;
    }

}