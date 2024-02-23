package com.nwintle.fichaspokemon.models;

import java.util.List;

public class Entrenador {
    private int id;
    private String nombre;
    private String apellido;
    private String icono;
    private float dinero;
    private List<Pokemon> pokemons;
    private List<Resultado> resultados;
    private Usuario usuario;

    public Entrenador(int id, String nombre, String apellido, String icono, float dinero, List<Pokemon> pokemons, List<Resultado> resultados, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.icono = icono;
        this.dinero = dinero;
        this.pokemons = pokemons;
        this.resultados = resultados;
        this.usuario = usuario;
    }

    public Entrenador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
