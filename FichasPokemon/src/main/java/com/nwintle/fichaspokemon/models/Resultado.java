package com.nwintle.fichaspokemon.models;

public class Resultado {
    private int id;
    private String ganador;
    private String perdedor;
    private boolean empate;

    public Resultado(int id, String ganador, String perdedor, boolean empate) {
        this.id = id;
        this.ganador = ganador;
        this.perdedor = perdedor;
        this.empate = empate;
    }

    public Resultado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getPerdedor() {
        return perdedor;
    }

    public void setPerdedor(String perdedor) {
        this.perdedor = perdedor;
    }

    public boolean isEmpate() {
        return empate;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }
}
