package com.arbolesb;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.arbolesb.model.Loaf;

@ApplicationPath("app")
public class App extends Application {
    
    public static void main(String[] args) {
        Loaf loaf = new Loaf(4);
        System.out.println("primera extensión");
        loaf.insertion(23);
        loaf.print();
        System.out.println("segunda extensión");
        loaf.insertion(45);
        loaf.print();
        System.out.println("tercera extensión");
        loaf.insertion(5);
        System.out.println("cuarta extensión");
        loaf.insertion(13);
        loaf.insertion(40);
        loaf.print();
    }

}
