package com.graph.model;

import java.util.ArrayList;

public class AdjacencyM<T> implements Graph<T> {
    
    private ArrayList<T> nodes;
    private int[][] adjacencyMatrix;

    public AdjacencyM() {
        this.nodes = new ArrayList<>();
        this.adjacencyMatrix = new int[0][0];
    }

    @Override
    public void addNode(T node) {
        nodes.add(node);
        int newSize = nodes.size();
        int[][] newMatrix = new int[newSize][newSize];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                newMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        adjacencyMatrix = newMatrix;
    }

    @Override
    public void addEdge(T from, T to) {
        int fromIndex = nodes.indexOf(from);
        int toIndex = nodes.indexOf(to);
        try {
            adjacencyMatrix[fromIndex][toIndex] = 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Alguno de los nodos no existe");
        }
    }

    @Override
    public void removeNode(T node) {
        int index = nodes.indexOf(node);
        try{
            nodes.remove(index);
            int newSize = nodes.size();
            int[][] newMatrix = new int[newSize][newSize];
            int newRow = 0;
            
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if(i != index) {
                    int newCol = 0;
                    for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                        if(j != index) {
                            newMatrix[newRow][newCol] = adjacencyMatrix[i][j];
                            newCol++;
                        }
                    }
                    newRow++;
                }
            }
            adjacencyMatrix = newMatrix;
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("El nodo no existe");
        }
    }

    @Override
    public void removeEdge(T from, T to) {
        int fromIndex = nodes.indexOf(from);
        int toIndex = nodes.indexOf(to);
        try {
            adjacencyMatrix[fromIndex][toIndex] = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Alguno de los nodos no existe");
        }
    }

    @Override
    public boolean hasNode(T node) {
        return nodes.contains(node);
    }

    @Override
    public boolean hasEdge(T from, T to) {
        int indexFrom = nodes.indexOf(from);
        int indexTo = nodes.indexOf(to);
        try {
            if(adjacencyMatrix[indexFrom][indexTo] == 1){
                return true;
            }else {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Alguno de los nodos no existe.");
            return false;
        }
    }

}
