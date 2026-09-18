package com.example.practica2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private Pila<Carta> cartasDelUsuario;

    public Mano(){
        cartasDelUsuario = new Pila<>();
    }

    public Pila<Carta> getCartasDelUsuario() {
        return cartasDelUsuario;
    }

    public void setCartasDelUsuario(Pila<Carta> cartasDelUsuario) {
        this.cartasDelUsuario = cartasDelUsuario;
    }

    public void anadirCarta(Carta carta){
        cartasDelUsuario.push(carta);
    }

    // obtiene la suma total de los valores de las cartas que tiene el usuario en la mano
    //REIMPLEMENTADO CON PILA
    public int obtenerSumatoriaDeLasCartas() {
        // Pila temporal para guardar las cartas mientras las sumo
        Pila<Carta> pilaAux = new Pila<>(20);
        int suma = 0;

        // saco cartas las sumo y las guardo en auxiliar
        while (!cartasDelUsuario.vacia()) {
            Carta carta = cartasDelUsuario.pop();
            suma += carta.getValorBajo();
            pilaAux.push(carta);
        }

        // las regreso
        while (!pilaAux.vacia()) {
            cartasDelUsuario.push(pilaAux.pop());
        }

        return suma;
    }

    public Carta removerUltimaCarta(){
        if(!cartasDelUsuario.vacia()){
            return cartasDelUsuario.pop();
        }
        return null;
    }

    public List<Carta> getCartasDelUsuarioGUI(){
        Pila<Carta> pilaAux = new Pila<>(20);
        List<Carta> lista = new ArrayList<>();

        while(!cartasDelUsuario.vacia()) {
            Carta c = cartasDelUsuario.pop();
            lista.add(0, c); // 0 para mantener el orden
            pilaAux.push(c);
        }

        while(!pilaAux.vacia()) {
            cartasDelUsuario.push(pilaAux.pop());
        }

        return lista;
    }

    public int tamanoMano(){
        return cartasDelUsuario.getTope();
    }
}
