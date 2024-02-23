package com.nwintle.fichaspokemon.controllers;

import com.google.gson.Gson;
import com.nwintle.fichaspokemon.PlayerClickListener;
import com.nwintle.fichaspokemon.api.Endpoint;
import com.nwintle.fichaspokemon.api.NetworkThread;
import com.nwintle.fichaspokemon.models.Entrenador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PlayerController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private ImageView img;

    @FXML
    private Label money;

    @FXML
    private Label name;

    @FXML
    private Label surname;

    @FXML
    private Label id;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button bVerPokemon;


    private Entrenador selectedPlayer;

    private PlayerClickListener clickListener;

    private List<Entrenador> getApiPlayers()  {
        Gson gson = new Gson();

        List<Entrenador> players = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String json = "";
            BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
            NetworkThread thread = new NetworkThread(Endpoint.API.getEndpoint() + Endpoint.TRAINER.getEndpoint() + i, queue);
            thread.start();

            try {
                json = queue.take();
                System.out.println(json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Entrenador player = gson.fromJson(json, Entrenador.class);
            players.add(player);
        }

        return players;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Entrenador> players = getApiPlayers();

        setVisibilityLeftPanel(false);
        if (!players.isEmpty()) {
            clickListener = new PlayerClickListener() {
                @Override
                public void clickListener(Entrenador player) {
                    System.out.println(player.getNombre());
                    selectedPlayer(player);
                    selectedPlayer = player;
                }
            };
            int row = 0;
            int col = 1;
            try {
                for (int i = 1; i < players.size(); i++) {
                    //System.out.println(players.get(i).getNombre());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/player-item.fxml"));
                    AnchorPane anchorPane = loader.load();
                    PlayerItemController controller = loader.getController();
                    controller.setData(players.get(i), clickListener);
                    if (col == 3) {
                        col = 1;
                        row++;
                    }
                    grid.add(anchorPane, col++, row);
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(20));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void selectedPlayer(Entrenador player) {
        id.setText("#" + player.getId());
        name.setText(player.getNombre());
        surname.setText(player.getApellido());
        money.setText(player.getDinero() + "€");
        if (player.getIcono() != null) {
            if (player.getIcono().contains("http")) {
                img.setImage(new Image(player.getIcono()));
            } else {
                img.setImage(new Image(Objects.requireNonNull(PlayerController.class.getResourceAsStream(player.getIcono()))));
            }
        } else {
            img.setImage(new Image(Objects.requireNonNull(PlayerController.class.getResourceAsStream("/img/OpenPokeball.png"))));
        }

        setVisibilityLeftPanel(true);
    }

    @FXML
    void verPokemonClicked(MouseEvent event) {
        Parent fxml = null;
        try {
            PokemonController.setEntrenador(selectedPlayer);
            fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pokemon.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(fxml);
    }

    private void setVisibilityLeftPanel(Boolean bool) {
        money.setVisible(bool);
        name.setVisible(bool);
        surname.setVisible(bool);
        img.setVisible(bool);
        id.setVisible(bool);
        bVerPokemon.setVisible(bool);
    }

    private List<Entrenador> getData() {
        List<Entrenador> players = new ArrayList<>();

        Entrenador player;
        float money = 10000;
        for (int i = 0; i < 10; i++) {
            //player = new Player(i, "PlayerName", "PlayerSurname", "/img/OpenPokeball.png", money);
            //players.add(player);
            money += 10000;
        }
        return players;
    }


}
