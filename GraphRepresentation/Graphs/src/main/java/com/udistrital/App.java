package com.udistrital;


import com.udistrital.Model.Graph.*;
import com.udistrital.Model.Algorithms.*;;

public class App 
{
    public static void main( String[] args )
    {   
        /*SimpleGraph<Character> grafo = new SimpleGraph<>();
        grafo.addVertex('A');
        grafo.addVertex('B');
        grafo.addEdge('A', 'B');
        grafo.listInci();*/

        WeightsGraph<Character, Integer> grafo2 = new WeightsGraph<>(9999, 0);
        Dijkstra dijkstra = new Dijkstra();

        grafo2.addVertex('I');
        grafo2.addVertex('A'); 
        grafo2.addVertex('D');
        grafo2.addVertex('E'); 
        grafo2.addVertex('T');
        grafo2.addVertex('F'); 
        grafo2.addVertex('C');
        grafo2.addVertex('B');

        grafo2.addEdge('I', 'A', 9);
        grafo2.addEdge('I', 'C', 2);        
        grafo2.addEdge('B', 'C', 1);
        grafo2.addEdge('C', 'F', 10);
        grafo2.addEdge('B','F',6);
        grafo2.addEdge('B', 'A', 1);
        grafo2.addEdge('B', 'D', 7);
        grafo2.addEdge('A', 'D', 1);
        grafo2.addEdge('D', 'E', 2);
        grafo2.addEdge('E', 'T', 9);
        grafo2.addEdge('D', 'T', 18);
        grafo2.addEdge('F', 'T', 2);

        System.out.println(dijkstra.shortestPath(grafo2, 'I', 'T'));

        Integer[][] matrix = grafo2.matrixAdya();
        for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j] + " ");
            
        }System.out.println();}

        
        
    }
  
    public static Integer[][] floydWarshall(Integer[][] matrix) {
        int len = matrix.length;
        int INF = 9999;
        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (matrix[i][k] != INF && matrix[k][j] != INF
                            && matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        return matrix;
    }

}
    
