package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;

public class SimpleEdge<T> implements Edge<T,Integer> {

    private IVertex<T,Integer> to;
    private IVertex<T,Integer> from;

    public SimpleEdge(IVertex<T,Integer> from, IVertex<T,Integer> to) {
        this.to = to;
        this.from = from;
    }

    @Override
    public Integer getValue() {
        throw new UnsupportedOperationException("Simple edges do not have weights");
    }

    @Override
    public void setValue(Integer value) {
        throw new UnsupportedOperationException("Simple edges do not have weights");
    }
    @Override
    public IVertex<T, Integer> get(IVertex<T, Integer> vertex) {
        return vertex == to ?  from : to;
    }
    @Override
    public IVertex<T, Integer>[] getVertexs() {
        return new IVertex[] {this.from, this.to};
    }

    @Override
    public String toString() {
        return  to + "-" + from;
    }
    
}
