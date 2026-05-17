package com.udistrital.Model.Algorithms.MaximumFlow;

import java.util.HashMap;
import java.util.Map;

import com.udistrital.Model.Graph.WeightsGraph;
import com.udistrital.Model.Vertex.IVertex;

public class EdmondsKarp<T, U extends Number & Comparable<U>> {

    private final WeightsGraph<T, U> graph;
    private final BFS<T, U> bfs;

    public EdmondsKarp(WeightsGraph<T, U> graph) {
        this.graph = graph;
        this.bfs = new BFS<>(graph);
    }

    public double maxFlow(T sourceValue, T sinkValue) {

        IVertex<T, U> source = this.graph.getVertex(sourceValue);
        IVertex<T, U> sink   = this.graph.getVertex(sinkValue);

        Map<IVertex<T, U>, Map<IVertex<T, U>, Double>> residual = buildResidualGraph();
        double totalFlow = 0.0;
        Map<IVertex<T, U>, IVertex<T, U>> parent = new HashMap<>();

        while (this.bfs.bfs(source, sink, residual, parent)) {

            double pathFlow = Double.MAX_VALUE;
            IVertex<T, U> current = sink;

            while (!current.equals(source)) {
                IVertex<T, U> prev = parent.get(current);
                double cap = residual.get(prev).getOrDefault(current, 0.0);
                pathFlow = Math.min(pathFlow, cap);
                current = prev;
            }

            current = sink;
            while (!current.equals(source)) {
                IVertex<T, U> prev = parent.get(current);
                double newForward = residual.get(prev).getOrDefault(current, 0.0) - pathFlow;
                residual.get(prev).put(current, newForward);
                double newBackward = residual.get(current).getOrDefault(prev, 0.0) + pathFlow;
                residual.get(current).put(prev, newBackward);
                current = prev;
            }
            totalFlow += pathFlow;
            parent.clear();
        }

        return totalFlow;
    }

    private Map<IVertex<T, U>, Map<IVertex<T, U>, Double>> buildResidualGraph() {
        Map<IVertex<T, U>, Map<IVertex<T, U>, Double>> residual = new HashMap<>();

        for (IVertex<T, U> v : graph.getVertexs()) {
            residual.put(v, new HashMap<>());
        }

        for (IVertex<T, U> u : graph.getVertexs()) {
            for (IVertex<T, U> v : graph.getVertexs()) {
                if (u.equals(v)) continue;
                U capacity = graph.getValueAdya(u, v);
                if (capacity != null && capacity.doubleValue() > 0) {
                    residual.get(u).put(v, capacity.doubleValue());
                    residual.get(v).putIfAbsent(u, 0.0);
                }
            }
        }
        return residual;
    }

}
