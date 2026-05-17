package com.udistrital.Model.Graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.WeightsEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class WeightsGraph<T,U> extends Graph<T,U> {

    private U infiniteValue;
    private U neutralValue;
    private Map<IVertex<T,U>,List<IVertex<T,U>>> adyacencyMap;

    public WeightsGraph(U infinityValue, U neutral) {
        super();
        this.infiniteValue = infinityValue;
        this.neutralValue = neutral;
        this.adyacencyMap = new HashMap<>();
        
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
        adyacencyMap.get(vertex1).add(vertex2);

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
    public Edge<T,U> getEdge(IVertex<T,U> vertex1, IVertex<T,U> vertex2) {
        Edge<T,U> tarjet = null;
        for (Edge<T,U> edge : vertex1.getEdges()) {
            if(edge.get(vertex1).equals(vertex2)) {
                tarjet = edge;
            }
            
        }
        return tarjet;
    }

    @Override
    public void addVertex(T value) {
        Vertex<T,U> vertex = new Vertex<>(value);
        this.vertexs.add(vertex);
        this.adyacencyMap.put(vertex, new LinkedList<>());
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
    public U getValueAdya(IVertex<T,U> vertex1 , IVertex<T,U> vertex2){

        List<IVertex<T,U>> adyaLinkedList = this.adyacencyMap.get(vertex1);
        if (adyaLinkedList == null) return this.neutralValue;
        for (IVertex<T,U> vertex : adyaLinkedList) {

            if(vertex2.equals(vertex)) {
                return this.getEdge(vertex1, vertex).getValue();
            } 
        }
        return this.neutralValue;
    }

    @Override
    public void setValueAdya(IVertex<T,U> vertex1 , IVertex<T,U> vertex2, U value){

        List<IVertex<T,U>> adyaLinkedList = this.adyacencyMap.get(vertex1);
        for (IVertex<T,U> vertex : adyaLinkedList) {

            if(vertex2.equals(vertex)) {
                this.getEdge(vertex1, vertex).setValue(value);
            } 
        }
    }

    public void printAdya() {

    List<IVertex<T,U>> vertices = new ArrayList<>(adyacencyMap.keySet());
    for (IVertex<T,U> v : vertices) {
        System.out.print("  " + v + "  "); 
    }
    System.out.println();
    for (IVertex<T,U> v : vertices) {
        System.out.print("  " + v + "  ");
        for (IVertex<T,U> v2 : this.getVertexs()) {
            if (v.equals(v2) || this.getEdge(v, v2) == null) {
            System.out.print(this.neutralValue + " "); 
            } else {
            System.out.print(this.getEdge(v, v2).getValue() + " ");
            } 
        }
        System.out.println();
    }
    }

    /* Este bloque de codigo ya no sirve para nada pero me costo mucho hacerlo asi que no quiero borrarlo :3

    private static <U> U[][] createArray(int size, U defaultValue) 
    {
    @SuppressWarnings("unchecked")
    U[][] array = (U[][]) Array.newInstance(defaultValue.getClass(), size, size);
    for (U[] row : array) {
        Arrays.fill(row, defaultValue);
    }
    return array;
    }
    */

    @Override
    public Map<IVertex<T,U>,List<IVertex<T,U>>> matrixAdya() {
        return this.adyacencyMap;
    }
    
    
}
