package Enigma.Model;
public class Roton {

    char letraInicial;
    int posicionInicial;
    char[] alfabetoEncriptado;
    char[] alfabeto;

    public Roton(char letra, String alfabeto) {

        this.alfabetoEncriptado = alfabeto.toCharArray();
        this.alfabeto = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }



}