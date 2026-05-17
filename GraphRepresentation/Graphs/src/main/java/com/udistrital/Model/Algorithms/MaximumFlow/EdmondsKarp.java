package com.udistrital.Model.Algorithms.MaximumFlow;

import com.udistrital.Model.Algorithms.FirstSearch.Breadth;
import com.udistrital.Model.Graph.Graph;

public class EdmondsKarp<T, U extends Number & Comparable<U>> extends MaximumFlow<T,U>  {

    public EdmondsKarp(Graph<T,U> graph) {
        super(graph, new Breadth<>(graph));
    }
	
}