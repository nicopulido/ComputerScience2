package com.arbolesb.model;

public class DivResult {
    int ValueKeyFather;
    Loaf LeftChild;
    Loaf RightChild;

    public DivResult(int valueKeyFather, Loaf leftChild, Loaf rightChild) {
        this.ValueKeyFather = valueKeyFather;
        this.LeftChild = leftChild;
        this.RightChild = rightChild;
    }
}
