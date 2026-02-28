package Enigma.Model;

import java.util.HashMap;

public class Enigma {
    public Roton[] rotones;
    public Reflector reflector;
    public Reflector cableado;
    private HashMap<String, String> alfabetos = new HashMap<String, String>();


    public Enigma() {
        this.rotones = new Roton[3];
        this.alfabetos.put("I", "ekmflgdqvzntowyhxuspaibrcj");
        this.alfabetos.put("II", "ajdksiruxblhwtmcqgznpyfvoe");
        this.alfabetos.put("III", "bdfhjlcprtxvznyeiwgakmusqo");
        this.alfabetos.put("IV", "ESOVPZJAYQUIRHXLNFTGKDCMWB");
        this.alfabetos.put("V", "VZBRGITYUPSDNHLXAWMJQOFECK");
    }

    public Roton setRoton(char LetraInicial, String alfabeto) {
        alfabeto = this.alfabetos.get(alfabeto);
        return new Roton(LetraInicial, alfabeto);
    }

    public void setReflector(String entradas, String salidas) {
        this.reflector = new Reflector(entradas, salidas);
    }

    public void setCableado(String entradas, String salidas) {
        this.cableado = new Reflector(entradas, salidas);
    }

    public char cipher(char letra) {
        System.out.println(letra);
        char letraCableado = this.cableado.getChar(letra);
        System.out.println("cableado: " + letraCableado);
        char letraRotones = letraCableado;
        for (int i = 0; i < rotones.length; i++) {
            letraRotones = rotones[i].getChar();
            System.out.println("roton " + Integer.toString(i) + " : " + letraCableado);
        }
        char letraReflector = this.reflector.getChar(letraRotones);
        System.out.println("reflector: " + letraReflector);
        letraRotones = letraReflector;
        for (int i = rotones.length - 1; i >= 0; i--) {
            letraRotones = rotones[i].getChar();
            System.out.println("roton " + Integer.toString(i) + " : " + letraCableado);
        }
        letraCableado = this.cableado.getChar(letraRotones);
        System.out.println("cableado: " + letraCableado);
        return letraCableado;
    }
}
