package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;

public class DirectedEdge<T> extends Edge<T> {

    private IVertex<T> from;
    private IVertex<T> to;

    public DirectedEdge(IVertex<T> from, IVertex<T> to) {
        this.from = from;
        this.to = to;
    }

    public IVertex<T> getFrom() {
        return from;
    }

    public IVertex<T> getTo() {
        return to;
    }
    @Override
    public IVertex<T> get(IVertex<T> vertex) {
        return vertex == to ?  to : from;
    }
    @Override
    public IVertex<T>[] getVertexs() {
        return new IVertex[] {this.from, this.to};
    }

    @Override
    public String toString() {
        return "(" + this.from + "->" + this.to + ")";
    }
    
}
