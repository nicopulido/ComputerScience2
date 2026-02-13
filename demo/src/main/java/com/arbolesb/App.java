package com.arbolesb;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.arbolesb.model.Loaf;

@ApplicationPath("app")
public class App extends Application {
    
    public static void main(String[] args) {
        Loaf loaf = new Loaf(4);
        loaf.insertion(3);
        loaf.insertion(4);
        loaf.insertion(5);
        loaf.print();
    }

}
