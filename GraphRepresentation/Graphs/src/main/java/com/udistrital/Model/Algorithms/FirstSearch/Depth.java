package com.udistrital.Model.Algorithms.FirstSearch;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.udistrital.Model.Vertex.IVertex;

public class Depth<T, U extends Number & Comparable<U>> implements FirstSearch<T, U> {

    @Override
    public boolean isPath(IVertex<T, U> source, IVertex<T, U> sink, Map<IVertex<T, U>, Map<IVertex<T, U>, Double>> residual, Map<IVertex<T, U>, IVertex<T, U>> parent) {
        Set<IVertex<T, U>> visited = new HashSet<>();
        Stack<IVertex<T, U>> stack = new Stack<>();

        stack.push(source);
        visited.add(source);

        while (!stack.isEmpty()) {
            IVertex<T, U> u = stack.pop();
            if (u.equals(sink)) {
                return true;
            }
            Map<IVertex<T, U>, Double> neighbors = residual.get(u);
            if (neighbors != null) {
                for (Map.Entry<IVertex<T, U>, Double> entry : neighbors.entrySet()) {
                    IVertex<T, U> v = entry.getKey();
                    double capacity = entry.getValue();
                    if (!visited.contains(v) && capacity > 0.0) {
                        stack.push(v);
                        visited.add(v);
                        parent.put(v, u); 
                    }
                }
            }
        }
        
        return false; 
    }
	
}
