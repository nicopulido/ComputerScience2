import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaGUI extends JFrame {
    private Enigma enigma;
    
    private JSpinner[] spinnerRotores;
    private JLabel[] labelPosicionActual;
    
    private JTextArea textAreaEntrada;
    private JTextArea textAreaSalida;
    
    private JTextField textFieldReflectorEntrada;
    private JTextField textFieldReflectorSalida;
    private JTextField textFieldCableadoEntrada;
    private JTextField textFieldCableadoSalida;
    
    private final Color COLOR_FONDO = new Color(45, 45, 48);
    private final Color COLOR_PANEL = new Color(60, 60, 65);
    private final Color COLOR_BOTON = new Color(0, 122, 204);
    private final Color COLOR_BOTON_HOVER = new Color(28, 151, 234);
    private final Color COLOR_TEXTO = new Color(220, 220, 220);
    private final Color COLOR_ROTOR = new Color(80, 80, 85);
    private final Color COLOR_ACENTO = new Color(255, 185, 0);

    public EnigmaGUI() {
        setTitle("Máquina Enigma - Simulador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(10, 10));
        
        int[] posicionesIniciales = {0, 0, 0};
        enigma = new Enigma(posicionesIniciales);
        enigma.setReflector("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba");
        enigma.setCableado("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz");

        add(crearPanelTitulo(), BorderLayout.NORTH);
        add(crearPanelPrincipal(), BorderLayout.CENTER);
        add(crearPanelEstado(), BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        JLabel titulo = new JLabel("ENIGMA MACHINE", SwingConstants.CENTER);
        titulo.setFont(new Font("Consolas", Font.BOLD, 28));
        titulo.setForeground(COLOR_ACENTO);
        
        JLabel subtitulo = new JLabel("Simulador de cifrado", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitulo.setForeground(COLOR_TEXTO);
        
        JPanel tituloPanel = new JPanel(new GridLayout(2, 1));
        tituloPanel.setBackground(COLOR_PANEL);
        tituloPanel.add(titulo);
        tituloPanel.add(subtitulo);
        
        panel.add(tituloPanel, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JPanel panelConfig = crearPanelConfiguracion();
        
        JPanel panelTexto = crearPanelTexto();
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelConfig, panelTexto);
        splitPane.setDividerLocation(400);
        splitPane.setBackground(COLOR_FONDO);
        
        panel.add(splitPane, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel crearPanelConfiguracion() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_ROTOR, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        panel.add(crearPanelRotores());
        panel.add(Box.createVerticalStrut(15));
        
        panel.add(crearPanelReflector());
        panel.add(Box.createVerticalStrut(15));

        panel.add(crearPanelCableado());
        panel.add(Box.createVerticalStrut(15));
        
        JButton btnAplicar = crearBoton("Aplicar Configuración", COLOR_BOTON);
        btnAplicar.addActionListener(e -> aplicarConfiguracion());
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(COLOR_PANEL);
        panelBoton.add(btnAplicar);
        panel.add(panelBoton);
        
        return panel;
    }
    
    private JPanel crearPanelRotores() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_ACENTO, 1),
            " ROTORES ",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Consolas", Font.BOLD, 14),
            COLOR_ACENTO
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        spinnerRotores = new JSpinner[3];
        labelPosicionActual = new JLabel[3];
        String[] nombresRotores = {"Rotor I", "Rotor II", "Rotor III"};
        
        for (int i = 0; i < 3; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel lblNombre = new JLabel(nombresRotores[i] + ":");
            lblNombre.setForeground(COLOR_TEXTO);
            lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 12));
            panel.add(lblNombre, gbc);
            
            gbc.gridx = 1;
            spinnerRotores[i] = new JSpinner(new SpinnerNumberModel(0, 0, 25, 1));
            spinnerRotores[i].setPreferredSize(new Dimension(60, 30));
            spinnerRotores[i].setFont(new Font("Consolas", Font.BOLD, 14));
            panel.add(spinnerRotores[i], gbc);
            
            gbc.gridx = 2;
            JLabel lblLetra = new JLabel("(A)");
            lblLetra.setForeground(COLOR_ACENTO);
            lblLetra.setFont(new Font("Consolas", Font.BOLD, 12));
            labelPosicionActual[i] = lblLetra;
            panel.add(lblLetra, gbc);
            
            final int index = i;
            spinnerRotores[i].addChangeListener(e -> {
                int valor = (Integer) spinnerRotores[index].getValue();
                char letra = (char) ('A' + valor);
                labelPosicionActual[index].setText("(" + letra + ")");
            });
        }
        
        return panel;
    }
    
    private JPanel crearPanelReflector() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_ACENTO, 1),
            " REFLECTOR ",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Consolas", Font.BOLD, 14),
            COLOR_ACENTO
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblEntrada = new JLabel("Entrada:");
        lblEntrada.setForeground(COLOR_TEXTO);
        panel.add(lblEntrada, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        textFieldReflectorEntrada = new JTextField("abcdefghijklmnopqrstuvwxyz");
        textFieldReflectorEntrada.setFont(new Font("Consolas", Font.PLAIN, 11));
        panel.add(textFieldReflectorEntrada, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel lblSalida = new JLabel("Salida:");
        lblSalida.setForeground(COLOR_TEXTO);
        panel.add(lblSalida, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        textFieldReflectorSalida = new JTextField("zyxwvutsrqponmlkjihgfedcba");
        textFieldReflectorSalida.setFont(new Font("Consolas", Font.PLAIN, 11));
        panel.add(textFieldReflectorSalida, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelCableado() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(COLOR_ACENTO, 1),
            " PLUGBOARD (Cableado) ",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Consolas", Font.BOLD, 14),
            COLOR_ACENTO
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblEntrada = new JLabel("Entrada:");
        lblEntrada.setForeground(COLOR_TEXTO);
        panel.add(lblEntrada, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        textFieldCableadoEntrada = new JTextField("abcdefghijklmnopqrstuvwxyz");
        textFieldCableadoEntrada.setFont(new Font("Consolas", Font.PLAIN, 11));
        panel.add(textFieldCableadoEntrada, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        JLabel lblSalida = new JLabel("Salida:");
        lblSalida.setForeground(COLOR_TEXTO);
        panel.add(lblSalida, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        textFieldCableadoSalida = new JTextField("abcdefghijklmnopqrstuvwxyz");
        textFieldCableadoSalida.setFont(new Font("Consolas", Font.PLAIN, 11));
        panel.add(textFieldCableadoSalida, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelTexto() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(COLOR_FONDO);
        
        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        panelEntrada.setBackground(COLOR_PANEL);
        panelEntrada.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_ROTOR, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblEntrada = new JLabel("MENSAJE DE ENTRADA");
        lblEntrada.setForeground(COLOR_ACENTO);
        lblEntrada.setFont(new Font("Consolas", Font.BOLD, 14));
        panelEntrada.add(lblEntrada, BorderLayout.NORTH);
        
        textAreaEntrada = new JTextArea();
        textAreaEntrada.setFont(new Font("Consolas", Font.PLAIN, 14));
        textAreaEntrada.setLineWrap(true);
        textAreaEntrada.setWrapStyleWord(true);
        textAreaEntrada.setBackground(new Color(30, 30, 32));
        textAreaEntrada.setForeground(COLOR_TEXTO);
        textAreaEntrada.setCaretColor(COLOR_TEXTO);
        textAreaEntrada.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollEntrada = new JScrollPane(textAreaEntrada);
        scrollEntrada.setBorder(new LineBorder(COLOR_ROTOR, 1));
        panelEntrada.add(scrollEntrada, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelBotones.setBackground(COLOR_PANEL);
        
        JButton btnEncriptar = crearBoton("Encriptar", new Color(40, 167, 69));
        btnEncriptar.addActionListener(e -> encriptar());
        
        JButton btnLimpiar = crearBoton("Limpiar", new Color(220, 53, 69));
        btnLimpiar.addActionListener(e -> limpiar());
        
        JButton btnReset = crearBoton("Reset Rotores", new Color(255, 193, 7));
        btnReset.setForeground(Color.BLACK);
        btnReset.addActionListener(e -> resetRotores());
        
        panelBotones.add(btnEncriptar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnReset);
        panelEntrada.add(panelBotones, BorderLayout.SOUTH);
        
        JPanel panelSalida = new JPanel(new BorderLayout(5, 5));
        panelSalida.setBackground(COLOR_PANEL);
        panelSalida.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_ROTOR, 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblSalida = new JLabel("MENSAJE ENCRIPTADO");
        lblSalida.setForeground(COLOR_ACENTO);
        lblSalida.setFont(new Font("Consolas", Font.BOLD, 14));
        panelSalida.add(lblSalida, BorderLayout.NORTH);
        
        textAreaSalida = new JTextArea();
        textAreaSalida.setFont(new Font("Consolas", Font.BOLD, 14));
        textAreaSalida.setLineWrap(true);
        textAreaSalida.setWrapStyleWord(true);
        textAreaSalida.setEditable(false);
        textAreaSalida.setBackground(new Color(30, 30, 32));
        textAreaSalida.setForeground(new Color(0, 255, 128));
        textAreaSalida.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollSalida = new JScrollPane(textAreaSalida);
        scrollSalida.setBorder(new LineBorder(COLOR_ROTOR, 1));
        panelSalida.add(scrollSalida, BorderLayout.CENTER);
        
        // Botón copiar
        JPanel panelCopiar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCopiar.setBackground(COLOR_PANEL);
        JButton btnCopiar = crearBoton("Copiar al Portapapeles", COLOR_BOTON);
        btnCopiar.addActionListener(e -> copiarAlPortapapeles());
        panelCopiar.add(btnCopiar);
        panelSalida.add(panelCopiar, BorderLayout.SOUTH);
        
        panel.add(panelEntrada);
        panel.add(panelSalida);
        
        return panel;
    }
    
    private JPanel crearPanelEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_PANEL);
        panel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JLabel lblEstado = new JLabel("Estado: Listo para encriptar");
        lblEstado.setForeground(COLOR_TEXTO);
        lblEstado.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        
        JLabel lblInfo = new JLabel("Posiciones actuales: [0, 0, 0] | A-A-A");
        lblInfo.setForeground(COLOR_ACENTO);
        lblInfo.setFont(new Font("Consolas", Font.PLAIN, 12));
        
        panel.add(lblEstado, BorderLayout.WEST);
        panel.add(lblInfo, BorderLayout.EAST);
        
        return panel;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(150, 35));
        
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(color.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(color);
            }
        });
        
        return boton;
    }
    
    private void aplicarConfiguracion() {
        try {
            int[] posiciones = new int[3];
            for (int i = 0; i < 3; i++) {
                posiciones[i] = (Integer) spinnerRotores[i].getValue();
            }
            
            enigma = new Enigma(posiciones);
            
            String reflectorEntrada = textFieldReflectorEntrada.getText().toLowerCase();
            String reflectorSalida = textFieldReflectorSalida.getText().toLowerCase();
            enigma.setReflector(reflectorEntrada, reflectorSalida);
            
            String cableadoEntrada = textFieldCableadoEntrada.getText().toLowerCase();
            String cableadoSalida = textFieldCableadoSalida.getText().toLowerCase();
            enigma.setCableado(cableadoEntrada, cableadoSalida);
            
            JOptionPane.showMessageDialog(this,
                "Configuración aplicada correctamente.\n" +
                "Posiciones de rotores: " + posiciones[0] + "-" + posiciones[1] + "-" + posiciones[2],
                "Configuración",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error al aplicar configuración: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void encriptar() {
        String mensaje = textAreaEntrada.getText();
        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, ingrese un mensaje para encriptar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Resetear rotores antes de encriptar para consistencia
        enigma.resetRotores();
        
        StringBuilder mensajeEncriptado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char c = mensaje.charAt(i);
            if (Character.isLetter(c)) {
                mensajeEncriptado.append(enigma.encriptar(c));
            } else {
                mensajeEncriptado.append(c);
            }
        }
        
        textAreaSalida.setText(mensajeEncriptado.toString());
    }
    
    private void limpiar() {
        textAreaEntrada.setText("");
        textAreaSalida.setText("");
    }
    
    private void resetRotores() {
        enigma.resetRotores();
        JOptionPane.showMessageDialog(this,
            "Rotores reseteados a posiciones iniciales.",
            "Reset",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void copiarAlPortapapeles() {
        String texto = textAreaSalida.getText();
        if (!texto.isEmpty()) {
            java.awt.datatransfer.StringSelection selection = 
                new java.awt.datatransfer.StringSelection(texto);
            java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            JOptionPane.showMessageDialog(this,
                "Texto copiado al portapapeles.",
                "Copiado",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EnigmaGUI());
    }
}
