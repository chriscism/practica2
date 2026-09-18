package com.example.practica2.modelo;

import java.util.Arrays;
import java.util.Collections;

public class Pila<T> {
    private T[] pila;
    private int tope;

    // Constructor vacío con capacidad de 10
    public Pila() {
        pila = (T[]) new Object[10];
        tope = -1;
    }

    // Constructor que recibe la capacidad
    public Pila(int capacidad) {
        pila = (T[]) new Object[capacidad];
        tope = -1;
    }

    public void push(T dato) {
        if (llena()) {
            System.out.println("Desbordamiento");
        } else {
            tope++;
            pila[tope] = dato;
        }
    }

    public T pop() {
        if (vacia()) {
            System.out.println("SubDesbordamiento");
            return null;
        } else {
            T dato = pila[tope];
            tope--;
            return dato;
        }
    }

    public boolean llena() {
        if (tope == pila.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean vacia() {
        if (tope == -1) {
            return true;
        } else {
            return false;
        }
    }

    public String invierteCadena(String cadena) {
        Pila<Character> pila = new Pila<Character>(cadena.length());

        for (int i = 0; i < cadena.length(); i++) {
            pila.push(cadena.charAt(i));
        }

        String inversa = "";

        while (!pila.vacia()) {
            inversa += pila.pop();
        }

        return inversa;
    }

    public boolean revisarSintaxis(String cadena) {
        Pila pila = new Pila(cadena.length());

        for (int i = 0; i < cadena.length(); i++) {
            char car = cadena.charAt(i);

            if (car == '(' || car == '[' || car == '{') {
                pila.push(car);
            } else {
                if (pila.vacia()) {
                    System.out.println("La cadena " + cadena+ " no es válida");
                    return false;
                }

                char abierto = (char) pila.pop();

                if (car == ')' && abierto != '(') {
                    System.out.println("La cadena " + cadena+ " no es válida");
                    return false;
                }

                if (car == ']' && abierto != '[') {
                    System.out.println("La cadena " + cadena+ " no es válida");
                    return false;
                }

                if (car == '}' && abierto != '{') {
                    System.out.println("La cadena " + cadena+ " no es válida");
                    return false;
                }
            }
        }
        System.out.println("La cadena " + cadena+ " es válida");
        return pila.vacia();
    }

    public Pila ordenarNumeros(Integer[] numeros){
        Pila pila = new Pila(numeros.length);

        Arrays.sort(numeros, Collections.reverseOrder());
        for(int i = 0; i < numeros.length; i++)
            pila.push((T) numeros[i]);

        return pila;
    }

    public int getTope(){
        return  tope+1;
    }
}