package com.example.practica2.modelo;

public class Movimiennto {
    public enum Accion {PIDECARTA, AVANZATURNO};
    private Accion accion;
    private  int turnoAnterior;
    private  boolean perdio;

    public Movimiennto(Accion accion, int turnoAnterior, boolean perdio) {
        this.accion = accion;
        this.turnoAnterior = turnoAnterior;
        this.perdio = perdio;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public boolean isPerdio() {
        return perdio;
    }

    public void setPerdio(boolean perdio) {
        this.perdio = perdio;
    }
}
