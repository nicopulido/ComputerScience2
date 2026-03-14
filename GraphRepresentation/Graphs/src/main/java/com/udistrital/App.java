package com.udistrital;

import com.udistrital.Model.*;


public class App 
{
    public static void main( String[] args )
    {
        Graph<Float> grafo = new Graph<>();
        grafo.addVertex(1.5f);
        grafo.addVertex(2.4f);
        grafo.addVertex(3.1f);
        grafo.addEdge(1.5f, 2.4f);
        grafo.addEdge(1.5f, 3.1f);
        

    }
}
