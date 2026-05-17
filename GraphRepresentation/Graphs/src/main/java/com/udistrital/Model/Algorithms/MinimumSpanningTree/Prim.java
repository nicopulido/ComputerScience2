package com.udistrital.Model.Algorithms.MinimumSpanningTree;

import com.udistrital.Model.Graph.*;
import com.udistrital.Model.Vertex.IVertex;
import com.udistrital.Model.Edge.Edge;
import com.udistrital.Model.Edge.WeightsEdge;
import java.util.ArrayList;

public class Prim<T, U extends Number> {

    ArrayList<IVertex<T, U>> visited = new ArrayList<>();
    ArrayList<WeightsEdge<T, U>> edges = new ArrayList<>();
    ArrayList<WeightsEdge<T, U>> mst = new ArrayList<>();

    public void execute(WeightsGraph<T, U> graph, T source) {
        IVertex<T, U> sourceVertex = graph.getVertex(source);
        if (sourceVertex == null) {
            System.out.println("El vertice de origen no existe en el grafo");
            return;
        }

        visited.add(sourceVertex);
        for (Edge<T, U> edge : sourceVertex.getEdges()) {
            edges.add((WeightsEdge<T, U>) edge);
        }
        while (!edges.isEmpty()) {
            WeightsEdge<T, U> minEdge = null;
            IVertex<T, U> nodoNuevo = null;
            for (WeightsEdge<T, U> edge : edges) {
                IVertex<T, U> v1 = edge.getVertexs()[0];
                IVertex<T, U> v2 = edge.getVertexs()[1];
                boolean v1Visitado = visited.contains(v1);
                boolean v2Visitado = visited.contains(v2);

                if (v1Visitado != v2Visitado) { 

                    if (minEdge == null || edge.getValue().doubleValue() < minEdge.getValue().doubleValue()) {
                        minEdge = edge;
                        if(v1Visitado == true){
                            nodoNuevo = v2;
                        }else{
                            nodoNuevo = v1;
                        }
                    }
                }
            }

            if (minEdge == null) {
                break; 
            }

            visited.add(nodoNuevo); 
            mst.add(minEdge);
            edges.remove(minEdge);
            for (Edge<T, U> edge : nodoNuevo.getEdges()) {
                edges.add((WeightsEdge<T, U>) edge);
            }
        }

    }
}