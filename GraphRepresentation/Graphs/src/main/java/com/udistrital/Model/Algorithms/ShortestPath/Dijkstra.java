package com.udistrital.Model.Algorithms.ShortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Graph.WeightsGraph;
import com.udistrital.Model.Vertex.IVertex;

public class Dijkstra {

public <T, U extends Number> String shortestPath(WeightsGraph<T, U> graph, T value_origin, T value_destiny) {

    IVertex<T, U> origen = graph.getVertex(value_origin);
    IVertex<T, U> destiny = graph.getVertex(value_destiny);

    if (origen == null || destiny == null) {
        return "El origen o el destino no existen en el grafo.";
    }
    
    Map<IVertex<T, U>, Double> distances = new HashMap<>();
    Map<IVertex<T, U>, IVertex<T, U>> predecessors = new HashMap<>();
    Set<IVertex<T, U>> explored = new HashSet<>();
    distances.put(origen, 0.0);
    IVertex<T, U> current = origen;

    while (!current.equals(destiny)) {

        double minDistances = Double.MAX_VALUE;

        for (Map.Entry<IVertex<T, U>, Double> entry : distances.entrySet()) {

            if (!explored.contains(entry.getKey()) && entry.getValue() < minDistances) {
                minDistances = entry.getValue();
                current = entry.getKey();
            }
        }

        if(current != null) {
            explored.add(current);
        }

        for (Edge<T, U> edge : current.getEdges()) {
            IVertex<T, U> neighbor = edge.get(current);

            if(!explored.contains(neighbor)){

            double weightEdge = edge.getValue().doubleValue();
            double newDistance = distances.get(current) + weightEdge;
            double distanceNeighbor = distances.getOrDefault(neighbor, Double.MAX_VALUE);

            if (newDistance < distanceNeighbor) {
                distances.put(neighbor, newDistance);
                predecessors.put(neighbor, current);
            }
        }
            }
    }

    if (!distances.containsKey(destiny)) {
        return "No existe un camino entre " + value_origin + " y " + value_destiny;
    }

    List<T> camino = new ArrayList<>();
    IVertex<T, U> paso = destiny;

    while (paso != null) {
        camino.add(paso.getValue()); 
        paso = predecessors.get(paso);
    }

    Collections.reverse(camino);
    return "Camino más corto: " + camino.toString() + " | Costo total: " + distances.get(destiny);
}
    
}
