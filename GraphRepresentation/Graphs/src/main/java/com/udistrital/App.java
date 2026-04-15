package com.udistrital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.udistrital.Model.Graph.*;
import com.udistrital.Model.Edge.*;
import com.udistrital.Model.Vertex.*;

public class App 
{
    public static void main( String[] args )
    {   
        /*SimpleGraph<Character> grafo = new SimpleGraph<>();
        grafo.addVertex('A');
        grafo.addVertex('B');
        grafo.addEdge('A', 'B');
        grafo.listInci();*/

        WeightsGraph<Character, Integer> grafo2 = new WeightsGraph<>(9999, 0);

        grafo2.addVertex('I');
        grafo2.addVertex('A'); 
        grafo2.addVertex('D');
        grafo2.addVertex('E'); 
        grafo2.addVertex('T');
        grafo2.addVertex('F'); 
        grafo2.addVertex('C');
        grafo2.addVertex('B');

        grafo2.addEdge('I', 'A', 9);
        grafo2.addEdge('I', 'C', 2);        
        grafo2.addEdge('B', 'C', 1);
        grafo2.addEdge('C', 'F', 10);
        grafo2.addEdge('B','F',6);
        grafo2.addEdge('B', 'A', 1);
        grafo2.addEdge('B', 'D', 7);
        grafo2.addEdge('A', 'D', 1);
        grafo2.addEdge('D', 'E', 2);
        grafo2.addEdge('E', 'T', 9);
        grafo2.addEdge('D', 'T', 18);
        grafo2.addEdge('F', 'T', 2);

        Integer[][] matrix = grafo2.matrixAdya();
        for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j] + " ");
            
        }System.out.println();}
        System.out.println(dijkstra(grafo2, 'I', 'T'));
        
    }
  
    public static Integer[][] floydWarshall(Integer[][] matrix) {
        int len = matrix.length;
        int INF = 9999;
        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (matrix[i][k] != INF && matrix[k][j] != INF
                            && matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        return matrix;
    }

public static <T, U extends Number> String dijkstra(WeightsGraph<T, U> graph, T value_origin, T value_destiny) {

    IVertex<T, U> origen = graph.getVertex(value_origin);
    IVertex<T, U> destiny = graph.getVertex(value_destiny);

    if (origen == null || destiny == null) {
        return "El origen o el destino no existen en el grafo.";
    }
    
    Map<IVertex<T, U>, Double> distancias = new HashMap<>();
    Map<IVertex<T, U>, IVertex<T, U>> predecesores = new HashMap<>();
    Set<IVertex<T, U>> visitados = new HashSet<>();
    distancias.put(origen, 0.0);
    IVertex<T, U> actual = origen;

    while (!actual.equals(destiny)) {

        double minDistancia = Double.MAX_VALUE;

        for (Map.Entry<IVertex<T, U>, Double> entry : distancias.entrySet()) {

            if (!visitados.contains(entry.getKey()) && entry.getValue() < minDistancia) {
                minDistancia = entry.getValue();
                actual = entry.getKey();
            }
        }

        if(actual != null) {
            visitados.add(actual);
        }

        for (Edge<T, U> edge : actual.getEdges()) {
            IVertex<T, U> vecino = edge.get(actual);

            if(!visitados.contains(vecino)){

            double pesoArista = edge.getValue().doubleValue();
            double nuevaDistancia = distancias.get(actual) + pesoArista;
            double distanciaActualVecino = distancias.getOrDefault(vecino, Double.MAX_VALUE);

            if (nuevaDistancia < distanciaActualVecino) {
                distancias.put(vecino, nuevaDistancia);
                predecesores.put(vecino, actual);
            }
        }
            }
    }

    if (!distancias.containsKey(destiny)) {
        return "No existe un camino entre " + value_origin + " y " + value_destiny;
    }

    List<T> camino = new ArrayList<>();
    IVertex<T, U> paso = destiny;

    while (paso != null) {
        camino.add(paso.getValue()); 
        paso = predecesores.get(paso);
    }
    
    Collections.reverse(camino);
    return "Camino más corto: " + camino.toString() + " | Costo total: " + distancias.get(destiny);
}

}
    
