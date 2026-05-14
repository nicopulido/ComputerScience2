package com.udistrital;
import com.udistrital.Model.Graph.*;

public class App 
{
    public static void main( String[] args )

    {   

    WeightsGraph<Character, Integer> grafo2 = new WeightsGraph<>(9999, 0);
    grafo2.addVertex('A');
    grafo2.addVertex('B');
    grafo2.addVertex('C');
    grafo2.addVertex('D');
    grafo2.addVertex('E');

    grafo2.addEdge('A', 'B', 1);
    grafo2.addEdge('A', 'C', 1);
    grafo2.addEdge('C', 'D', 1);
    grafo2.addEdge('C', 'E', 1);
    }

}
    
