import java.util.HashMap;

public class Enigma {
    public Rotor[] rotores;
    public Reflector reflector;
    public Reflector cableado;
    public int[] arrayPosicionesIniciales = new int[3]; 

    public Enigma(int[] posicionesIniciales){
        this.rotores = new Rotor[3];
        rotores[0] = new Rotor("abcdefghijklmnopqrstuvwxyz", posicionesIniciales[0]);
        rotores[1] = new Rotor(new String(rotores[0].alfabetoEncriptado), posicionesIniciales[1]);
        rotores[2] = new Rotor(new String(rotores[1].alfabetoEncriptado), posicionesIniciales[2]);
        
        for (int i = 0; i < 3; i++) {
            this.arrayPosicionesIniciales[i] = posicionesIniciales[i];
        }
    }

    public void setReflector(String entrada, String salida){
        this.reflector = new Reflector(entrada, salida);
    }

    public void setCableado(String entrada, String salida){
        this.cableado = new Reflector(entrada, salida);
    }

    private void rotarRotores(){
        boolean ciclo = rotores[0].rotar();
        if(ciclo){
            ciclo = rotores[1].rotar();
            if(ciclo){
                rotores[2].rotar();
            }
        }
    }

    public void resetRotores() {
        for (int i = 0; i < 3; i++) {
            rotores[i].posicionActual = arrayPosicionesIniciales[i];
        }
    }

    public char encriptar(char letra){    
        if (letra == ' ') {
            return ' '; 
        }
        rotarRotores();
        letra = Character.toLowerCase(letra);
        letra = cableado.getChar(letra);
        for (int i = 0; i < rotores.length; i++) {
            letra = rotores[i].encriptarAdelante(letra);
        }
        letra = reflector.getChar(letra);
        for (int i = rotores.length - 1; i >= 0; i--) {
            letra = rotores[i].encriptarAtras(letra);
        }
        letra = cableado.getChar(letra);
        return letra;
    }

    public void printMensajeEncriptado(String mensaje){
        String mensajeEncriptado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            mensajeEncriptado += encriptar(mensaje.charAt(i));
        }
        System.out.println("Mensaje original: " + mensaje);
        System.out.println("Mensaje encriptado: " + mensajeEncriptado);
    }
}