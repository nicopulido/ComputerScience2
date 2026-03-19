package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;

public abstract class Edge<T> {

public T value;

public T getValue() {
        return value;
    }

public void setValue(T value) {
        this.value = value;
    }

public abstract IVertex<T> get(IVertex<T> vertex);
public abstract IVertex<T>[] getVertexs();

}
