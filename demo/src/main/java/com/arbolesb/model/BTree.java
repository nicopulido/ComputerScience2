package com.arbolesb.model;

public class BTree {

    int maxChild;
    Loaf root;

    public BTree(int maxChild) {
        this.maxChild = maxChild;
        this.root = null;
    }

    public void insertion(int value) {

        if(this.root.isFull() == false) {
            this.root.insertion(value);
        }
        else {
            // TODO: division function, make it appart
        }
    }



    
}
