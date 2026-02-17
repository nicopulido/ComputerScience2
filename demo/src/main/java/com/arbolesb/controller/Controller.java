package com.arbolesb.controller;

import com.arbolesb.model.BTree;

public class Controller {
    
    public BTree btree;

    public Controller(int maxChild) {
        this.btree = new BTree(maxChild);
    }

    public void insertion(int value){
        this.btree.insertion(value);
    } 

    public void elimination(int value) {
        this.btree.elimination(value);
    }
    // TODO: Frontend devs, please change this function
    public String print() {
        return this.btree.print();
    }
}
