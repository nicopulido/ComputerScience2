package com.udistrital.Model.Algorithms.MaximumFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.udistrital.Model.Graph.Graph;
import com.udistrital.Model.Vertex.IVertex;

public class FordFulkerson<T, U extends Number & Comparable<U>>  {

    private Graph<T,U> graph;  
    private Map<T, List<T>> residualAdj;
    private Map<T, Map<T, Double>> residualCapacity;
    private DFS<T,U> dfs;

    public FordFulkerson(Graph<T,U> graph){
        this.graph = graph;
        this.residualAdj = new HashMap<>();
        this.residualCapacity = new HashMap<>();
        this.dfs = new DFS<>();

        for (IVertex<T, U> vertex : this.graph.getVertexs()) {
            residualAdj.put(vertex.getValue(), new ArrayList<>());
            residualCapacity.put(vertex.getValue(), new HashMap<>());
        }

        for (Map.Entry<IVertex<T, U>, List<IVertex<T, U>>> entry : this.graph.matrixAdya().entrySet()) {
            T u = entry.getKey().getValue();
            
            for (IVertex<T, U> neighbor : entry.getValue()) {
                T v = neighbor.getValue();
                U genericCapacity = this.graph.getValueAdya(entry.getKey(), neighbor);
                double capacity = 0.0;
                if (genericCapacity != null && !genericCapacity.equals(this.graph.neutralValue)) {
                    if (genericCapacity instanceof Number) {
                        capacity = ((Number) genericCapacity).doubleValue();
                    } else {
                        try {
                            capacity = Double.parseDouble(genericCapacity.toString());
                        } catch (NumberFormatException e) {
                            capacity = 0.0;
                        }
                    }
                }
                if (!residualAdj.get(u).contains(v)) {
                    residualAdj.get(u).add(v);
                }
                residualCapacity.get(u).put(v, capacity);
                if (!residualAdj.get(v).contains(u)) {
                    residualAdj.get(v).add(u);
                }
                if (!residualCapacity.get(v).containsKey(u)) {
                    residualCapacity.get(v).put(u, 0.0);
                }
            }
        }
    }

    public double getMaxFlow(T source, T sink) {

        double maxFlow = 0.0;
        Map<T, T> parent = new HashMap<>();
        while (this.dfs.dfs(source, sink, parent, residualAdj, residualCapacity)) {
            
            double pathFlow = Double.MAX_VALUE;
            T current = sink;
            
            while (!current.equals(source)) {
                T prev = parent.get(current);
                pathFlow = Math.min(pathFlow, residualCapacity.get(prev).get(current));
                current = prev;
            }

            current = sink;

            while (!current.equals(source)) {
                T prev = parent.get(current);

                double forwardCapacity = residualCapacity.get(prev).get(current);
                residualCapacity.get(prev).put(current, forwardCapacity - pathFlow);

                double backwardCapacity = residualCapacity.get(current).get(prev);
                residualCapacity.get(current).put(prev, backwardCapacity + pathFlow);
                current = prev;
            }
            maxFlow += pathFlow;
            parent.clear();
        }
        return maxFlow;
    }

}