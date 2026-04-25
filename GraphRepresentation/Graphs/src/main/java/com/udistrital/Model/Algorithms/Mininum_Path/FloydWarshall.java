package com.udistrital.Model.Algorithms.Mininum_Path;

public class FloydWarshall {
    //TODO: convert into generic
    private Integer[][] floydWarshall(Integer[][] matrix) {
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
    private String print(Integer[][] matrix) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
            sb.append(matrix[i][j]).append(" ");
        }
        sb.append("\n");
    }
    return sb.toString();
    }

public <T, U extends Number> String shortestPath(Integer [][] matrix) {
    return print(this.floydWarshall(matrix));
}
    
}
