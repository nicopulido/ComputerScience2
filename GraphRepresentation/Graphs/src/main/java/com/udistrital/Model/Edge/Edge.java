package com.udistrital.Model.Edge;

import com.udistrital.Model.Vertex.IVertex;
import com.udistrital.Model.Vertex.Vertex;

public interface Edge<T,U> {

public IVertex<T,U> get(IVertex<T,U> vertex);
public IVertex<T,U>[] getVertexs();
public U getValue();
public void setValue(U value);


}
