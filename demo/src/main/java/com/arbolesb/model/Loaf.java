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
    int minKeys;
    int fatherDegrade;

    public Loaf(int maxChild) {
        this.maxChild = maxChild;
        this.maxKey = maxChild - 1;
        this.numKeys = 0;
        this.parent = null;
        this.keys = new Key[maxChild]; 
        this.children = new Loaf[maxChild + 1];
        this.numChildren = 0;
        this.minKeys = (maxChild - 1) / 2;
    }


    public Loaf(int maxChild, Loaf parent) {
        this.maxChild = maxChild;
        this.maxKey = maxChild - 1;
        this.numKeys = 0;
        this.parent = parent;
        this.keys = new Key[maxChild]; 
        this.children = new Loaf[maxChild + 1];
        this.numChildren = 0;
        this.minKeys = (maxChild - 1) / 2;
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

    public void rebalance(Loaf current) {
 
        if(current.parent.children[0] == current){
            this.fatherDegrade = current.parent.keys[0].getValue();    
        }else if (current.parent.children[current.parent.numChildren - 1] == current){
            this.fatherDegrade = current.parent.keys[current.parent.numKeys - 1].getValue();
        }else {
            for (int i = 0; i < current.parent.numChildren - 1; i++) {
                if (current.parent.children[i] == current) {
                    this.fatherDegrade = current.parent.keys[i].getValue();
                    break;
                }
            }
        }
        
        current.insertion(this.fatherDegrade);
        for (int i = 0; i < current.parent.numKeys; i++) {
            if (current.parent.keys[i].getValue() == this.fatherDegrade) {
                current.parent.keys[i] = null;
                for (int j = i; j < current.parent.numKeys - 1; j++) {
                    current.parent.keys[j] = current.parent.keys[j + 1];
                }
                current.parent.keys[current.parent.numKeys - 1] = null;
                current.parent.numKeys--;
                break;
            }
        }

        
    
    
    }

    public void rotation() {
        int i = 0;
        while (i < this.parent.numChildren && this.parent.children[i] != this) {
            i++;
        }

        if (this.parent.children[i] == this) {
            int indexBrotherLeft = i - 1;
            int indexBrotherRight = i + 1;
            int indexCurrent = i;

            if (indexBrotherLeft >= 0 && this.parent.children[indexBrotherLeft].numKeys > this.minKeys) {
                Loaf brotherLeft = this.parent.children[indexBrotherLeft];
                Key keyPromote = brotherLeft.keys[brotherLeft.numKeys - 1];

                for (int j = this.numKeys; j > 0; j--) {
                    this.keys[j] = this.keys[j - 1];
                }
                this.keys[0] = new Key(this.parent.keys[indexCurrent - 1].getValue());
                this.numKeys++;

                this.parent.keys[indexCurrent - 1] = keyPromote;
                brotherLeft.keys[brotherLeft.numKeys - 1] = null;
                brotherLeft.numKeys--;

                if (brotherLeft.numChildren > 0) {
                    for (int j = this.numChildren; j > 0; j--) {
                        this.children[j] = this.children[j - 1];
                    }
                    this.children[0] = brotherLeft.children[brotherLeft.numChildren - 1];
                    if (this.children[0] != null) {
                        this.children[0].parent = this;
                    }
                    this.numChildren++;
                    brotherLeft.children[brotherLeft.numChildren - 1] = null;
                    brotherLeft.numChildren--;
                }
            } else if (indexBrotherRight < this.parent.numChildren && this.parent.children[indexBrotherRight].numKeys > this.minKeys) {
                Loaf brotherRight = this.parent.children[indexBrotherRight];
                Key keyPromote = brotherRight.keys[0];

                this.keys[this.numKeys] = new Key(this.parent.keys[indexCurrent].getValue());
                this.numKeys++;
                this.parent.keys[indexCurrent] = keyPromote;

                for (int j = 0; j < brotherRight.numKeys - 1; j++) {
                    brotherRight.keys[j] = brotherRight.keys[j + 1];
                }
                brotherRight.keys[brotherRight.numKeys - 1] = null;
                brotherRight.numKeys--;

                if (brotherRight.numChildren > 0) {
                    this.children[this.numChildren] = brotherRight.children[0];
                    if (this.children[this.numChildren] != null) {
                        this.children[this.numChildren].parent = this;
                    }
                    this.numChildren++;
                    for (int j = 0; j < brotherRight.numChildren - 1; j++) {
                        brotherRight.children[j] = brotherRight.children[j + 1];
                    }
                    brotherRight.children[brotherRight.numChildren - 1] = null;
                    brotherRight.numChildren--;
                }
            } else {
                Loaf brotherLeft;
                Loaf brotherRight;
                int indexFatherDegrade;

                if (indexBrotherLeft >= 0) {
                    brotherLeft = this.parent.children[indexBrotherLeft];
                    brotherRight = this;
                    indexFatherDegrade = indexCurrent - 1;
                } else {
                    brotherLeft = this;
                    brotherRight = this.parent.children[indexBrotherRight];
                    indexFatherDegrade = indexCurrent;
                }

                Key keyPromote = new Key(this.parent.keys[indexFatherDegrade].getValue());
                brotherLeft.keys[brotherLeft.numKeys] = keyPromote;
                brotherLeft.numKeys++;

                for (int j = 0; j < brotherRight.numKeys; j++) {
                    brotherLeft.keys[brotherLeft.numKeys] = brotherRight.keys[j];
                    brotherLeft.numKeys++;
                }

                if (brotherRight.numChildren > 0) {
                    for (int j = 0; j < brotherRight.numChildren; j++) {
                        brotherLeft.children[brotherLeft.numChildren] = brotherRight.children[j];
                        if (brotherLeft.children[brotherLeft.numChildren] != null) {
                            brotherLeft.children[brotherLeft.numChildren].parent = brotherLeft;
                        }
                        brotherLeft.numChildren++;
                    }
                }

                for (int j = indexFatherDegrade; j < this.parent.numKeys - 1; j++) {
                    this.parent.keys[j] = this.parent.keys[j + 1];
                }
                this.parent.keys[this.parent.numKeys - 1] = null;
                this.parent.numKeys--;

                for (int k = indexFatherDegrade + 1; k < this.parent.numChildren - 1; k++) {
                    this.parent.children[k] = this.parent.children[k + 1];
                }
                this.parent.children[this.parent.numChildren - 1] = null;
                this.parent.numChildren--;
            }
        }
    }

        public void elimination(int value) {
        int i = 0;
        while (i < this.numKeys && value > this.keys[i].getValue()) {
            i++;
        }

        if (i < this.numKeys && this.keys[i].getValue() == value) {
            if (this.numChildren == 0) {
                for (int j = i; j < this.numKeys - 1; j++) {
                    this.keys[j] = this.keys[j + 1];
                    }
                this.keys[this.numKeys - 1] = null;
                this.numKeys--;
            } else {
                Loaf leftChild = this.children[i];
                Loaf current = leftChild;
                while (current.numChildren > 0) {
                    current = current.children[current.numChildren - 1];
                    }
                int predecessor = current.keys[current.numKeys - 1].getValue();
                this.keys[i] = new Key(predecessor);
                this.children[i].elimination(predecessor);
            }
            return; 
        }

        if (this.numChildren == 0) {
            System.out.println("El valor no se encuentra en el arbol");
            return;
        }

        Loaf child = this.children[i];
        if (child.numKeys <= this.minKeys) {
            child.rotation();
            i = 0;
            while (i < this.numKeys && value > this.keys[i].getValue()) {
                i++;
            }
        }
        
        if (this.children[i] != null) {
            this.children[i].elimination(value);
        }
    }
   

    public void print(int level) {
        System.out.println("Level " + level + Arrays.toString(Arrays.copyOf(keys, numKeys)));
        for (int i = 0; i < numChildren; i++) {
            if (children[i] != null) children[i].print(level + 1);
        }
    }
}