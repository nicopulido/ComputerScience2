package com.udistrital.Model.Borg;

import com.udistrital.Model.Graph.WeightsGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BorgResolver {

    private char[][] maze;
    private int width;
    private int height;
    private ArrayList<Point> puntosImportantes;

    public BorgResolver(char[][] maze, int width, int height) {
        this.maze = maze;
        this.width = width;
        this.height = height;
        this.puntosImportantes = new ArrayList<>();
    }

    public WeightsGraph<Point, Integer> buildGraph() {
        WeightsGraph<Point, Integer> graph = new WeightsGraph<>(Integer.MAX_VALUE, 0);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 'S' || maze[y][x] == 'A') {
                    Point p = new Point(x, y);
                    puntosImportantes.add(p);
                    graph.addVertex(p);
                }
            }
        }

        for (int i = 0; i < puntosImportantes.size(); i++) {
            bfs(puntosImportantes.get(i), graph, i);
        }

        return graph;
    }

    private void bfs(Point start, WeightsGraph<Point, Integer> graph, int startIndex) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        queue.add(new int[]{start.getX(), start.getY(), 0});
        visited[start.getY()][start.getX()] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int dist = current[2];

            if ((maze[cy][cx] == 'A' || maze[cy][cx] == 'S') && dist > 0) {
                Point endPoint = new Point(cx, cy);
                
                if (puntosImportantes.indexOf(endPoint) > startIndex) {
                    graph.addEdge(start, endPoint, dist);
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    if (!visited[ny][nx] && maze[ny][nx] != '#') {
                        visited[ny][nx] = true;
                        queue.add(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
    }
}