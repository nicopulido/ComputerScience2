package com.arbolesb.model;

public class BTree {

    int maxChild;
    Loaf root;

    public BTree(int maxChild) {
        this.maxChild = maxChild;
        this.root = null;
    }

    public void insertion(int value) {

        if (root == null) {
            root = new Loaf(maxChild);
            root.insertion(value);
        }
        else {
            Loaf current = root;
            while (current.numChildren > 0) {
                int i = 0;
                while (i < current.numKeys && value > current.keys[i].getValue()) {
                    i++;
                }
                current = current.children[i];
            }
            current.insertion(value);
            if (root.parent != null) {
                root = root.parent;
            }
        }


        
    }
    public void print() {
        if (root != null) {
            root.print(0);
        }
    }



    
}
