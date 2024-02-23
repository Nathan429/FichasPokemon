package com.nwintle.fichaspokemon.api;

public enum Endpoint {
    API("http://192.168.50.193:8080"),
    POKEMON_ALL("/pokemon/pokemons"),
    POKEMON("/pokemon"),
    TRAINER("/entrenador/"),
    MERCADO("/mostrarMercado");

    private final String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
