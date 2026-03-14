package com.udistrital.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.udistrital.Controller.GraphController;
import com.udistrital.Model.Edge;
import com.udistrital.Model.Vertex;

public class GraphGUI extends JFrame {
    private GraphController controller;
    private GraphPanel graphPanel;
    
    private JTextField textFieldVertex;
    private JTextField textFieldEdgeVertex1;
    private JTextField textFieldEdgeVertex2;
    private JTextArea textAreaInfo;
    private JButton btnAddVertex;
    private JButton btnAddEdge;
    private JButton btnRemoveVertex;
    private JButton btnClear;
    private JButton btnAdjacencyList;
    private JButton btnIncidenceList;
    private JButton btnAdjacencyMatrix;
    private JButton btnIncidenceMatrix;
    
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color TEXT_COLOR = new Color(44, 62, 80);

    public GraphGUI() {
        this.controller = new GraphController();
        this.initializeUI();
    }

    private void initializeUI() {
        setTitle("Graph Representation - MVC Architecture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 750);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Aplicar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Panel superior (controles)
        mainPanel.add(createControlPanel(), BorderLayout.NORTH);

        // Panel central (grafo)
        this.graphPanel = new GraphPanel(controller.getGraph());
        mainPanel.add(createGraphPanel(), BorderLayout.CENTER);

        // Panel derecho (información)
        mainPanel.add(createInfoPanel(), BorderLayout.EAST);

        this.add(mainPanel);
        this.setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 2, 3));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(PRIMARY_COLOR, 2),
            " Controles del Grafo ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12),
            PRIMARY_COLOR
        ));

        // Fila 1: Agregar vértice
        JPanel row1 = new JPanel(new BorderLayout(5, 2));
        row1.setBackground(PANEL_COLOR);
        JPanel left1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        left1.setBackground(PANEL_COLOR);
        JLabel lbl1 = new JLabel("Vértice:");
        lbl1.setFont(new Font("Arial", Font.BOLD, 10));
        left1.add(lbl1);
        textFieldVertex = new JTextField(12);
        left1.add(textFieldVertex);
        row1.add(left1, BorderLayout.WEST);
        
        JPanel right1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 0));
        right1.setBackground(PANEL_COLOR);
        btnAddVertex = createButton("Agregar", PRIMARY_COLOR);
        btnAddVertex.addActionListener(e -> addVertex());
        right1.add(btnAddVertex);
        row1.add(right1, BorderLayout.EAST);

        // Fila 2: Agregar arista
        JPanel row2 = new JPanel(new BorderLayout(5, 2));
        row2.setBackground(PANEL_COLOR);
        JPanel left2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 0));
        left2.setBackground(PANEL_COLOR);
        JLabel lbl2 = new JLabel("Arista:");
        lbl2.setFont(new Font("Arial", Font.BOLD, 10));
        left2.add(lbl2);
        textFieldEdgeVertex1 = new JTextField(6);
        left2.add(new JLabel("De:"));
        left2.add(textFieldEdgeVertex1);
        textFieldEdgeVertex2 = new JTextField(6);
        left2.add(new JLabel("A:"));
        left2.add(textFieldEdgeVertex2);
        row2.add(left2, BorderLayout.WEST);
        
        JPanel right2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 0));
        right2.setBackground(PANEL_COLOR);
        btnAddEdge = createButton("Conectar", PRIMARY_COLOR);
        btnAddEdge.addActionListener(e -> addEdge());
        right2.add(btnAddEdge);
        row2.add(right2, BorderLayout.EAST);

        // Fila 3: Botones de utilidad
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 2));
        row3.setBackground(PANEL_COLOR);
        
        btnRemoveVertex = createButton("Eliminar", new Color(231, 76, 60));
        btnRemoveVertex.addActionListener(e -> removeVertex());
        row3.add(btnRemoveVertex);
        
        btnClear = createButton("Limpiar", new Color(149, 165, 166));
        btnClear.addActionListener(e -> clearGraph());
        row3.add(btnClear);

        panel.add(row1);
        panel.add(row2);
        panel.add(row3);

        return panel;
    }

    private JPanel createGraphPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(PRIMARY_COLOR, 2),
            " Visualización del Grafo ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12),
            PRIMARY_COLOR
        ));

        this.graphPanel.setBackground(Color.WHITE);
        panel.add(this.graphPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setPreferredSize(new Dimension(320, 0));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(PRIMARY_COLOR, 2),
            " Representaciones del Grafo ",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12),
            PRIMARY_COLOR
        ));

        // Panel de botones para representaciones
        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 2, 2));
        btnPanel.setBackground(PANEL_COLOR);
        btnPanel.setBorder(new EmptyBorder(3, 3, 3, 3));

        btnAdjacencyList = createSmallButton("Adyacencia", new Color(52, 152, 219));
        btnAdjacencyList.addActionListener(e -> showAdjacencyList());
        btnPanel.add(btnAdjacencyList);

        btnIncidenceList = createSmallButton("Incidencia", new Color(46, 204, 113));
        btnIncidenceList.addActionListener(e -> showIncidenceList());
        btnPanel.add(btnIncidenceList);

        btnAdjacencyMatrix = createSmallButton("Mat. Ady.", new Color(155, 89, 182));
        btnAdjacencyMatrix.addActionListener(e -> showAdjacencyMatrix());
        btnPanel.add(btnAdjacencyMatrix);

        btnIncidenceMatrix = createSmallButton("Mat. Inc.", new Color(230, 126, 34));
        btnIncidenceMatrix.addActionListener(e -> showIncidenceMatrix());
        btnPanel.add(btnIncidenceMatrix);

        panel.add(btnPanel, BorderLayout.NORTH);

        textAreaInfo = new JTextArea();
        textAreaInfo.setEditable(false);
        textAreaInfo.setLineWrap(true);
        textAreaInfo.setWrapStyleWord(true);
        textAreaInfo.setFont(new Font("Monospaced", Font.PLAIN, 8));
        textAreaInfo.setForeground(TEXT_COLOR);
        textAreaInfo.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(textAreaInfo);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        panel.add(scrollPane, BorderLayout.CENTER);

        updateInfo();

        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(100, 25));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private JButton createSmallButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 9));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(95, 35));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private void addVertex() {
        String value = textFieldVertex.getText().trim();
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre para el vértice.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        controller.addVertex(value);
        graphPanel.updateGraph(controller.getGraph());
        textFieldVertex.setText("");
        updateInfo();
        JOptionPane.showMessageDialog(this, "Vértice '" + value + "' agregado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addEdge() {
        String vertex1 = textFieldEdgeVertex1.getText().trim();
        String vertex2 = textFieldEdgeVertex2.getText().trim();

        if (vertex1.isEmpty() || vertex2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa ambos vértices.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que ambos vértices existan
        if (!controller.vertexExists(vertex1)) {
            JOptionPane.showMessageDialog(this, "El vértice '" + vertex1 + "' no existe.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!controller.vertexExists(vertex2)) {
            JOptionPane.showMessageDialog(this, "El vértice '" + vertex2 + "' no existe.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean edgeAdded = controller.addEdge(vertex1, vertex2);
        
        if (edgeAdded) {
            graphPanel.updateGraph(controller.getGraph());
            textFieldEdgeVertex1.setText("");
            textFieldEdgeVertex2.setText("");
            updateInfo();
            JOptionPane.showMessageDialog(this, "Arista agregada entre '" + vertex1 + "' y '" + vertex2 + "'.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la arista.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeVertex() {
        String value = JOptionPane.showInputDialog(this, "Ingresa el nombre del vértice a eliminar:");
        if (value != null && !value.trim().isEmpty()) {
            controller.removeVertex(value.trim());
            graphPanel.updateGraph(controller.getGraph());
            updateInfo();
            JOptionPane.showMessageDialog(this, "Vértice eliminado.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearGraph() {
        int option = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas limpiar el grafo?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            controller.clearGraph();
            graphPanel.updateGraph(controller.getGraph());
            updateInfo();
            JOptionPane.showMessageDialog(this, "Grafo limpiado.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showAdjacencyList() {
        String content = controller.getAdjacencyList();
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE ADYACENCIA ===\n\n");
        sb.append(content);
        textAreaInfo.setText(sb.toString());
    }

    private void showIncidenceList() {
        String content = controller.getIncidenceList();
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE INCIDENCIA ===\n\n");
        sb.append(content);
        textAreaInfo.setText(sb.toString());
    }

    private void showAdjacencyMatrix() {
        int[][] matrix = controller.getAdjacencyMatrix();
        StringBuilder sb = new StringBuilder();
        sb.append("=== MATRIZ DE ADYACENCIA ===\n\n");
        sb.append(formatMatrix(matrix));
        textAreaInfo.setText(sb.toString());
    }

    private void showIncidenceMatrix() {
        int[][] matrix = controller.getIncidenceMatrix();
        StringBuilder sb = new StringBuilder();
        sb.append("=== MATRIZ DE INCIDENCIA ===\n\n");
        sb.append(formatMatrix(matrix));
        textAreaInfo.setText(sb.toString());
    }

    private String formatMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int val : row) {
                sb.append(val).append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void updateInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("== ESTADÍSTICAS ==\n\n");
        sb.append("Vértices: ").append(controller.getVertexs().size()).append("\n");
        sb.append("Aristas: ").append(controller.getEdges().size()).append("\n\n");

        sb.append("== VÉRTICES ==\n");
        for (Vertex<String> v : controller.getVertexs()) {
            sb.append("• ").append(v.getValue()).append("\n");
        }

        sb.append("\n== ARISTAS ==\n");
        for (Edge<String> e : controller.getEdges()) {
            sb.append("• ").append(e.toString()).append("\n");
        }

        textAreaInfo.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphGUI());
    }
}

