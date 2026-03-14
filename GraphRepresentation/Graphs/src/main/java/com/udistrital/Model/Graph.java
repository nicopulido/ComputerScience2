package com.udistrital.Model;

import java.util.ArrayList;

public class Graph<T> {

    public ArrayList<Vertex<T>> vertexs;
    public ArrayList<Edge<T>> edges;

    public Graph() {
        vertexs = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(T value) {
        Vertex<T> vertex = new Vertex<>(value);
        vertexs.add(vertex);
    }

    public boolean removeVertex(T vertex) {
        int index = this.vertexs.indexOf(vertex);
        if (index != -1) {
        this.edges.remove(index);
        return true;
        }
        else return false;    
    }

    public Vertex<T> getVertex(T value) {
        return vertexs.get(vertexs.indexOf(new Vertex<>(value)));
    }

    public void addEdge(T vertexValue1, T vertexValue2) {

        Vertex<T> vertex1 = this.getVertex(vertexValue1);
        Vertex<T> vertex2 = this.getVertex(vertexValue2);
        vertex1.addEdge(vertex2);
        vertex2.addEdge(vertex1);
        Edge<T> edge = new Edge<>(vertex1, vertex2);
        this.edges.add(edge);
    }

    public void listAdya() {
        for (Vertex<T> vertex : this.vertexs) {
            System.out.println(vertex + "-> { " + vertex.getNeighbours() + " }");
        }
    }

    public void listInci() {
        for (Vertex<T> vertex : this.vertexs) {
            System.out.println(vertex + "-> { " + vertex.getEdges() + " }");
        }
    }

    public int[][] matrixAdya() {
        int[][] matrix = new int[this.vertexs.size()][this.vertexs.size()];

        for (Edge<T> edge : this.edges) {
            int vertex1Index = this.vertexs.indexOf(edge.getFirst());
            int vertex2Index = this.vertexs.indexOf(edge.getSecond());
            matrix[vertex1Index][vertex2Index] = 1;
            matrix[vertex2Index][vertex1Index] = 1;           
        }
        return matrix;
    }

    public int[][] matrixInci() {
        int[][] matrix = new int[this.vertexs.size()][this.edges.size()];

        for (Edge<T> edge : this.edges) {

            int edgeIndex = this.edges.indexOf(edge);
            
            for (Vertex<T> vertex : this.vertexs) {

                int vertexIndex = this.vertexs.indexOf(vertex);

                if(vertex == edge.getFirst() || vertex == edge.getSecond()) {
                    matrix[vertexIndex][edgeIndex] = 1;
                }
                
            }

        }
        return matrix;
    }

}
