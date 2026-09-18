package com.example.practica2;

import com.example.practica2.Controlador;
import com.example.practica2.Jugador;
import com.example.practica2.VistaJuego;
import com.example.practica2.VistaRegistro;
import com.example.practica2.VistaResultados;
import javafx.stage.Stage;
import java.util.List;

public class ControladorUI {
    private Stage stage;
    private Controlador controlador;
    private int turnoDeJugador = 0;

    public ControladorUI(Stage stage) {
        this.stage = stage;
    }

    public void iniciar() {
        this.controlador = new Controlador();
        this.turnoDeJugador = 0;
        mostrarPantallaRegistro();
    }

    public void mostrarPantallaRegistro() {
        VistaRegistro vista = new VistaRegistro(this);
        stage.setTitle("BLACKJACK - Registro");
        stage.setScene(vista.crearEscena());
        stage.show();
    }

    public void mostrarPantallaJuego() {
        VistaJuego vista = new VistaJuego(this);
        stage.setTitle("BLACKJACK - Juego");
        stage.setScene(vista.crearEscena());
    }

    public void mostrarPantallaResultados() {
        VistaResultados vista = new VistaResultados(this);
        stage.setTitle("BLACKJACK - PANTALLA FINAL");
        stage.setScene(vista.crearEscena());
    }

    public void registrarJugadoresEIniciar(List<String> nombres) {
        controlador.anadirJugadores(nombres);
        controlador.repartirCartasIniciales();
        turnoDeJugador = 0;
        verificarBlackjackInicial();
        mostrarPantallaJuego();
    }

    public void pedirCarta() {
        Jugador actual = controlador.getJugadores().get(turnoDeJugador);
        controlador.anadirCarta(actual);

        int puntos = actual.getMano().obtenerSumatoriaDeLasCartas();
        if (puntos > 21) {
            actual.setYaPerdio(true);
            avanzarTurno();
        } else if (puntos == 21) {
            avanzarTurno();
        } else {
            mostrarPantallaJuego();
        }
    }

    public void avanzarTurno() {
        turnoDeJugador++;
        if (turnoDeJugador >= controlador.getJugadores().size() - 1) {
            jugarTurnoDealer();
        }
        mostrarPantallaJuego();
    }

    private void jugarTurnoDealer() {
        Jugador dealer = controlador.getJugadores().get(turnoDeJugador);
        while (dealer.getMano().obtenerSumatoriaDeLasCartas() < 17) {
            controlador.anadirCarta(dealer);
        }
        if (dealer.getMano().obtenerSumatoriaDeLasCartas() > 21) {
            dealer.setYaPerdio(true);
        }
    }

    private void verificarBlackjackInicial() {
        Jugador actual = controlador.getJugadores().get(turnoDeJugador);
        if(actual.getMano().obtenerSumatoriaDeLasCartas() == 21) {
            avanzarTurno();
        }
    }

    public Controlador getcontrolador() {
        return controlador;
    }

    public int getTurnoDeJugador() {
        return turnoDeJugador;
    }

    public boolean esFinDePartida() {
        return turnoDeJugador >= controlador.getJugadores().size() - 1;
    }
}