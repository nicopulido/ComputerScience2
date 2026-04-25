package com.udistrital.Model.Algorithms.Maxinum_Flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.udistrital.Model.Graph.WeightsGraph;
import com.udistrital.Model.Vertex.IVertex;

public class FordFulkerson<T,U> {
    private WeightsGraph<T,U> graph;
    U[][] matrixAdya;

    public FordFulkerson(WeightsGraph<T,U> graph) {
        this.graph = graph;
        ArrayList<IVertex<T,U>> vertices = this.graph.getVertexs();
        matrixAdya = this.graph.matrixAdya();
    }

    /*
        def dfs(self, s, t, visited=None, path=None):

        visited[s] = True
        path.append(s)

        if s == t:
            return path

        for ind, val in enumerate(self.adj_matrix[s]):
            if not visited[ind] and val > 0:
                result_path = self.dfs(ind, t, visited, path.copy())
                if result_path:
                    return result_path

        return None
    */
    private ArrayList<IVertex<T,U>> dfs(IVertex<T,U> source,  IVertex<T,U> tarjet, ArrayList<IVertex<T,U>> path, Map<IVertex<T, U>, Boolean> visitados) {
        Map<IVertex<T, U>, Boolean> CurrentVisitados = new HashMap<>();
        ArrayList<IVertex<T,U>> CurrentPath = new ArrayList<>();

        if(path != null) CurrentPath = path;

        if(visitados != null) CurrentVisitados =  visitados;

        CurrentVisitados.put(source, true);
        path.add(source);
        if(source == tarjet) return CurrentPath;

        for (int i = 0; i < matrixAdya.length; i++) {
        for (int j = 0; j < matrixAdya[i].length; j++) {

            
        }
            
        


        return null;
    }
    
}
