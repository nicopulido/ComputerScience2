package com.udistrital.Model.Algorithms.MaximumFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.udistrital.Model.Graph.Graph;
import com.udistrital.Model.Vertex.IVertex;

public class BFS<T,U> {

    private Graph<T,U> graph;
    public BFS(Graph<T,U> graph) {
        this.graph = graph;
    }

    public List<IVertex<T,U>> bfs(T originValue) {

        IVertex<T,U> origin = this.graph.getVertex(originValue);
        Map<IVertex<T,U>,Boolean> visitedArray = new HashMap<>();

        for (IVertex<T,U> vertex : graph.getVertexs()) {
            visitedArray.put(vertex, false);
        }

        List<IVertex<T,U>> order = new ArrayList<>();
        Queue<IVertex<T,U>> queue = new LinkedList<>();

        visitedArray.put(origin, true);
        queue.offer(origin);

        while (!queue.isEmpty()) {
            IVertex<T,U> vertex = queue.poll();
            order.add(vertex);
            for (IVertex<T,U> neighbor : this.graph.matrixAdya().get(vertex)) {
                    if (!visitedArray.get(neighbor)) {
                        visitedArray.put(neighbor, true);
                        queue.offer(neighbor);
                    }
                }
            }

            return order;
    } 
}