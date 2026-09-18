package com.example.practica2.vista;

import java.util.Scanner;

public class Vista {
    private Scanner scanner;

    public Vista(){
        scanner = new Scanner(System.in);
    }

    public int pedirNumero(String mensaje){
        System.out.println(mensaje);
        return  Integer.valueOf(scanner.nextLine());
    }

    public String pedirCadena(String mensaje){
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public int menuDeAccion(){
        String opcion;
        do{
            System.out.println("¿Qué deseas hacer?\n1. Tomar otra carta\n2. Parar ");
            // esto asegura que solo sea numeros entre 1 y 2
            opcion = scanner.nextLine();
            if(!opcion.matches("[12]"))
                System.out.println("Valor inválido");
        }while(!opcion.matches("[12]"));
        return Integer.parseInt(opcion);
    }
}
