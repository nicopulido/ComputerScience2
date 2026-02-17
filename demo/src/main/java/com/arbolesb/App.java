package com.arbolesb;

import java.util.Scanner;

import com.arbolesb.controller.Controller;
import com.arbolesb.model.BTree;

public class App {
    
    private static BTree bTree;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.print("\n¿Cual es el grado del arbol B? ");
        int grado = Integer.parseInt(scanner.nextLine());
        Controller controller = new Controller(grado);
        bTree = controller.btree;
        mostrarMenu();
    }

    private static void mostrarMenu() {
        boolean execute = true;
        
        while (execute) {

            System.out.println("\n1. Insertar un valor"+
                                "\n2. Eliminar un valor"+
                                "\n3. Ver el árbol"+
                                "\n4. Salir");
            String option = scanner.nextLine();
            switch(option) {
                case "1":
                    insert();
                    break;
                case "2":
                    eliminate();
                    break;
                case "3":
                    tree();
                    break;
                case "4":
                    execute = false;
                    break;
                default:
                    System.out.println("Opción no válida. Vuelve a intentarlo.");
            }
        }
    }

    private static void insert() {
        System.out.print("\nIngresa el valor a insertar: ");
        try {
            int valor = Integer.parseInt(scanner.nextLine());
            bTree.insertion(valor);
            System.out.println("Valor " + valor + " ingresado.");
        } catch (NumberFormatException e) {
            System.out.println("Error: debes ingresar un valor valido.");
        }
    }

    private static void eliminate() {
        System.out.print("\nIngresa el valor a eliminar: ");
        try {
            int valor = Integer.parseInt(scanner.nextLine().trim());
            bTree.elimination(valor);
            System.out.println("Valor " + valor + " eliminado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: debes ingresar un valor valido.");
        }
    }

    private static void tree() {
        System.out.println("        VISUALIZACION DEL ARBOL         ");
        System.out.println(bTree.print());
        System.err.println("\n");
    }

}