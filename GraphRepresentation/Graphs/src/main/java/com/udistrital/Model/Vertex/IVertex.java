package com.udistrital.Model.Vertex;

import java.util.ArrayList;
import java.util.Objects;
import com.udistrital.Model.Edge.Edge;

public abstract class IVertex<T,U> {

    public T value;

    public abstract void addEdge(Edge<T,U> edge);
    public abstract void removeEdge(Edge<T,U> edge);
    public abstract ArrayList<Edge<T, U>> getEdges();
    public abstract ArrayList<IVertex<T,U>> getNeighbours();

    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        IVertex<?,?> other = (IVertex<?,?>) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
}
