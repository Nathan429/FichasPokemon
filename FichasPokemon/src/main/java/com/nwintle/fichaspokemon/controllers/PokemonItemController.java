package com.nwintle.fichaspokemon.controllers;

import com.nwintle.fichaspokemon.PokeClickListener;
import com.nwintle.fichaspokemon.models.Pokemon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class PokemonItemController {
    @FXML
    private Label hp;

    @FXML
    private Label id;

    @FXML
    private Label lvl;

    @FXML
    private Label name;

    @FXML
    private Label pos;

    @FXML
    private Label value;


    @FXML
    private Pane lifeBar;

    @FXML
    private ImageView img;

    @FXML
    private Label atk;

    @FXML
    private Label def;

    @FXML
    private Label spd;


    private Pokemon pokemon;

    private PokeClickListener clickListener;

    public void setData(Pokemon pokemon, PokeClickListener clickListener) {
        int maxLifeWidth = 160;
        this.pokemon = pokemon;
        this.clickListener = clickListener;
        name.setText(pokemon.getName());
        value.setText(pokemon.getValor() + "€");
        hp.setText(pokemon.getHp() + " / 100" );
        id.setText("#" + pokemon.getId());
        lvl.setText("lvl." + pokemon.getLevel());
        atk.setText("Atk: " + pokemon.getAttack());
        def.setText("Def: " + pokemon.getDefence());
        spd.setText("Spd: " + pokemon.getSpeed());

        // ENABLE CODE BELOW FOR IMAGES ON INDIVIDUAL ITEMS IN POKÉMON VIEW AND MERCADO
        // IMPLEMENT BUTTON TO DISABLE IMAGES INSIDE THE APP
        //if (pokemon.getHiresURL().contains("http")) {
        //    img.setImage(new Image(pokemon.getHiresURL()));
        //} else {
        //    img.setImage(new Image(Objects.requireNonNull(PokemonItemController.class.getResourceAsStream(pokemon.getHiresURL()))));
        //}
        lifeBar.setPrefWidth(maxLifeWidth * (pokemon.getHp() * 0.01));
        setLifeBarColour(pokemon.getHp());
    }

    private void setLifeBarColour(int hp) {
        if (hp >= 50) {
            lifeBar.setStyle("-fx-background-color:" + "darkgreen" + ";\n" + "-fx-background-radius: 10;");
        } else if (hp < 50 && hp >= 25) {
            lifeBar.setStyle("-fx-background-color:" + "gold" + ";\n" + "-fx-background-radius: 10;");
        } else if (hp < 25) {
            lifeBar.setStyle("-fx-background-color:" + "darkred" + ";\n" + "-fx-background-radius: 10;");
        }
    }

    @FXML
    void mouseClicked(MouseEvent event) {
        clickListener.clickListener(pokemon);
    }
}
