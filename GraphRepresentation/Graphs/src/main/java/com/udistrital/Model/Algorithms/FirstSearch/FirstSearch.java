package com.udistrital.Model.Algorithms.FirstSearch;

import java.util.Map;

import com.udistrital.Model.Vertex.IVertex;

public interface FirstSearch<T, U extends Number & Comparable<U>> {

    public boolean isPath(IVertex<T, U> source, IVertex<T, U> sink,
                          Map<IVertex<T, U>, Map<IVertex<T, U>, Double>> residual,
                          Map<IVertex<T, U>, IVertex<T, U>> parent);
    
}
