package com.udistrital.Model.Graph;

import java.util.ArrayList;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.SimpleEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class SimpleGraph<T> extends Graph<T,Void> {

    public SimpleGraph() {
        super();
    }


    public void addEdge(T vertexValue1, T vertexValue2) {
        IVertex<T,Void> vertex1 = this.getVertex(vertexValue1);
        IVertex<T, Void> vertex2 = this.getVertex(vertexValue2);
        Edge<T,Void> edge = new SimpleEdge<>(vertex1, vertex2);
        this.edges.add(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
  
    }

    @Override
    public void removeEdge(T vertexValue1, T vertexValue2) {
        IVertex<T,Void> vertex1 = this.getVertex(vertexValue1);
        IVertex<T,Void> vertex2 = this.getVertex(vertexValue2);
        Edge<T,Void> edge = new SimpleEdge<>(vertex1, vertex2);
        this.edges.remove(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
    }

    @Override
    public ArrayList<Edge<T,Void>> getEdges(T value) {
        return this.edges;
    }

    @Override
    public void addVertex(T value) {
        IVertex<T,Void> vertex = new Vertex<>(value);
        this.vertexs.add(vertex);
    }

    @Override
    public void removeVertex(T value) {
        IVertex<T,Void> vertex = new Vertex<>(value);
        int index = this.vertexs.indexOf(vertex);
        this.vertexs.remove(index);
    }

    @Override
    public IVertex<T,Void> getVertex(T value) {
        return vertexs.get(vertexs.indexOf(new Vertex<>(value)));
    }


    @Override
    public void addEdge(T vertexValue1, T vertexValue2, Void edgeValue) {
        throw new UnsupportedOperationException("Simple graph do not support weight edges");
    }

    @Override
    public Void getNeutral() {
        throw new UnsupportedOperationException("Simple graph do not support neutral values");
    }
    @Override
    public void setNeutral(Void value) {
        throw new UnsupportedOperationException("Simple graph do not support neutral values");
    }

    @Override
    public Void getInfinity() {
        throw new UnsupportedOperationException("Simple graph do not support neutral values");
    }
    @Override
    public void setInfinity(Void value) {
        throw new UnsupportedOperationException("Simple graph do not support neutral values");
    }
    
    
    
}
