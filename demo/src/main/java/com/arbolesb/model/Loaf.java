package com.arbolesb.model;

import java.util.Arrays;

public class Loaf {

    int maxChild;
    int maxKey;
    int numKeys;
    Loaf parent;      
    Key[] keys;
    Loaf[] children;
    int numChildren;

    public Loaf(int maxChild) {
        this.maxChild = maxChild;
        this.maxKey = maxChild - 1;
        this.numKeys = 0;
        this.parent = null;
        this.keys = new Key[maxChild]; 
        this.children = new Loaf[maxChild + 1];
        this.numChildren = 0;
    }


    public Loaf(int maxChild, Loaf parent) {
        this.maxChild = maxChild;
        this.maxKey = maxChild - 1;
        this.numKeys = 0;
        this.parent = parent;
        this.keys = new Key[maxChild]; 
        this.children = new Loaf[maxChild + 1];
        this.numChildren = 0;
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
        this.keys[this.numKeys] = newKey;
        this.numKeys++;
        this.sort();
        if (this.numKeys > this.maxKey) {
            DivResult resultDiv = this.division();
            
            if (this.parent == null) {
                Loaf newRoot = new Loaf(this.maxChild, null);
                newRoot.keys[0] = new Key(resultDiv.ValueKeyFather);
                newRoot.numKeys = 1;
                
                newRoot.children[0] = resultDiv.LeftChild;
                newRoot.children[1] = resultDiv.RightChild;
                newRoot.numChildren = 2;

                resultDiv.LeftChild.parent = newRoot;
                resultDiv.RightChild.parent = newRoot;
                
                this.parent = newRoot; 
            } 
            else {
                promParent(resultDiv);
            }
        }
    }

    private void promParent(DivResult result) {
        this.parent.insertion(result.ValueKeyFather);
        int index = 0;
        while (index < this.parent.numKeys && this.parent.keys[index].getValue() != result.ValueKeyFather) {
            index++;
        }
        
        this.parent.children[index] = result.LeftChild;
        for (int j = this.parent.numChildren; j > index + 1; j--) {
            this.parent.children[j] = this.parent.children[j - 1];
        }
        
        this.parent.children[index + 1] = result.RightChild;
        this.parent.numChildren++;

        result.LeftChild.parent = this.parent;
        result.RightChild.parent = this.parent;
    }

    public DivResult division() {
        int indexChoosen = (this.numKeys - 1) / 2;
        int indexValue = this.keys[indexChoosen].getValue();

        Loaf left = new Loaf(this.maxChild, this.parent);
        Loaf right = new Loaf(this.maxChild, this.parent);

        left.numKeys = indexChoosen;
        for (int i = 0; i < indexChoosen; i++) {
            left.keys[i] = this.keys[i];
        }
       
        if (this.numChildren > 0) {
            left.numChildren = indexChoosen + 1;
            for (int i = indexChoosen; i >= 0; i--) {
                left.children[i] = this.children[i];
                if (left.children[i] != null) left.children[i].parent = left;
            }
        }

        right.numKeys = this.numKeys - indexChoosen - 1;
        for (int i = indexChoosen + 1; i < this.numKeys; i++) {
            right.keys[i - indexChoosen - 1] = this.keys[i];
        }
        if (this.numChildren > 0) {
            right.numChildren = right.numKeys + 1;
            for (int i = 0; i <= right.numKeys; i++) {
                right.children[i] = this.children[indexChoosen + 1 + i];
                if (right.children[i] != null) right.children[i].parent = right;
            }
        }

        return new DivResult(indexValue, left, right);
    }

    public void print(int level) {
        System.out.println("Level " + level + Arrays.toString(Arrays.copyOf(keys, numKeys)));
        for (int i = 0; i < numChildren; i++) {
            if (children[i] != null) children[i].print(level + 1);
        }
    }
}