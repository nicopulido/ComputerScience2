import java.util.HashMap;

public class Reflector {
    char[] entrada;
    char[] salida;
    HashMap<Character, Character> reflexion = new HashMap<Character, Character>();
    

    public Reflector(String entrada, String salida){
        this.entrada = entrada.toCharArray();
        this.salida = salida.toCharArray();
        this.setMap();
    }

    private void setMap() {
        for (int i = 0; i < entrada.length; i++) {
            reflexion.put(entrada[i], salida[i]);
        }
    }

    public char getChar(char entrada) {
        return reflexion.get(entrada);
    }

}
