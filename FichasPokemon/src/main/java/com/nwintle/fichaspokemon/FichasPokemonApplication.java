package com.nwintle.fichaspokemon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FichasPokemonApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // CAMBIAR IP/Nombre de dominio en Endpoint, en la carpeta api
        FXMLLoader fxmlLoader = new FXMLLoader(FichasPokemonApplication.class.getResource("/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Pokemon Manager");
        stage.getIcons().add(new Image(Objects.requireNonNull(FichasPokemonApplication.class.getResourceAsStream("/img/OpenPokeball.png"))));
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}