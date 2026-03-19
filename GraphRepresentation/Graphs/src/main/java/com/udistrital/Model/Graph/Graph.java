package com.udistrital.Model.Graph;

import java.util.ArrayList;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Vertex.IVertex;


public abstract class Graph<T> {

    public ArrayList<IVertex<T>> vertexs;
    public ArrayList<Edge<T>> edges;


    public Graph() {
        this.vertexs = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public abstract void addEdge(T vertexValue1, T vertexValue2);
    public abstract void removeEdge(T vertexValue1, T vertexValue2);
    public abstract ArrayList<Edge<T>> getEdges(T vertex);
    public abstract void addVertex(T value);
    public abstract void removeVertex(T value);
    public abstract IVertex<T> getVertex(T value);

    public void listAdya() {
        for (IVertex<T> vertex : this.vertexs) {
            System.out.println(vertex + "-> { " + vertex.getNeighbours() + " }");
        }
    }

    public void listInci() {
        for (IVertex<T> vertex : this.vertexs) {
            System.out.println(vertex + "-> { " + vertex.getEdges() + " }");
        }
    }

    public int[][] matrixAdya() {
        int[][] matrix = new int[this.vertexs.size()][this.vertexs.size()];

        for (Edge<T> edge : this.edges) {
            int vertex1Index = this.vertexs.indexOf(edge.getVertexs()[0]);
            int vertex2Index = this.vertexs.indexOf(edge.getVertexs()[1]);
            matrix[vertex1Index][vertex2Index] = 1;
            matrix[vertex2Index][vertex1Index] = 1;
        }
        return matrix;
    }

    public int[][] matrixInci() {
        int[][] matrix = new int[this.vertexs.size()][this.edges.size()];

        for (Edge<T> edge : this.edges) {

            int edgeIndex = this.edges.indexOf(edge);

            for (IVertex<T> vertex : this.vertexs) {

                int vertexIndex = this.vertexs.indexOf(vertex);

                if(vertex == edge.getVertexs()[0] || vertex == edge.getVertexs()[1]) {
                    matrix[vertexIndex][edgeIndex] = 1;
                }

            }
        }
        return matrix;
    }
}
