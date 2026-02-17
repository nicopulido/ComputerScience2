package com.arbolesb;

import com.arbolesb.model.BTree;
import com.arbolesb.controller.*;;

public class App {
    
    public static void main(String[] args) {
        Controller controller = new Controller(4);
        BTree bTree = controller.btree;

        System.out.println("primera extensión");
        bTree.insertion(23);
        System.out.println(bTree.print());
        System.out.println("segunda extensión");
        bTree.insertion(45);
        System.out.println(bTree.print());
        System.out.println("tercera extensión");
        bTree.insertion(5);
        System.out.println(bTree.print());
        System.out.println("cuarta extensión");
        bTree.insertion(13);
        System.out.println(bTree.print());
        bTree.insertion(40);
        System.out.println(bTree.print());
        bTree.insertion(50);
        System.out.println(bTree.print());
        bTree.insertion(60);
        System.out.println(bTree.print());
        bTree.elimination(45);
        System.out.println(bTree.print());
        bTree.elimination(23);
        System.out.println(bTree.print());
    }

}