package com.example.practica2;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    private List<Carta> cartasDelUsuario;

    public Mano(){
        cartasDelUsuario = new ArrayList<>();
    }

    public List<Carta> getCartasDelUsuario() {
        return cartasDelUsuario;
    }

    public void setCartasDelUsuario(List<Carta> cartasDelUsuario) {
        this.cartasDelUsuario = cartasDelUsuario;
    }

    public void anadirCarta(Carta carta){
        cartasDelUsuario.add(carta);
    }

    // obtiene la suma total de los valores de las cartas que tiene el usuario en la mano
    public int obtenerSumatoriaDeLasCartas(){
        return cartasDelUsuario.stream()
                .mapToInt(c -> c.getValorBajo())
                .reduce(0, Integer::sum);
    }
    // vacio la mano
    public void vaciarMano(){
        cartasDelUsuario.clear();
    }

    public int tamanoMano(){
        return cartasDelUsuario.size();
    }
}
