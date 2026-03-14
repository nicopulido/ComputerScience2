package com.udistrital.Controller;

import java.util.ArrayList;

import com.udistrital.Model.Edge;
import com.udistrital.Model.Graph;
import com.udistrital.Model.Vertex;

public class GraphController {
    private Graph<String> graph;

    public GraphController() {
        this.graph = new Graph<>();
    }

    public void addVertex(String value) {
        this.graph.addVertex(value);
    }

    public void removeVertex(String value) {
        this.graph.removeVertex(value);
    }

    public boolean vertexExists(String value) {
        for (Vertex<String> v : this.graph.vertexs) {
            if (v.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean addEdge(String vertexValue1, String vertexValue2) {
        // Validar que ambos vértices existan
        if (!vertexExists(vertexValue1)) {
            return false;
        }
        if (!vertexExists(vertexValue2)) {
            return false;
        }
        
        try {
            this.graph.addEdge(vertexValue1, vertexValue2);
            return true;
        } catch (Exception e) {
            System.err.println("Error al agregar arista: " + e.getMessage());
            return false;
        }
    }

    public Graph<String> getGraph() {
        return this.graph;
    }

    public ArrayList<Vertex<String>> getVertexs() {
        return this.graph.vertexs;
    }

    public ArrayList<Edge<String>> getEdges() {
        return this.graph.edges;
    }

    public int[][] getAdjacencyMatrix() {
        return this.graph.matrixAdya();
    }

    public int[][] getIncidenceMatrix() {
        return this.graph.matrixInci();
    }

    public String getAdjacencyList() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<String> vertex : this.graph.vertexs) {
            sb.append(vertex.getValue()).append(" -> { ");
            ArrayList<Vertex<String>> neighbours = vertex.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                sb.append(neighbours.get(i).getValue());
                if (i < neighbours.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }\n");
        }
        return sb.toString();
    }

    public String getIncidenceList() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<String> vertex : this.graph.vertexs) {
            sb.append(vertex.getValue()).append(" -> { ");
            ArrayList<Edge<String>> edges = vertex.getEdges();
            for (int i = 0; i < edges.size(); i++) {
                sb.append(edges.get(i).toString());
                if (i < edges.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }\n");
        }
        return sb.toString();
    }

    public void displayAdjacentList() {
        this.graph.listAdya();
    }

    public void displayIncidentList() {
        this.graph.listInci();
    }

    public void clearGraph() {
        this.graph = new Graph<>();
    }

    public Vertex<String> getVertex(String value) {
        for (Vertex<String> v : this.graph.vertexs) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
