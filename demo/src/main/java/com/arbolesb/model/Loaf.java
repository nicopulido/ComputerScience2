package com.arbolesb.model;

public class Loaf {

    int maxChild;
    int maxKey;
    int minKey;
    int numKeys;
    Loaf parent;
    Key[] keys;

    public Loaf(int maxChild) {
        this.maxChild = maxChild;
        this.maxKey = maxChild - 1;
        this.minKey = (int) Math.ceil(maxChild / 2.0) - 1;
        this.numKeys = 0;
        this.parent = null;
        this.keys = new Key[maxKey];
    }

    public Loaf(int maxChild, Loaf parent) {
        this.maxChild = maxChild;
        this.maxKey = maxChild - 1;
        this.minKey = (int) Math.ceil(maxChild / 2.0) - 1;
        this.numKeys = 0;
        this.parent = parent;
        this.keys = new Key[maxKey];
    }

    public void sort() {
        
        int i, j;
        Key temp;
        boolean swapped;
        for (i = 0; i < this.numKeys - 1; i++) {
            swapped = false;
            for (j = 0; j < this.numKeys - i - 1; j++) {
                if(this.keys[j].getValue() > this.keys[j+1].getValue()) {
                    temp = this.keys[j];
                    this.keys[j] = this.keys[j + 1];
                    this.keys[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false)
                break;
        }
    }

    public void insertion(int value) {

        Key newKey = new Key(value);
        
        if(this.numKeys < this.maxKey) {
            this.keys[numKeys] = newKey;
            this.numKeys++;
            this.sort();
        }
        else {
            System.out.println("No hay espacio");
        }
        
    }

    public void print() {
        for (int i = 0; i < this.numKeys; i++) {
            System.out.println(this.keys[i]);
            
        }
    }

    public int getMaxChild() {
        return maxChild;
    }

    public void setMaxChild(int maxChild) {
        this.maxChild = maxChild;
    }

    public int getMaxKey() {
        return maxKey;
    }

    public void setMaxKey(int maxKey) {
        this.maxKey = maxKey;
    }

    public int getMinKey() {
        return minKey;
    }

    public void setMinKey(int minKey) {
        this.minKey = minKey;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    public Loaf getParent() {
        return parent;
    }

    public void setParent(Loaf parent) {
        this.parent = parent;
    }

    public Key[] getKeys() {
        return keys;
    }

    public void setKeys(Key[] keys) {
        this.keys = keys;
    }
    
}
