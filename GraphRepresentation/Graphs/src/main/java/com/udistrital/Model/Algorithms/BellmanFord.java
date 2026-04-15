package com.udistrital.Model.Algorithms;

import com.udistrital.Model.Graph.WeightsGraph;
import com.udistrital.Model.Vertex.IVertex;
import com.udistrital.Model.Edge.Edge;
import java.util.HashMap;
import java.util.Map;

public class BellmanFord<T, U extends Number> {
    
    public void execute(WeightsGraph<T, U> graph, T source){

        IVertex<T, U> sourceVertex = graph.getVertex(source);
        if (sourceVertex == null){
            System.out.println("El vertice de origen no existe en el grafo");
            return;
        }
        Map<IVertex<T, U>, Double> distances = new HashMap<>();
        Map<IVertex<T, U>, IVertex<T, U>> predecessors = new HashMap<>();

        for (IVertex<T, U> vertex : graph.vertexs) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
            predecessors.put(vertex, null);
        }
        distances.put(sourceVertex, 0.0);

        int V = graph.vertexs.size();

        for (int i = 1; i < V; i++){

            for(Edge<T, U> edge : graph.edges){
                IVertex<T, U> u = edge.getVertexs()[0];
                IVertex<T, U> v = edge.getVertexs()[1];
                double weight = edge.getValue().doubleValue();

                if (distances.get(u) != Double.POSITIVE_INFINITY && distances.get(u) + weight < distances.get(v)){
                    distances.put(v, distances.get(u) + weight);
                    predecessors.put(v, u);
                }
            }

        }

        for (Edge<T, U> edge : graph.edges){
            IVertex<T, U> u = edge.getVertexs()[0];
            IVertex<T, U> v = edge.getVertexs()[1];
            double weight = edge.getValue().doubleValue();

            if (distances.get(u) != Double.POSITIVE_INFINITY && distances.get(u) + weight < distances.get(v)){
                System.out.println("El grafo contiene un ciclo de peso negativo");
                return;
            }
        }

        System.out.println("Distancias desde el vertice de origen: " + source);
        for (IVertex<T, U> vertex : graph.vertexs) {
            System.out.println("Vertice: " + vertex.getValue() + ", Distancia: " + distances.get(vertex));
        }

    }


}
