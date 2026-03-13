package com.graph.model;

import java.util.ArrayList;

public class IncidenceM<T> implements Graph<T>{
    
    private ArrayList<T> nodes;
    private ArrayList<Edge<T>> edges;
    private int[][] incidenceMatrix;
    
    public IncidenceM() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.incidenceMatrix = new int[0][0];
    }

    @Override
    public void addNode(T node) {
        nodes.add(node);
        if(edges.size() > 0) {
            int newSize = nodes.size();
            int[][] newMatrix = new int[newSize][edges.size()];
            for (int i = 0; i < incidenceMatrix.length; i++) {
                for (int j = 0; j < incidenceMatrix[i].length; j++) {
                    newMatrix[i][j] = incidenceMatrix[i][j];
                }
            }
            incidenceMatrix = newMatrix;
        }else {
            incidenceMatrix = new int[nodes.size()][edges.size()];
        }
           
    }

    @Override
    public void addEdge(T from, T to) {
        edges.add( new Edge<T>(from, to));
        int fromIndex = nodes.indexOf(from);
        int toIndex = nodes.indexOf(to);
        int newSize = edges.size();
        int[][] newMatrix = new int[nodes.size()][newSize];
        for (int i = 0; i < incidenceMatrix.length; i++) {
            for (int j = 0; j < incidenceMatrix[i].length; j++) {
                newMatrix[i][j] = incidenceMatrix[i][j];
            }
        }
        incidenceMatrix = newMatrix;
        try {
            if (fromIndex != -1 && toIndex != -1) {
                incidenceMatrix[fromIndex][newSize - 1] = 1;
                incidenceMatrix[toIndex][newSize - 1] = -1;
            } else {
                System.out.println("Alguno de los nodos no existe :(");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No se pudo agregar la arista al grafo :(");
        }
    }

    @Override
    public void removeNode(T node) {
        int index = nodes.indexOf(node);
        if(index == -1) {
            System.out.println("El nodo no existe :(");
            return;
        }
        
        ArrayList<Edge<T>> edgesToRemove = new ArrayList<>();
        for (int j = 0; j < edges.size(); j++) {
            if (incidenceMatrix[index][j] != 0) {
                edgesToRemove.add(edges.get(j));
            }
        }
        for (Edge<T> edge : edgesToRemove) {
            removeEdge(edge.getFrom(), edge.getTo());
        }
        
        nodes.remove(index);
        int[][] newMatrix = new int[nodes.size()][edges.size()];
        int newRow = 0;
        
        for (int i = 0; i < incidenceMatrix.length; i++) {
            if(i != index) {
                for (int j = 0; j < edges.size(); j++) {
                    newMatrix[newRow][j] = incidenceMatrix[i][j];
                }
                newRow++;
            }
        }
        incidenceMatrix = newMatrix;
    }

    @Override
    public void removeEdge(T from, T to) {
        if(edges.size() == 0) {
            System.out.println("No hay aristas");
            return;
        }else {
            int fromIndex = nodes.indexOf(from);
            int toIndex = nodes.indexOf(to);
            if (fromIndex == -1 || toIndex == -1) {
                System.out.println("Alguno de los nodos no existe");
                return;
            }
            for (int j = 0; j < edges.size(); j++) {
                if (incidenceMatrix[fromIndex][j] == 1 && incidenceMatrix[toIndex][j] == -1) {
                    edges.remove(j);
                    for (int i = 0; i < incidenceMatrix.length; i++) {
                        for (int k = j; k < incidenceMatrix[i].length - 1; k++) {
                            incidenceMatrix[i][k] = incidenceMatrix[i][k + 1];
                        }
                    }
                    int newSize = edges.size();
                    int[][] newMatrix = new int[nodes.size()][newSize];
                    for (int i = 0; i < incidenceMatrix.length; i++) {
                        for (int l = 0; l < newSize; l++) {
                            newMatrix[i][l] = incidenceMatrix[i][l];
                        }
                    }
                    incidenceMatrix = newMatrix;
                    return;
                }
            }
            System.out.println("La arista no existe :(");
        }
    }

    @Override
    public boolean hasNode(T node) {
        return nodes.contains(node);
    }

    @Override
    public boolean hasEdge(T from, T to) {
        int fromIndex = nodes.indexOf(from);
        int toIndex = nodes.indexOf(to);
        if(fromIndex == -1 || toIndex == -1) {
            System.out.println("Alguno de los nodos no existe :(");
            return false;
        } else{
            for (int j = 0; j < edges.size(); j++) {
                if (incidenceMatrix[fromIndex][j] == 1 && incidenceMatrix[toIndex][j] == -1) {
                    return true;
                }
            }
            return false;
        }
    }
}