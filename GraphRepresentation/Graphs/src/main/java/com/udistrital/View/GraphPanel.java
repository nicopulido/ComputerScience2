package com.udistrital.View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import com.udistrital.Model.Edge;
import com.udistrital.Model.Graph;
import com.udistrital.Model.Vertex;

public class GraphPanel extends JPanel implements ComponentListener {
    private Graph<String> graph;
    private HashMap<Vertex<String>, Point> vertexPositions;
    private Point selectedVertex = null;
    private Vertex<String> draggingVertex = null;
    private static final int VERTEX_RADIUS = 25;
    private static final Color LINE_COLOR = new Color(100, 150, 200);
    private static final Color VERTEX_COLOR = new Color(70, 130, 180);
    private static final Color VERTEX_HIGHLIGHT = new Color(100, 180, 220);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 245);

    public GraphPanel(Graph<String> graph) {
        this.graph = graph;
        this.vertexPositions = new HashMap<>();
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(800, 600));
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (Vertex<String> v : vertexPositions.keySet()) {
                    Point p = vertexPositions.get(v);
                    if (isNearVertex(e.getPoint(), p)) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        return;
                    }
                }
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (Vertex<String> v : vertexPositions.keySet()) {
                    Point p = vertexPositions.get(v);
                    if (isNearVertex(e.getPoint(), p)) {
                        draggingVertex = v;
                        selectedVertex = e.getPoint();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggingVertex != null) {
                    vertexPositions.put(draggingVertex, e.getPoint());
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggingVertex = null;
                selectedVertex = null;
                repaint();
            }
        };

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    public void updateGraph(Graph<String> graph) {
        this.graph = graph;
        organizeVertices();
        repaint();
    }

    private void organizeVertices() {
        if (graph == null || graph.vertexs.isEmpty()) {
            vertexPositions.clear();
            return;
        }

        vertexPositions.clear();
        int numVertices = graph.vertexs.size();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 50;

        for (int i = 0; i < numVertices; i++) {
            Vertex<String> v = graph.vertexs.get(i);
            double angle = (2 * Math.PI * i) / numVertices;
            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));
            vertexPositions.put(v, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null || graph.vertexs.isEmpty()) {
            return;
        }

        if (vertexPositions.isEmpty()) {
            organizeVertices();
        }

        // Dibujar aristas
        drawEdges(g2d);

        // Dibujar vértices
        drawVertices(g2d);
    }

    private void drawEdges(Graphics2D g) {
        g.setColor(LINE_COLOR);
        g.setStroke(new BasicStroke(2));

        Set<Edge<String>> drawnEdges = new HashSet<>();

        for (Edge<String> edge : graph.edges) {
            Vertex<String> v1 = edge.getFirst();
            Vertex<String> v2 = edge.getSecond();

            // Evitar dibujar la misma arista dos veces
            if (drawnEdges.contains(edge)) {
                continue;
            }
            drawnEdges.add(edge);

            Point p1 = vertexPositions.get(v1);
            Point p2 = vertexPositions.get(v2);

            if (p1 != null && p2 != null) {
                // Verificar si es un self-loop
                if (v1 == v2) {
                    drawSelfLoop(g, p1);
                } else {
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    drawArrowHead(g, p1, p2);
                }
            }
        }
    }

    private void drawSelfLoop(Graphics2D g, Point center) {
        int loopRadius = VERTEX_RADIUS + 15;
        int startAngle = 0;
        int arcAngle = 360;

        // Dibujar el self-loop como un círculo
        g.drawArc(center.x - loopRadius, center.y - loopRadius, 
                  loopRadius * 2, loopRadius * 2, startAngle, arcAngle);

        // Dibujar una pequeña flecha al final del arco
        int arrowAngle = 45;
        double rad = Math.toRadians(arrowAngle);
        int arrowX = (int) (center.x + loopRadius * Math.cos(rad));
        int arrowY = (int) (center.y - loopRadius * Math.sin(rad));
        
        double arrowHeadAngle = Math.toRadians(arrowAngle + 90);
        int arrowSize = 10;
        int x1 = (int) (arrowX - arrowSize * Math.cos(arrowHeadAngle - Math.PI / 6));
        int y1 = (int) (arrowY + arrowSize * Math.sin(arrowHeadAngle - Math.PI / 6));
        int x2 = (int) (arrowX - arrowSize * Math.cos(arrowHeadAngle + Math.PI / 6));
        int y2 = (int) (arrowY + arrowSize * Math.sin(arrowHeadAngle + Math.PI / 6));

        g.drawLine(arrowX, arrowY, x1, y1);
        g.drawLine(arrowX, arrowY, x2, y2);
    }

    private void drawArrowHead(Graphics2D g, Point start, Point end) {
        double angle = Math.atan2(end.y - start.y, end.x - start.x);
        int arrowSize = 12;
        
        int x1 = (int) (end.x - arrowSize * Math.cos(angle - Math.PI / 6));
        int y1 = (int) (end.y - arrowSize * Math.sin(angle - Math.PI / 6));
        int x2 = (int) (end.x - arrowSize * Math.cos(angle + Math.PI / 6));
        int y2 = (int) (end.y - arrowSize * Math.sin(angle + Math.PI / 6));

        g.drawLine(end.x, end.y, x1, y1);
        g.drawLine(end.x, end.y, x2, y2);
    }

    private void drawVertices(Graphics2D g) {
        for (Vertex<String> v : vertexPositions.keySet()) {
            Point p = vertexPositions.get(v);
            
            // Dibujar círculo del vértice
            g.setColor(VERTEX_COLOR);
            g.fillOval(p.x - VERTEX_RADIUS, p.y - VERTEX_RADIUS, 
                      VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
            
            // Borde del vértice
            g.setColor(new Color(30, 80, 130));
            g.setStroke(new BasicStroke(2));
            g.drawOval(p.x - VERTEX_RADIUS, p.y - VERTEX_RADIUS, 
                      VERTEX_RADIUS * 2, VERTEX_RADIUS * 2);
            
            // Texto del vértice
            g.setColor(TEXT_COLOR);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            FontMetrics fm = g.getFontMetrics();
            String text = v.getValue();
            int textX = p.x - fm.stringWidth(text) / 2;
            int textY = p.y + fm.getAscent() / 2;
            g.drawString(text, textX, textY);
        }
    }

    private boolean isNearVertex(Point click, Point vertex) {
        double distance = click.distance(vertex);
        return distance <= VERTEX_RADIUS + 5;
    }

    public HashMap<Vertex<String>, Point> getVertexPositions() {
        return vertexPositions;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        organizeVertices();
        repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
