package com.udistrital;

import com.udistrital.Model.Graph.*;


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
        grafo2.addVertex('A');
        grafo2.addVertex('B'); 
        grafo2.addVertex('C');
        grafo2.addVertex('D'); 
        grafo2.addVertex('E');
        grafo2.addVertex('F'); 
        grafo2.addVertex('G');

        grafo2.addEdge('A', 'B', 50);
        grafo2.addEdge('A', 'G', 70);
        grafo2.addEdge('B', 'C', 20);
        grafo2.addEdge('B', 'D', 40);
        grafo2.addEdge('C', 'D', 15);
        grafo2.addEdge('C', 'G', 12);
        grafo2.addEdge('D', 'E', 15);
        grafo2.addEdge('D', 'F', 27);
        grafo2.addEdge('E', 'F', 10);
        grafo2.addEdge('F', 'G', 30);

        int[][] matrix = grafo2.matrixInci();
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
    
