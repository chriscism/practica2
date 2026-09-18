package com.example.practica2.modelo;

public class Dealer extends Jugador{

    public Dealer(String nombre){
        super(nombre);
        esDealer = true;
        yaGano = false;
    }
}
