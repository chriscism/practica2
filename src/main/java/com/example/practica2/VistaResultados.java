package com.example.practica2;

import com.example.practica2.ControladorUI;
import com.example.practica2.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.List;

public class VistaResultados {
    private ControladorUI controlador;

    public VistaResultados(ControladorUI controlador) {
        this.controlador = controlador;
    }

    public Scene crearEscena() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #006B38;");

        Label titulo = new Label("RESULTADOS FINALES");
        titulo.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");

        List<Jugador> jugadores = controlador.getcontrolador().getJugadores();
        Jugador dealer = jugadores.get(jugadores.size() - 1);
        int puntosDealer = dealer.getMano().obtenerSumatoriaDeLasCartas();
        boolean dealerSePaso = puntosDealer > 21;

        Label infoDealer = new Label("Puntaje del Dealer: " + puntosDealer + (dealerSePaso ? " (BUST)" : ""));
        infoDealer.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ffd700;");

        VBox listaResultados = new VBox(10);
        listaResultados.setAlignment(Pos.CENTER);

        for (int i = 0; i < jugadores.size() - 1; i++) {
            Jugador j = jugadores.get(i);
            listaResultados.getChildren().add(evaluarJugador(j, puntosDealer, dealerSePaso));
        }

        HBox botones = new HBox(20);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(20, 0, 0, 0));

        Button btnReiniciar = new Button("JUGAR OTRA VEZ");
        Button btnSalir = new Button("SALIR DEL JUEGO");
        String estiloBoton = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20;";
        btnReiniciar.setStyle(estiloBoton);
        btnSalir.setStyle(estiloBoton);

        btnReiniciar.setOnAction(e -> controlador.iniciar());
        btnSalir.setOnAction(e -> System.exit(0));

        botones.getChildren().addAll(btnReiniciar, btnSalir);
        layout.getChildren().addAll(titulo, infoDealer, listaResultados, botones);

        return new Scene(layout, 800, 600);
    }

    private Label evaluarJugador(Jugador j, int puntosDealer, boolean dealerSePaso) {
        int puntosJugador = j.getMano().obtenerSumatoriaDeLasCartas();
        String estado;
        String colorFondo;

        if (j.isYaPerdio()) {
            estado = "DERROTA";
            colorFondo = "#ff4444";
        } else if (dealerSePaso || puntosJugador > puntosDealer) {
            estado = "VICTORIA";
            colorFondo = "#44ff44";
        } else if (puntosJugador < puntosDealer) {
            estado = "DERROTA";
            colorFondo = "#ff4444";
        } else {
            estado = "EMPATE";
            colorFondo = "#aaaaaa";
        }

        Label lbl = new Label(j.getNombre() + " - Puntos: " + puntosJugador + " (" + estado + ")");
        lbl.setStyle("-fx-background-color: " + colorFondo + "; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10; -fx-background-radius: 5;");
        lbl.setMinWidth(300);
        lbl.setAlignment(Pos.CENTER);
        return lbl;
    }
}