package com.udistrital.Model.Algorithms.MaximumFlow;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DFS<T, U extends Number & Comparable<U>> {

    public boolean dfs(T source, T sink, Map<T, T> parent, Map<T, List<T>> residualAdj, Map<T, Map<T, Double>> residualCapacity) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);

        while (!stack.isEmpty()) {
            T u = stack.pop();

            if (u.equals(sink)) {
                return true;
            }

            for (T v : residualAdj.get(u)) {
                if (!visited.contains(v) && residualCapacity.get(u).get(v) > 0.0) {
                    stack.push(v);
                    visited.add(v);
                    parent.put(v, u);
                }
            }
        }
        return false;
    }
}
