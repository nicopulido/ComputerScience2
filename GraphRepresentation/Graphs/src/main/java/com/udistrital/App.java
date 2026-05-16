package com.udistrital;
import com.udistrital.Model.Borg.BorgResolver;
import com.udistrital.Model.Borg.Point;
import com.udistrital.Model.Algorithms.MinimumSpanningTree.Krustal;
import com.udistrital.Model.Graph.WeightsGraph;
import com.udistrital.Model.Edge.WeightsEdge;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = Integer.parseInt(scanner.nextLine().trim());

        for (int t = 0; t < numTest; t++) {

            String[] dimensions = scanner.nextLine().trim().split("\\s+");
            int width = Integer.parseInt(dimensions[0]); 
            int height = Integer.parseInt(dimensions[1]);

            char[][] maze = new char[height][width];
            for (int y = 0; y < height; y++) {
                String line = scanner.nextLine();
                
                for (int x = 0; x < width; x++) {
                    if (x < line.length()) {
                        maze[y][x] = line.charAt(x);
                    } else {
                        maze[y][x] = ' '; 
                    }
                }
            }

            BorgResolver resolver = new BorgResolver(maze, width, height);
            WeightsGraph<Point, Integer> borgGraph = resolver.buildGraph();

            Krustal<Point, Integer> kruskal = new Krustal<>();
            kruskal.execute(borgGraph);
            int totalCost = 0;
            for (WeightsEdge<Point, Integer> edge : kruskal.mst) {
                totalCost += edge.getValue(); 
            }

            System.out.println(totalCost);
        }

        scanner.close();
    }
}


    
