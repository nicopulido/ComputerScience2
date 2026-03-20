package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;

public class SimpleEdge<T> implements Edge<T,Void> {

    private IVertex<T,Void> to;
    private IVertex<T,Void> from;

    public SimpleEdge(IVertex<T,Void> from, IVertex<T,Void> to) {
        this.to = to;
        this.from = from;
    }

    @Override
    public Void getValue() {
        throw new UnsupportedOperationException("Simple edges do not have weights");
    }

    @Override
    public void setValue(Void value) {
        throw new UnsupportedOperationException("Simple edges do not have weights");
    }
    @Override
    public IVertex<T, Void> get(IVertex<T, Void> vertex) {
        return vertex == to ?  from : to;
    }
    @Override
    public IVertex<T, Void>[] getVertexs() {
        return new IVertex[] {this.from, this.to};
    }

    @Override
    public String toString() {
        return  to + "-" + from;
    }
    
}
