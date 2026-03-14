package com.udistrital.Model;

public class Edge<T> {
    private Vertex<T> first;
    private Vertex<T> second;

    public Edge(Vertex<T> first, Vertex<T> second) {
        this.first = first;
        this.second = second;
    }

    public Vertex<T> getFirst() {
        return first;
    }

    public Vertex<T> getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}