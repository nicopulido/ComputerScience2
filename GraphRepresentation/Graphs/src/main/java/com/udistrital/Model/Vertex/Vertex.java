package com.udistrital.Model.Vertex;

import java.util.ArrayList;
import com.udistrital.Model.Edge.Edge;

public class Vertex<T> extends IVertex<T> {

    ArrayList<Edge<T>> edges;

    public Vertex(T value) {
        this.value = value;
        this.edges = new ArrayList<Edge<T>>();
    }

    @Override
    public void addEdge(Edge<T> edge) {
        // TODO: add exception
        this.edges.add(edge);

    }

    @Override
    public void removeEdge(Edge<T> edge) {
        //TODO: add exception
        int index = this.edges.indexOf(edge);
        this.edges.remove(index);
    }

    @Override
    public ArrayList<Edge<T>> getEdges() {
        return this.edges;
    }

    @Override
    public ArrayList<IVertex<T>> getNeighbours() {
        ArrayList<IVertex<T>> neighbours = new ArrayList<>();
        for (Edge<T> edge : this.edges) {
            neighbours.add(edge.get(this));
        }
        return neighbours;
    }
    
}
