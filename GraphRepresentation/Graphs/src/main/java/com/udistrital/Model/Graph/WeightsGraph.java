package com.udistrital.Model.Graph;

import java.util.ArrayList;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.WeightsEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class WeightsGraph<T,U> extends Graph<T,U> {

    public WeightsGraph() {
        super();
    }

    @Override
    public void addEdge(T vertexValue1, T vertexValue2, U weightEdge) {
        IVertex<T,U> vertex1 = this.getVertex(vertexValue1);
        IVertex<T, U> vertex2 = this.getVertex(vertexValue2);
        WeightsEdge<T,U> edge = new WeightsEdge<>(vertex1, vertex2, weightEdge);
        this.edges.add(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
  
    }

    @Override
    public void removeEdge(T vertexValue1, T vertexValue2) {
        IVertex<T,U> vertex1 = this.getVertex(vertexValue1);
        IVertex<T,U> vertex2 = this.getVertex(vertexValue2);
        Edge<T,U> edge = new WeightsEdge(vertex1, vertex2, null);
        this.edges.remove(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
    }

    @Override
    public ArrayList<Edge<T,U>> getEdges(T value) {
        return this.edges;
    }

    @Override
    public void addVertex(T value) {
        Vertex<T,U> vertex = new Vertex<>(value);
        this.vertexs.add(vertex);
    }

    @Override
    public void removeVertex(T value) {
        Vertex<T,U> vertex = new Vertex<>(value);
        int index = this.vertexs.indexOf(vertex);
        this.vertexs.remove(index);
    }

    @Override
    public IVertex<T,U> getVertex(T value) {
        return vertexs.get(vertexs.indexOf(new Vertex<>(value)));
    }


    
    
}
