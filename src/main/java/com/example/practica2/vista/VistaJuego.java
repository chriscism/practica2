package com.example.practica2.vista;

import com.example.practica2.controlador.ControladorUI;
import com.example.practica2.modelo.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VistaJuego {
    private ControladorUI controlador;
    private final String estiloCarta = "-fx-background-color: white; -fx-padding: 15; -fx-font-size: 16px; -fx-border-radius: 5; -fx-background-radius: 5;";
    private final String estiloBoton = "-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20;";

    public VistaJuego(ControladorUI controlador) {
        this.controlador = controlador;
    }

    public Scene crearEscena() {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(25));
        borderPane.setStyle("-fx-background-color: #006B38;");

        VBox cajitaDealer = new VBox(10);
        cajitaDealer.setAlignment(Pos.TOP_CENTER);

        FlowPane cajaJugadores = new FlowPane(10, 10);
        cajaJugadores.setAlignment(Pos.CENTER);

        // Dibujar Jugadores y Dealer
        for (Jugador j : controlador.getcontrolador().getJugadores()) {
            if(j.isEsDealer()) {
                dibujarDealer(j, cajitaDealer);
                borderPane.setTop(cajitaDealer);
            } else {
                cajaJugadores.getChildren().add(dibujarPanelJugador(j));
            }
        }

        VBox contenedorCentro = new VBox();
        contenedorCentro.setAlignment(Pos.CENTER);
        contenedorCentro.getChildren().add(cajaJugadores);
        borderPane.setCenter(contenedorCentro);
        borderPane.setBottom(crearCajaOpciones());

        return new Scene(borderPane, 800, 600);
    }

    private void dibujarDealer(Jugador j, VBox cajitaDealer) {
        Label labelDealer = new Label(j.getNombre());
        labelDealer.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");

        HBox cartasDealer = new HBox(10);
        cartasDealer.setAlignment(Pos.CENTER);
        Label puntosDealer;

        if (controlador.esFinDePartida()) {
            for (Object cartaObj : j.getMano().getCartasDelUsuarioGUI()) {
                Label carta = new Label(" " + cartaObj.toString() + " ");
                carta.setStyle(estiloCarta);
                cartasDealer.getChildren().add(carta);
            }
            puntosDealer = new Label("Puntaje: " + j.getMano().obtenerSumatoriaDeLasCartas());
        } else {
            Label cartaOculta = new Label(" " + j.getMano().getCartasDelUsuarioGUI().get(0).toString() + " ");
            Label cartaVisible = new Label(" " + j.getMano().getCartasDelUsuarioGUI().get(1).toString() + " ");
            cartaOculta.setStyle(estiloCarta);
            cartaVisible.setStyle(estiloCarta);
            cartasDealer.getChildren().addAll(cartaOculta, cartaVisible);
            puntosDealer = new Label("Puntaje: ?");
        }

        puntosDealer.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        cajitaDealer.getChildren().addAll(labelDealer, cartasDealer, puntosDealer);
    }

    private VBox dibujarPanelJugador(Jugador j) {
        VBox panel = new VBox(10);
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-border-color: rgba(255,255,255,0.5); -fx-border-width: 2; -fx-padding: 15; -fx-border-radius: 10;");
        panel.setMinWidth(160);

        Label labelNombre = new Label(j.getNombre());
        labelNombre.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        FlowPane cartas = new FlowPane(5, 5);
        cartas.setAlignment(Pos.CENTER);

        for (Object cartaObj : j.getMano().getCartasDelUsuarioGUI()) {
            Label lblCarta = new Label(cartaObj.toString());
            lblCarta.setStyle("-fx-background-color: white; -fx-padding: 10 15; -fx-font-size: 14px; -fx-border-radius: 5;");
            cartas.getChildren().add(lblCarta);
        }

        int puntosJugador = j.getMano().obtenerSumatoriaDeLasCartas();
        Label labelPuntos = new Label("Puntaje: " + puntosJugador);
        labelPuntos.setStyle("-fx-text-fill: " + (j.isYaPerdio() ? "#ff3333" : "#ffd700") + "; -fx-font-weight: bold;");

        panel.getChildren().addAll(labelNombre, cartas, labelPuntos);

        // Avisos BUST / BLACKJACK
        Label labelAviso = new Label();
        if (puntosJugador > 21 || j.isYaPerdio()) {
            labelAviso.setText("BUST");
            labelAviso.setStyle("-fx-text-fill: #ff3333; -fx-font-weight: bold; -fx-font-size: 14px;");
        } else if (puntosJugador == 21) {
            labelAviso.setText(j.getMano().tamanoMano() == 2 ? "BLACKJACK" : "21!");
            labelAviso.setStyle("-fx-text-fill: #00ffff; -fx-font-weight: bold; -fx-font-size: 16px;");
        }

        if (!labelAviso.getText().isEmpty()) panel.getChildren().add(labelAviso);
        return panel;
    }

    private VBox crearCajaOpciones() {
        VBox cajaOpciones = new VBox(15);
        cajaOpciones.setAlignment(Pos.CENTER);
        HBox botones = new HBox(20);
        botones.setAlignment(Pos.CENTER);

        if (controlador.esFinDePartida()) {
            Label msjFin = new Label("¡FIN DE LA PARTIDA!");
            msjFin.setStyle("-fx-text-fill: yellow; -fx-font-size: 18px; -fx-font-weight: bold;");
            Button btnResultados = new Button("VER RESULTADOS");
            btnResultados.setStyle(estiloBoton);
            btnResultados.setOnAction(event -> controlador.mostrarPantallaResultados());
            botones.getChildren().add(btnResultados);
            cajaOpciones.getChildren().addAll(msjFin, botones);
        } else {
            Jugador jugadorActual = controlador.getcontrolador().getJugadores().get(controlador.getTurnoDeJugador());
            Label turnoJugador = new Label("Turno de: " + jugadorActual.getNombre());
            turnoJugador.setStyle("-fx-text-fill: yellow; -fx-font-size: 18px; -fx-font-weight: bold;");

            Button tomarCarta = new Button("TOMA UNA CARTA");
            Button parar = new Button("DETENTE");
            Button deshacer = new Button("DESHACER MOVIMIENTO");
            deshacer.setStyle(estiloBoton);
            tomarCarta.setStyle(estiloBoton);
            // YA FUNCIONA
            parar.setStyle(estiloBoton);

            tomarCarta.setOnAction(e -> controlador.pedirCarta());
            parar.setOnAction(e -> controlador.avanzarTurno());
            deshacer.setOnAction(event -> controlador.deshacer());

            botones.getChildren().addAll(tomarCarta, parar, deshacer);
            cajaOpciones.getChildren().addAll(turnoJugador, botones);
        }
        return cajaOpciones;
    }
}