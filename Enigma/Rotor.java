public class Rotor {

    int posicionActual;
    char[] alfabetoEncriptado;

    public Rotor(String alfabeto, int posicionInicial) {
        claveRotor(alfabeto.toCharArray());
        for (int i = 0; i < alfabetoEncriptado.length; i++) {
            alfabetoEncriptado[i] = Character.toLowerCase(alfabetoEncriptado[i]);
        }
        this.posicionActual = posicionInicial;
    }


    public void claveRotor(char[] alfabeto) {
        this.alfabetoEncriptado = new char[26];
        for (int i = 0; i < alfabeto.length; i++) {
            this.alfabetoEncriptado[i] = alfabeto[(i*5 + 7) % 26];
        };
    }

    public boolean rotar() {
        posicionActual = (posicionActual + 1) % 26;
        return posicionActual == 0; 
    }

    public char encriptarAdelante(char letra){
        int index = letra - 'a'; // 'a' representa 97 en ascci, casi no entiendo esto ajsjajs
        // entonces obtenemos el indice de la letra restandole 97
        int desplazamiento = (index + posicionActual) % 26;
        return alfabetoEncriptado[desplazamiento];
    }

    public char encriptarAtras(char letra){
        int index = 0;
        for (int i = 0; i < alfabetoEncriptado.length; i++) {
            if (alfabetoEncriptado[i] == letra) {
                index = i;
                break;
            }
        }
        int desplazamiento = (index - posicionActual + 26) % 26;
        return (char) ('a' + desplazamiento);
    }

    
    
    
}
