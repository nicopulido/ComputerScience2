package com.graph.model;

public interface Graph <T> {
    
    public void addNode(T node);
    public void addEdge(T from, T to);
    public void removeNode(T node);
    public void removeEdge(T from, T to);
    public boolean hasNode(T node);
    public boolean hasEdge(T from, T to);

}
