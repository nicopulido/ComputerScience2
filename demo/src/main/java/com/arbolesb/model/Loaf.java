package com.arbolesb.model;

public class Loaf {

    int maxChilds;
    int maxKeys;
    Key firstKey;
    Key lastKey;

    public Loaf(int maxChilds) {
        this.maxChilds = maxChilds;
        this.maxKeys = maxChilds - 1;

    }

    public void insertion(int value) {
        int counter = 0;
        if(this.firstKey == null) {
            this.firstKey = new Key(value);
        }
        Key nextKey = this.firstKey;
        Key newKey = new Key(value);
        while(counter < this.maxKeys) {

            if(nextKey.next() == null) {
                nextKey.setNext(newKey);
                return;
            }
            else {
                nextKey = nextKey.next();
                counter++;
            }
        }
        System.out.println("Lleno");

    }

    public void print() {

        if(this.firstKey == null) {
        System.out.println("Nodo vacio");
        }

        else { 
            Key nextKey = this.firstKey;
            while(nextKey.next() != null) {
                nextKey = nextKey.next();
                System.out.println(nextKey.getValue());
                    
            }
        }
    }

}
