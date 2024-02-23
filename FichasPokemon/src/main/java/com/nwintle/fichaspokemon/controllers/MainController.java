package com.nwintle.fichaspokemon.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    @FXML
    private Pane pMain;

    // PLAYER BUTTON
    @FXML
    public void playersClicked(MouseEvent event) {
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/players.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pMain.getChildren().removeAll();
        pMain.getChildren().setAll(fxml);
    }

    // POKEMON BUTTON
    @FXML
    public void pokemonClicked(MouseEvent event) {
        PokemonController.setEntrenador(null);
        PokemonController.setMercado(false);
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pokemon.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pMain.getChildren().removeAll();
        pMain.getChildren().setAll(fxml);
    }

    // MERCADO BUTTON
    @FXML
    void mercadoClicked(MouseEvent event) {
        PokemonController.setEntrenador(null);
        PokemonController.setMercado(true);
        Parent fxml = null;
        try {
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pokemon.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pMain.getChildren().removeAll();
        pMain.getChildren().setAll(fxml);
    }

}
