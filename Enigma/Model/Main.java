public class main {
    public static void main(String[] args){
        int[] posicionesIniciales = {0, 0, 0};
        Enigma enigma = new Enigma(posicionesIniciales);
        
        enigma.setReflector("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba");
        
        enigma.setCableado("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz");

        String mensaje = "clave secreta xd";
        
        enigma.printMensajeEncriptado(mensaje);

        enigma.resetRotores();
        
        String mensajeEncriptado = "dsbet dppyjsj kc"; 
        enigma.printMensajeEncriptado(mensajeEncriptado);
    }
}