package com.nwintle.fichaspokemon.controllers;

import com.nwintle.fichaspokemon.PlayerClickListener;
import com.nwintle.fichaspokemon.models.Entrenador;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class PlayerItemController {

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

    private Entrenador player;

    private PlayerClickListener clickListener;

    @FXML
    void mouseClicked(MouseEvent event) {
        clickListener.clickListener(player);
    }

    public void setData(Entrenador player, PlayerClickListener clickListener) {
        this.player = player;
        this.clickListener = clickListener;
        name.setText(player.getNombre());
        surname.setText(player.getApellido());
        money.setText(player.getDinero() + "€");
        id.setText("#" + player.getId());
        if (player.getIcono() != null) {
            if (player.getIcono().contains("http")) {
                img.setImage(new Image(player.getIcono()));
            } else {
                img.setImage(new Image(Objects.requireNonNull(PlayerController.class.getResourceAsStream(player.getIcono()))));
            }
        } else {
            img.setImage(new Image(Objects.requireNonNull(PlayerController.class.getResourceAsStream("/img/OpenPokeball.png"))));
        }

    }


}
