package com.udistrital.Model.Graph;

import java.util.ArrayList;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.UndirectedEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class UndirectedGraph<T> extends Graph<T> {

    public UndirectedGraph() {
        super();
    }

    @Override
    public void addEdge(T vertexValue1, T vertexValue2) {
        IVertex<T> vertex1 = this.getVertex(vertexValue1);
        IVertex<T> vertex2 = this.getVertex(vertexValue2);
        Edge<T> edge = new UndirectedEdge<>(vertex1, vertex2);
        this.edges.add(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
  
    }

    @Override
    public void removeEdge(T vertexValue1, T vertexValue2) {
        IVertex<T> vertex1 = this.getVertex(vertexValue1);
        IVertex<T> vertex2 = this.getVertex(vertexValue2);
        Edge<T> edge = new UndirectedEdge<>(vertex1, vertex2);
        this.edges.remove(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
    }

    @Override
    public ArrayList<Edge<T>> getEdges(T value) {
        return this.edges;
    }

    @Override
    public void addVertex(T value) {
        Vertex<T> vertex = new Vertex<>(value);
        this.vertexs.add(vertex);
    }

    @Override
    public void removeVertex(T value) {
        Vertex<T> vertex = new Vertex<>(value);
        int index = this.vertexs.indexOf(vertex);
        this.vertexs.remove(index);
    }

    @Override
    public IVertex<T> getVertex(T value) {
        return vertexs.get(vertexs.indexOf(new Vertex<>(value)));
    }
    
    
}
