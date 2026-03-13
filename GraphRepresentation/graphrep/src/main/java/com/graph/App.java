package com.graph;

import com.graph.model.Graph;
import com.graph.model.AdjacencyM;
import com.graph.model.IncidenceM;
import java.lang.reflect.Field;

public class App {
    public static void main(String[] args) {
        probarGrafo("ADYACENCIA", new AdjacencyM<>());
        System.out.println("\n\n");
        probarGrafo("INCIDENCIA", new IncidenceM<>());
    }
    
    private static void probarGrafo(String tipo, Graph<String> graph) {
        System.out.println(tipo);
        
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        System.out.println("Nodos agregados: A, B, C");
        mostrarMatriz(graph);
        
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");
        System.out.println("Aristas agregadas: A->B, B->C, A->C");
        mostrarMatriz(graph);
        
        System.out.println("A existe: " + graph.hasNode("A"));
        System.out.println("A->B existe: " + graph.hasEdge("A", "B"));
        
        graph.removeEdge("A", "B");
        System.out.println("Eliminada A->B");
        mostrarMatriz(graph);
        
        graph.removeNode("B");
        System.out.println("Eliminado B");
        mostrarMatriz(graph);
    }
    
    private static void mostrarMatriz(Graph<String> graph) {
        try {
            Field f = graph.getClass().getDeclaredField("adjacencyMatrix");
            f.setAccessible(true);
            int[][] m = (int[][]) f.get(graph);
            for(int[] fila : m) {
                for(int val : fila) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
            System.out.println();
            return;
        } catch(Exception e) {}
        
        try {
            Field f = graph.getClass().getDeclaredField("incidenceMatrix");
            f.setAccessible(true);
            int[][] m = (int[][]) f.get(graph);
            for(int[] fila : m) {
                for(int val : fila) {
                    System.out.print(val + " ");
                }
                System.out.println();
            }
            System.out.println();
        } catch(Exception e) {}
    }
}