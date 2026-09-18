package com.example.practica2;

import com.example.practica2.ControladorUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.Arrays;
import java.util.List;

public class VistaRegistro {
    private ControladorUI controlador;

    public VistaRegistro(ControladorUI controlador) {
        this.controlador = controlador;
    }

    public Scene crearEscena() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));
        layout.setStyle("-fx-background-color: #006B38;");

        Label titulo = new Label("BLACKJACK");
        titulo.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: #C70000; -fx-effect: dropshadow(gaussian, black, 1, 1.0, 0, 0);");

        Label instrucciones = new Label("INSERTE LOS NOMBRES DE LOS JUGADORES");
        instrucciones.setStyle("-fx-font-size: 16px; -fx-padding: 0 0 10 0;");

        TextField jugador1 = crearTextField("Jugador 1");
        TextField jugador2 = crearTextField("Jugador 2");
        TextField jugador3 = crearTextField("Jugador 3");
        TextField jugador4 = crearTextField("Jugador 4");

        Button botonEmpezar = new Button("INICIAR JUEGO");

        botonEmpezar.setOnAction(event -> {
            List<String> nombres = Arrays.asList(
                    jugador1.getText(), jugador2.getText(),
                    jugador3.getText(), jugador4.getText()
            );
            controlador.registrarJugadoresEIniciar(nombres);
        });

        layout.getChildren().addAll(titulo, instrucciones, jugador1, jugador2, jugador3, jugador4, botonEmpezar);
        return new Scene(layout, 800, 600);
    }

    private TextField crearTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setMaxWidth(300);
        return tf;
    }
}