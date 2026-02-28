package Enigma.Model;

import java.util.HashMap;

public class Reflector {

    private HashMap<Character, Character> reflexion = new HashMap<Character, Character>();

    public char[] entradas;
    public char[] salidas;

    public Reflector(String entrada, String salida) {
        entradas = entrada.toCharArray();
        salidas = salida.toCharArray();
        this.setMap();

    }

    private void setMap() {
        for (int i = 0; i < entradas.length; i++) {
            reflexion.put(entradas[i], salidas[i]);
        }
    }
    
    public char getChar(char entrada) {
        return reflexion.get(entrada);
    }

}
