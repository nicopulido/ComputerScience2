package com.udistrital.Model.Algorithms.MinimumSpanningTree;

import com.udistrital.Model.Graph.*;
import com.udistrital.Model.Vertex.IVertex;
import com.udistrital.Model.Edge.WeightsEdge;
import java.util.ArrayList;
import java.util.HashMap;
import com.udistrital.Model.Edge.Edge;

public class Krustal<T, U extends Number> {

    ArrayList<WeightsEdge<T, U>> edges = new ArrayList<>();
    public ArrayList<WeightsEdge<T, U>> mst = new ArrayList<>();
    HashMap<IVertex<T,U>, IVertex<T,U>> parent = new HashMap<>();

    public void execute(WeightsGraph<T, U> graph){
        ArrayList<Edge<T, U>> allEdges = graph.getEdges(null);
        for(Edge<T, U> edge : allEdges){
            edges.add((WeightsEdge<T, U>) edge);
        }
        for (WeightsEdge<T, U> edge : edges) {
            parent.put(edge.getVertexs()[0], edge.getVertexs()[0]);
            parent.put(edge.getVertexs()[1], edge.getVertexs()[1]);
        }

        while(!edges.isEmpty()){
                WeightsEdge<T, U> minEdge = null;
                for(WeightsEdge<T, U> edge : edges){
                    if(minEdge == null || edge.getValue().doubleValue() < minEdge.getValue().doubleValue()){
                        minEdge = edge;
                    }
                }

                IVertex<T,U> v1 = minEdge.getVertexs()[0];
                IVertex<T,U> v2 = minEdge.getVertexs()[1];
    
                IVertex<T,U> raiz1 = find(v1);
                IVertex<T,U> raiz2 = find(v2);

                if(!raiz1.equals(raiz2)){
                    mst.add(minEdge);
                    parent.put(raiz1, raiz2);
                }
                edges.remove(minEdge);
        }


    }

    public IVertex<T,U> find(IVertex<T,U> vertex){
        if(parent.get(vertex) == vertex){
            return vertex;
        }
        return find(parent.get(vertex));
    }

}
