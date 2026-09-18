package com.example.practica2.modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    protected String nombre;
    protected boolean esDealer;
    protected boolean yaGano;
    protected boolean yaPerdio;

    public boolean isHayBlackJack() {
        return hayBlackJack;
    }

    public void setHayBlackJack(boolean hayBlackJack) {
        this.hayBlackJack = hayBlackJack;
    }

    public boolean isHay21() {
        return hay21;
    }

    public void setHay21(boolean hay21) {
        this.hay21 = hay21;
    }

    public List<Carta> getCartasDelUsuario() {
        return cartasDelUsuario;
    }

    public void setCartasDelUsuario(List<Carta> cartasDelUsuario) {
        this.cartasDelUsuario = cartasDelUsuario;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    protected boolean hayBlackJack;
    protected  boolean hay21;
    protected List<Carta> cartasDelUsuario;
    protected Mano mano;

    public Jugador(String nombre, boolean esDealer, boolean yaGano) {
        this.nombre = nombre;
        this.esDealer = esDealer;
        this.yaGano = yaGano;
        cartasDelUsuario = new ArrayList<>();
        mano = new Mano();
    }

    public Jugador(String nombre){
        this.nombre = nombre;
        esDealer = false;
        yaGano = false;
        mano = new Mano();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsDealer() {
        return esDealer;
    }

    public void setEsDealer(boolean esDealer) {
        this.esDealer = esDealer;
    }

    public boolean isYaGano() {
        return yaGano;
    }

    public void setYaGano(boolean yaGano) {
        this.yaGano = yaGano;
    }

    public Mano getMano(){
        return  mano;
    }

    public void setYaPerdio(boolean yaPerdio){
        this.yaPerdio = yaPerdio;
    }

    public boolean isYaPerdio(){
        return yaPerdio;
    }


}
