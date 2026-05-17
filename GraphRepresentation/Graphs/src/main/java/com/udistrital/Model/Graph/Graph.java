package com.udistrital.Model.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Vertex.IVertex;


public abstract class Graph<T,U> {

    public ArrayList<IVertex<T,U>> vertexs;
    public ArrayList<Edge<T,U>> edges;
    public U neutralValue;
    public U infiniteValue;

    public Graph() {
        this.vertexs = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public abstract void addEdge(T vertexValue1, T vertexValue2, U edgeValue);
    public abstract void removeEdge(T vertexValue1, T vertexValue2);
    public abstract ArrayList<Edge<T,U>> getEdges(T vertex);
    public abstract Edge<T,U> getEdge(IVertex<T,U> vertex1, IVertex<T,U> vertex2);
    public abstract void addVertex(T value);
    public abstract void removeVertex(T value);
    public abstract IVertex<T,U> getVertex(T value);
    public abstract U getValueAdya(IVertex<T,U> vertex1 , IVertex<T,U> vertex2);
    public abstract void setValueAdya(IVertex<T,U> vertex1 , IVertex<T,U> vertex2, U value);

    public ArrayList<IVertex<T,U>> getVertexs() {
        return this.vertexs;
    }

    public int vertexSize() {
        return this.vertexs.size();
    }

    public void listAdya() {
        for (IVertex<T,U> vertex : this.vertexs) {
            System.out.println(vertex + "-> { " + vertex.getNeighbours() + " }");
        }
    }

    public void listInci() {
        for (IVertex<T,U> vertex : this.vertexs) {
            System.out.println(vertex + "-> { " + vertex.getEdges() + " }");
        }
    }

    public abstract Map<IVertex<T,U>,List<IVertex<T,U>>> matrixAdya();

    public int[][] matrixInci() {
        int[][] matrix = new int[this.vertexs.size()][this.edges.size()];
        for (Edge<T,U> edge : this.edges) {
            int edgeIndex = this.edges.indexOf(edge);
            for (IVertex<T,U> vertex : this.vertexs) {
                int vertexIndex = this.vertexs.indexOf(vertex);
                if(vertex == edge.getVertexs()[0] || vertex == edge.getVertexs()[1]) {
                    matrix[vertexIndex][edgeIndex] = 1;
                }

            }
        }
        return matrix;
    }
}
