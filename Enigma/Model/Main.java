package Enigma.Model;

public class Main {

    public static void main(String[] args) {
        Enigma maquina = new Enigma();
        // Clave AAA Rotones I,II,III
        maquina.rotones[0] = maquina.setRoton('a', "I");
        maquina.rotones[1] = maquina.setRoton('a', "II");
        maquina.rotones[2] = maquina.setRoton('a', "III");
        // Cableado
        String entradaCableado = "abcdefghijklmnopqrstuvwxyz";
        String salidaCableado = "zyxwvutsrqponmlkjihgfedcba";
        maquina.setCableado(entradaCableado, salidaCableado);
        //Reflector
        String entradaReflector = "abcdefghijklmnopqrstuvwxyz";
        String salidaReflector = "yruhqsldpxngokmiebfzcwvjat";
        maquina.setReflector(entradaReflector, salidaReflector);

        System.out.println(maquina.cipher('a'));
    }
    
}
