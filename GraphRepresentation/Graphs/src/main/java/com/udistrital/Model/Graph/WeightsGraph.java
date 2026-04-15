package com.udistrital.Model.Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.WeightsEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class WeightsGraph<T,U> extends Graph<T,U> {

    private U infiniteValue;
    private U neutralValue;

    public WeightsGraph(U infinityValue, U neutral) {
        super();
        this.infiniteValue = infinityValue;
        this.neutralValue = neutral;
    }

    public U getNeutral() {
        return this.neutralValue;
    }

    public U getInfinity() {
        return this.infiniteValue;
    }

    @Override
    public void addEdge(T vertexValue1, T vertexValue2, U weightEdge) {
        IVertex<T,U> vertex1 = this.getVertex(vertexValue1);
        IVertex<T, U> vertex2 = this.getVertex(vertexValue2);
        WeightsEdge<T,U> edge = new WeightsEdge<>(vertex1, vertex2, weightEdge);
        if(vertex1 == vertex2) {
            vertex1.addEdge(edge);
        }
        else {
            vertex1.addEdge(edge);
            vertex2.addEdge(edge);
        }
        this.edges.add(edge);  
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

    @Override
    public U[][] matrixAdya() {
        U[][] matrix = this.createArray(this.vertexs.size(), this.neutralValue);

        for (Edge<T,U> edge : this.edges) {
            int vertex1Index = this.vertexs.indexOf(edge.getVertexs()[0]);
            int vertex2Index = this.vertexs.indexOf(edge.getVertexs()[1]);
            matrix[vertex1Index][vertex2Index] = edge.getValue();
            matrix[vertex2Index][vertex1Index] = edge.getValue();
        }
        return matrix;
    }

    private static <U> U[][] createArray(int size, U defaultValue) 
    {
    @SuppressWarnings("unchecked")
    U[][] array = (U[][]) Array.newInstance(defaultValue.getClass(), size, size);
    for (U[] row : array) {
        Arrays.fill(row, defaultValue);
    }
    return array;
    }
    
    
}
