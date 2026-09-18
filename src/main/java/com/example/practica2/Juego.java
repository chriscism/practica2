package com.example.practica2;

import com.example.practica2.controlador.ControladorUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Juego extends Application {

    @Override
    public void start(Stage stage) {
        ControladorUI controlador = new ControladorUI(stage);
        controlador.iniciar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}