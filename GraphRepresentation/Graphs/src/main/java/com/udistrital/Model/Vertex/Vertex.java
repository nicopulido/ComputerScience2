package com.udistrital.Model.Vertex;

import java.util.ArrayList;
import com.udistrital.Model.Edge.Edge;

public class Vertex<T,U> extends IVertex<T,U> {

    ArrayList<Edge<T,U>> edges;

    public Vertex(T value) {
        this.value = value;
        this.edges = new ArrayList<Edge<T,U>>();
    }

    @Override
    public void addEdge(Edge<T,U> edge) {
        // TODO: add exception
        this.edges.add(edge);

    }

    @Override
    public void removeEdge(Edge<T,U> edge) {
        //TODO: add exception
        int index = this.edges.indexOf(edge);
        this.edges.remove(index);
    }

    @Override
    public ArrayList<Edge<T,U>> getEdges() {
        return this.edges;
    }

    @Override
    public ArrayList<IVertex<T,U>> getNeighbours() {
        ArrayList<IVertex<T,U>> neighbours = new ArrayList<>();
        for (Edge<T,U> edge : this.edges) {
            neighbours.add(edge.get((IVertex) this));
        }
        return neighbours;
    }
    
}
