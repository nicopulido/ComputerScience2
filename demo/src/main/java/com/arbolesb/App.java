package com.arbolesb;

import com.arbolesb.model.BTree;

public class App {
    
    public static void main(String[] args) {
        BTree bTree = new BTree(4);
        System.out.println("primera extensión");
        bTree.insertion(23);
        bTree.print();
        System.out.println("segunda extensión");
        bTree.insertion(45);
        bTree.print();
        System.out.println("tercera extensión");
        bTree.insertion(5);
        bTree.print();
        System.out.println("cuarta extensión");
        bTree.insertion(13);
        bTree.print();
        bTree.insertion(40);
        bTree.print();
    }

}