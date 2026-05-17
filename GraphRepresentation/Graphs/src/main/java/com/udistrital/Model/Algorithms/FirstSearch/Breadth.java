package com.udistrital.Model.Algorithms.FirstSearch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.udistrital.Model.Graph.Graph;
import com.udistrital.Model.Vertex.IVertex;

public class Breadth<T, U extends Number & Comparable<U>> implements FirstSearch<T, U> {
    
    private Graph<T,U> graph;
    public Breadth(Graph<T,U> graph) {
        this.graph = graph;
    }

	@Override
	public boolean isPath(IVertex<T, U> source, IVertex<T, U> sink,Map<IVertex<T, U>, Map<IVertex<T, U>, Double>> residual, Map<IVertex<T, U>, IVertex<T, U>> parent) {
        Map<IVertex<T, U>, Boolean> visited = new HashMap<>();
        for (IVertex<T, U> v : graph.getVertexs()) {
            visited.put(v, false);
        }
        Queue<IVertex<T, U>> queue = new LinkedList<>();
        visited.put(source, true);
        parent.put(source, null);
        queue.offer(source);

        while (!queue.isEmpty()) {
            IVertex<T, U> u = queue.poll();

            for (IVertex<T, U> v : graph.getVertexs()) {
                if (!visited.get(v)) {
                    double cap = residual.get(u).getOrDefault(v, 0.0);
                    if (cap > 0) {
                        visited.put(v, true);
                        parent.put(v, u);
                        if (v.equals(sink)) return true;
                        queue.offer(v);
                    }
                }
            }
        }

        return false;
	}
    
}
