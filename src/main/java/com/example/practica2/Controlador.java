package com.example.practica2;

import com.example.practica2.Carta;
import com.example.practica2.Dealer;
import com.example.practica2.Jugador;
import com.example.practica2.Mazo;
import com.example.practica2.Vista;
import  java.util.Iterator;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private List<Jugador> jugadores;
    private Mazo mazo;
    private Vista vista;
    private Jugador ganador;

    public Controlador(){
        jugadores = new ArrayList<>();
        mazo = new Mazo();
        vista = new Vista();
    }

    public void anadirJugadores(){
        for(int i = 0; i < 4; i++){
            String nombre = vista.pedirCadena("Nombre del jugador " + (i+1));
            jugadores.add(new Jugador(nombre));
        }
        jugadores.add(new Dealer("Dealer"));
    }

    public void juegoCentral(){
        Iterator<Jugador> iterador = jugadores.iterator();
        boolean borrarJugador;
        while(iterador.hasNext()){
            Jugador j = iterador.next();
            if(j.isEsDealer()){
                borrarJugador = juegoDealer(j);
            }else {
                borrarJugador = turnoJugador(j);
            }
            if (borrarJugador)
                iterador.remove();
        }
        Jugador ganador = obtenerGanador();
        if(ganador == null){
            System.out.println("Nadie ganó.");
        }else{
            System.out.println("GANADOR: " + ganador.getNombre());
        }
    }

    public void calcularValorDelAs(Carta carta, Jugador  j){
        if(j.getMano().obtenerSumatoriaDeLasCartas() + carta.getValorBajo() > 21){
            carta.setValorBajo(1);
        }
    }

    public boolean turnoJugador(Jugador jugador){
        System.out.println("Turno de: " + jugador.getNombre());
        for(int i = 0; i < 2; i++){
           Carta cartaObtenida =  anadirCarta(jugador);
            System.out.println(jugador.getNombre() + " obtuvo: " + cartaObtenida);
        }
        System.out.println("Valor total: "+ jugador.getMano().obtenerSumatoriaDeLasCartas());
        if(jugador.getMano().obtenerSumatoriaDeLasCartas() == 21){
            System.out.println("BLACKJACK");
            jugador.setHayBlackJack(true);
            return false;
        }
        int opcion;
        do{
            opcion = vista.menuDeAccion();
            switch (opcion){
                case 1:
                    Carta carta = anadirCarta(jugador);

                    if(jugador.getMano().obtenerSumatoriaDeLasCartas() > 21) {
                        jugador.setYaPerdio(true);
                        System.out.println(jugador.getNombre() + " obtuvo: " + carta);
                        System.out.println("Valor total: "+ jugador.getMano().obtenerSumatoriaDeLasCartas());
                        System.out.println("BUST");
                        jugador.setYaPerdio(true);
                        return true;
                    }else if(jugador.getMano().obtenerSumatoriaDeLasCartas() == 21){
                        System.out.println("21!");
                        return false;
                    }else {
                        System.out.println(jugador.getNombre() + " obtuvo: " + carta);
                        System.out.println("Valor total: " + jugador.getMano().obtenerSumatoriaDeLasCartas());
                    }
                    break;
                case 2:
                    break;
            }
        }while(opcion != 2 || jugador.isYaPerdio());
        return  false;
    }

    /*
    public void verSiHayBlackJack(Jugador jugador){
        if(jugador.getMano().obtenerSumatoriaDeLasCartas() == 21){
            if(jugador.getMano().tamanoMano() == 2)
                jugador.setHayBlackJack(true);
            jugador.setHay21(true);
        }
    }

     */

    public Carta anadirCarta(Jugador jugador){
        Carta cartaObtenida = mazo.obtenerUnaCarta();
        if(cartaObtenida.getValorBajo() == 11)
            calcularValorDelAs(cartaObtenida, jugador);
        jugador.getMano().anadirCarta(cartaObtenida);
        return  cartaObtenida;
    }

    public  Jugador obtenerGanador(){
        if(jugadores.isEmpty()){
            System.out.println("Nadie ganó.");
            return  null;
        }
        List<Jugador> posiblesGanadores = new ArrayList<>();
        boolean hayBlackJackEnMesa = false;
        int puntajeMaximo = 0;

        for (Jugador j : jugadores) {
            if (j.isHayBlackJack()) {
                if (!hayBlackJackEnMesa) {
                    posiblesGanadores.clear();
                    hayBlackJackEnMesa = true;
                }
                posiblesGanadores.add(j);
            }
            else if (!hayBlackJackEnMesa) {
                int puntajeActual = j.getMano().obtenerSumatoriaDeLasCartas();

                if (puntajeActual > puntajeMaximo) {
                    puntajeMaximo = puntajeActual;
                    posiblesGanadores.clear();
                    posiblesGanadores.add(j);
                } else if (puntajeActual == puntajeMaximo) {
                    posiblesGanadores.add(j);
                }
            }
        }

        // Si solo hay 1 jugador en la lista es el ganador
        if (posiblesGanadores.size() == 1) {
            return posiblesGanadores.get(0);
        }

        // caso de empate
        return null;
    }

    public boolean juegoDealer(Jugador dealer){
        while(dealer.getMano().obtenerSumatoriaDeLasCartas() < 17){
          Carta carta =  anadirCarta(dealer);
            System.out.println(dealer.getNombre() + " obtuvo: " + carta.toString());
            System.out.println("Valor total: " + dealer.getMano().obtenerSumatoriaDeLasCartas());
        }

        if(dealer.getMano().obtenerSumatoriaDeLasCartas() > 21) {
            dealer.setYaPerdio(true);
            return  true;
        }
        return  false;
    }
// A PARTIR DE AQUI EMPIEZAN METODOS PARA LA GUI
    public void anadirJugadores(List<String> nombres){
        jugadores.clear();
        for(String n:nombres){
            if(n != null && !n.trim().isEmpty()){
                jugadores.add(new Jugador(n));
            }
        }
        jugadores.add(new Dealer("Dealer"));
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void repartirCartasIniciales() {
        for (Jugador j : jugadores) {
            anadirCarta(j);
            anadirCarta(j);
        }
    }
}
