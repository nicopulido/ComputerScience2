package com.udistrital.Model;

import java.util.ArrayList;
import java.util.Objects;

public class Vertex<T> {
    
    public T value;
    ArrayList<Edge<T>> edges;

    public Vertex(T value) {
        this.value = value;
        this.edges = new ArrayList<Edge<T>>();
    }

    public T getValue() {
        return value;
    }

    public void addEdge(Vertex<T> vertex) {
        Edge<T> edge = new Edge<T>(this, vertex);
        this.edges.add(edge);
    }

    public boolean removeEdge(T edge) {
        int index = this.edges.indexOf(edge);
        if (index != -1) {
        this.edges.remove(index);
        return true;
        }
        else return false;
    }

    public ArrayList<Edge<T>> getEdges() {
        return this.edges;
    }

    public ArrayList<Vertex<T>> getNeighbours() {
        ArrayList<Vertex<T>> neighbours = new ArrayList<>();
        for (Edge<T> edge : this.edges) {
            neighbours.add(edge.getSecond());
        }
        return neighbours;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;

        Vertex<?> other = (Vertex<?>) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
}
