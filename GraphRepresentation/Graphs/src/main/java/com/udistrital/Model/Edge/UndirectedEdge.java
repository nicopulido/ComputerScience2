package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;

public class UndirectedEdge<T> extends Edge<T> {

    private IVertex<T> first;
    private IVertex<T> second;

    public UndirectedEdge(IVertex<T> first, IVertex<T> second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public IVertex<T> get(IVertex<T> vertex) {
        return vertex == first ?  second : first;
    }
    @Override
    public IVertex<T>[] getVertexs() {
        return new IVertex[] {this.first, this.second};
    }
    
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
    
}
