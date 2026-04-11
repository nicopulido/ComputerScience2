package com.udistrital.Model.Graph;

import java.util.ArrayList;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.SimpleEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class SimpleGraph<T> extends Graph<T,Integer> {

    public SimpleGraph() {
        super();
    }


    public void addEdge(T vertexValue1, T vertexValue2) {
        IVertex<T,Integer> vertex1 = this.getVertex(vertexValue1);
        IVertex<T, Integer> vertex2 = this.getVertex(vertexValue2);
        Edge<T,Integer> edge = new SimpleEdge<>(vertex1, vertex2);
        this.edges.add(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
  
    }

    @Override
    public void removeEdge(T vertexValue1, T vertexValue2) {
        IVertex<T,Integer> vertex1 = this.getVertex(vertexValue1);
        IVertex<T,Integer> vertex2 = this.getVertex(vertexValue2);
        Edge<T,Integer> edge = new SimpleEdge<>(vertex1, vertex2);
        this.edges.remove(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
    }

    @Override
    public ArrayList<Edge<T,Integer>> getEdges(T value) {
        return this.edges;
    }

    @Override
    public void addVertex(T value) {
        IVertex<T,Integer> vertex = new Vertex<>(value);
        this.vertexs.add(vertex);
    }

    @Override
    public void removeVertex(T value) {
        IVertex<T,Void> vertex = new Vertex<>(value);
        int index = this.vertexs.indexOf(vertex);
        this.vertexs.remove(index);
    }

    @Override
    public IVertex<T,Integer> getVertex(T value) {
        return vertexs.get(vertexs.indexOf(new Vertex<>(value)));
    }

    public Integer[][] matrixAdya() {
        Integer[][] matrix = new Integer[this.vertexs.size()][this.vertexs.size()];

        for (Edge<T,Integer> edge : this.edges) {
            int vertex1Index = this.vertexs.indexOf(edge.getVertexs()[0]);
            int vertex2Index = this.vertexs.indexOf(edge.getVertexs()[1]);
            matrix[vertex1Index][vertex2Index] = 1;
            matrix[vertex2Index][vertex1Index] = 1;
        }
        return matrix;
    }

    @Override
    public void addEdge(T vertexValue1, T vertexValue2, Integer edgeValue) {
        throw new UnsupportedOperationException("Simple graph do not support weight edges");
    }
    
}
