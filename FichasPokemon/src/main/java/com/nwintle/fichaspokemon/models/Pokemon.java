package com.nwintle.fichaspokemon.models;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id;
    private String name;
    private String type;
    private int level;
    private int hp;
    private int attack;
    private int defence;
    private int speed;
    private String gender;
    private double valor;
    private String hiresURL;
    private int entrenador;
    private int mercado;
    private int alineacion;

    public Pokemon(int id, String name, String type, int level, int hp, int attack, int defence, int speed, String gender, double valor, String hiresURL, int entrenador, int mercado, int alineacion) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.gender = gender;
        this.valor = valor;
        this.hiresURL = hiresURL;
        this.entrenador = entrenador;
        this.mercado = mercado;
        this.alineacion = alineacion;
    }

    public Pokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHiresURL() {
        return hiresURL;
    }

    public void setHiresURL(String hiresURL) {
        this.hiresURL = hiresURL;
    }

    public int getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(int entrenador) {
        this.entrenador = entrenador;
    }

    public int getMercado() {
        return mercado;
    }

    public void setMercado(int mercado) {
        this.mercado = mercado;
    }

    public int getAlineacion() {
        return alineacion;
    }

    public void setAlineacion(int alineacion) {
        this.alineacion = alineacion;
    }
}
