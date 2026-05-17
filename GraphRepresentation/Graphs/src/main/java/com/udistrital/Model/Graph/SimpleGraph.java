package com.udistrital.Model.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.SimpleEdge;
import com.udistrital.Model.Vertex.Vertex;
import com.udistrital.Model.Vertex.IVertex;

public class SimpleGraph<T> extends Graph<T,Integer> {

    private Map<IVertex<T,Integer>,List<IVertex<T,Integer>>> adyacencyMap;

    public SimpleGraph() {
        super();
        this.adyacencyMap = new HashMap<>();
    }


    public void addEdge(T vertexValue1, T vertexValue2) {
        IVertex<T,Integer> vertex1 = this.getVertex(vertexValue1);
        IVertex<T, Integer> vertex2 = this.getVertex(vertexValue2);
        Edge<T,Integer> edge = new SimpleEdge<>(vertex1, vertex2);
        this.edges.add(edge);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
        adyacencyMap.get(vertex1).add(vertex2);
  
    }

    public Edge<T,Integer> getEdge(IVertex<T,Integer> vertex1, IVertex<T,Integer> vertex2) {
        Edge<T,Integer> tarjet = null;
        for (Edge<T,Integer> edge : vertex1.getEdges()) {
            if(edge.get(vertex1).equals(vertex2)) {
                tarjet = edge;
            }  
        }
        return tarjet;
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

    @Override
    public Map<IVertex<T,Integer>,List<IVertex<T,Integer>>> matrixAdya() {
        return this.adyacencyMap;
    }

        public void printAdya() {

    List<IVertex<T,Integer>> vertices = new ArrayList<>(adyacencyMap.keySet());
    for (IVertex<T,Integer> v : vertices) {
        System.out.print("  " + v + "  "); 
    }
    System.out.println();
    for (IVertex<T,Integer> v : vertices) {
        System.out.print("  " + v + "  ");
        for (IVertex<T,Integer> v2 : this.getVertexs()) {
            if (v.equals(v2) || this.getEdge(v, v2) == null) {
            System.out.print(0); 
            } else {
            System.out.print(1 + " ");
            } 
        }
        System.out.println();
    }
    }

    @Override
    public void addEdge(T vertexValue1, T vertexValue2, Integer edgeValue) {
        throw new UnsupportedOperationException("Simple graph do not support weight edges");
    }


    @Override
    public Integer getValueAdya(IVertex<T, Integer> vertex1, IVertex<T, Integer> vertex2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValueAdya'");
    }


    @Override
    public void setValueAdya(IVertex<T, Integer> vertex1, IVertex<T, Integer> vertex2, Integer value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValueAdya'");
    }
    
}
