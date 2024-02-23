package com.nwintle.fichaspokemon.models;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private Entrenador entrenador;

    public Usuario(int id, String username, String password, Entrenador entrenador) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.entrenador = entrenador;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}
