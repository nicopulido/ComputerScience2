package com.udistrital;

import com.udistrital.Model.Algorithms.FirstSearch.Depth;
import com.udistrital.Model.Algorithms.MaximumFlow.EdmondsKarp;
import com.udistrital.Model.Algorithms.MaximumFlow.FordFulkerson;
import com.udistrital.Model.Graph.*;

public class App {
    public static void main(String[] args) {

    WeightsGraph<Character, Integer> grafo2 = new WeightsGraph<>(9999, 0);
    grafo2.addVertex('S');
    grafo2.addVertex('1');
    grafo2.addVertex('2');
    grafo2.addVertex('3');
    grafo2.addVertex('4');
    grafo2.addVertex('T');

    grafo2.addEdge('S', '1', 3);
    grafo2.addEdge('1', '2', 5);
    grafo2.addEdge('1', '3', 3);
    grafo2.addEdge('1', '4', 4);
    grafo2.addEdge('2', '4', 3);
    grafo2.addEdge('3', '4', 3);
    grafo2.addEdge('4', 'T', 6);
    grafo2.addEdge('3', 'T', 6);
    grafo2.addEdge('S', '2', 7);

    grafo2.printAdya();
    
    FordFulkerson<Character, Integer> fordFulkerson = new FordFulkerson<>(grafo2, new Depth<>());
    FordFulkerson<Character, Integer> edmondsKarp = new EdmondsKarp<>(grafo2);
    System.out.println(fordFulkerson.maxFlow('S', 'T'));
    System.out.println(edmondsKarp.maxFlow('S', 'T'));
    }
}


    
