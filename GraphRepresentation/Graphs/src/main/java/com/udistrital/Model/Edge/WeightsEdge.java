package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;

public class WeightsEdge<T,U> implements Edge<T,U> {

    private IVertex<T,U> to;
    private IVertex<T,U> from;
    private U value;

    public WeightsEdge(IVertex<T,U> from, IVertex<T,U> to, U value) {
        this.to = to;
        this.from = from;
        this.value = value;
    }

    @Override
    public IVertex<T,U> get(IVertex<T,U> vertex) {
        return vertex == to ?  from : to;
    }
    @Override
    public IVertex<T,U>[] getVertexs() {
        return new IVertex[] {this.from, this.to};
    }

    @Override
    public U getValue() {
        return this.value;
    }

    @Override
    public void setValue(U value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return  to + "-" + from + ":" + value;
    }
    
}
