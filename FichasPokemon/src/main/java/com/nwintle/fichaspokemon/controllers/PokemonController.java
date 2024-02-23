package com.nwintle.fichaspokemon.controllers;

import com.google.gson.Gson;
import com.nwintle.fichaspokemon.PokeClickListener;
import com.nwintle.fichaspokemon.api.Endpoint;
import com.nwintle.fichaspokemon.api.NetworkThread;
import com.nwintle.fichaspokemon.api.UpdateApi;
import com.nwintle.fichaspokemon.models.Entrenador;
import com.nwintle.fichaspokemon.models.Pokemon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PokemonController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label hp;

    @FXML
    private Label lvl;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private ImageView img;

    @FXML
    private Label value;

    @FXML
    private Label atk;

    @FXML
    private Label def;

    @FXML
    private Label spd;

    @FXML
    private Pane lifeBar;

    @FXML
    private Pane imgContainer;

    @FXML
    private Pane lifeBarContainer;

    @FXML
    private TextField editSpd;

    @FXML
    private TextField editType;

    @FXML
    private TextField editValue;

    @FXML
    private TextField editAtk;

    @FXML
    private TextField editDef;

    @FXML
    private TextField editGender;

    @FXML
    private TextField editHp;

    @FXML
    private TextField editLvl;

    @FXML
    private TextField editName;

    @FXML
    private Pane editPane;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonBuyHp;

    private PokeClickListener clickListener;
    private int min = 0;
    private int max = 9;
    private List<Pokemon> pokemonList = getApiPokemon();
    private int maxPokemon;
    boolean firstTimeRun = true;
    private static Entrenador selectedPlayer;
    private static Boolean selectedMercado;
    private Pokemon selectedPokemon;




    public static void setEntrenador(Entrenador player) {
        selectedPlayer = player;
    }
    public static void setMercado(Boolean bool) {
        selectedMercado = bool;
    }
    private List<Pokemon> getApiPokemon() {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        Gson gson = new Gson();


        if (selectedPlayer != null) {
            maxPokemon = selectedPlayer.getPokemons().size();
            return selectedPlayer.getPokemons();
        } else if (selectedMercado) {
            NetworkThread thread = new NetworkThread(Endpoint.API.getEndpoint() + Endpoint.MERCADO.getEndpoint(), queue);
            thread.start();
        } else {
            NetworkThread thread = new NetworkThread(Endpoint.API.getEndpoint() + Endpoint.POKEMON_ALL.getEndpoint(), queue);
            thread.start();
        }


        String json;
        try {
            json = queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Pokemon> pokemons = Arrays.asList(gson.fromJson(json,Pokemon[].class));
        maxPokemon = pokemons.size();
        return pokemons;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editPane.setVisible(false);
        gridPane.getChildren().clear();
        if (firstTimeRun)
            setVisibilityLeftPanel(false);
        if (!pokemonList.isEmpty()) {
            firstTimeRun = false;
            clickListener = new PokeClickListener() {
                @Override
                public void clickListener(Pokemon pokemon) {
                    System.out.println(pokemon.getName());
                    selectedPokemon(pokemon);
                }
            };
            int row = 0;
            int col = 1;
            try {
                for (int i = min; i < max; i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/poke-item.fxml"));
                    AnchorPane anchorPane = loader.load();
                    PokemonItemController controller = loader.getController();
                    try {
                        controller.setData(pokemonList.get(i), clickListener);
                    } catch (IndexOutOfBoundsException ioobe){
                        break;
                    }

                    if (col == 4) {
                        col = 1;
                        row++;
                    }
                    gridPane.add(anchorPane, col++, row);
                    gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(20));

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void selectedPokemon(Pokemon pokemon) {
        int maxLifeWidth = 177;
        name.setText(pokemon.getName());
        value.setText(pokemon.getValor() + "€");
        hp.setText(pokemon.getHp() + " / 100" );
        id.setText("#" + pokemon.getId());
        lvl.setText("lvl." + pokemon.getLevel());
        atk.setText("Atk: " + pokemon.getAttack());
        def.setText("Def: " + pokemon.getDefence());
        spd.setText("Spd: " + pokemon.getSpeed());
        if (pokemon.getHiresURL().contains("http")) {
            img.setImage(new Image(pokemon.getHiresURL()));
        } else {
            img.setImage(new Image(Objects.requireNonNull(PokemonController.class.getResourceAsStream(pokemon.getHiresURL()))));
        }
        lifeBar.setPrefWidth(maxLifeWidth * (pokemon.getHp() * 0.01));
        setLifeBarColour(pokemon.getHp());
        selectedPokemon = pokemon;
        setVisibilityLeftPanel(true);
    }

    private void setLifeBarColour(int hp) {
        if (hp >= 50) {
            lifeBar.setStyle("-fx-background-color:" + "darkgreen" + ";\n" + "-fx-background-radius: 10;");
        } else if (hp >= 25) {
            lifeBar.setStyle("-fx-background-color:" + "gold" + ";\n" + "-fx-background-radius: 10;");
        } else {
            lifeBar.setStyle("-fx-background-color:" + "darkred" + ";\n" + "-fx-background-radius: 10;");
        }
    }

    private void setVisibilityLeftPanel(Boolean bool) {
        hp.setVisible(bool);
        name.setVisible(bool);
        id.setVisible(bool);
        lvl.setVisible(bool);
        img.setVisible(bool);
        imgContainer.setVisible(bool);
        lifeBar.setVisible(bool);
        lifeBarContainer.setVisible(bool);
        value.setVisible(bool);
        atk.setVisible(bool);
        def.setVisible(bool);
        spd.setVisible(bool);
        buttonEdit.setVisible(bool);
        buttonBuyHp.setVisible(bool);
    }

    @FXML
    void editClicked(MouseEvent event) {
        editPane.setVisible(true);
    }

    @FXML
    void editConfirm(MouseEvent event) {
        CharSequence newName = selectedPokemon.getName();
        CharSequence newType = selectedPokemon.getType();
        CharSequence newLvl = selectedPokemon.getLevel() + "";
        CharSequence newHp = selectedPokemon.getHp() + "";
        CharSequence newAtk= selectedPokemon.getAttack() + "";
        CharSequence newDef = selectedPokemon.getDefence() + "";
        CharSequence newSpd = selectedPokemon.getSpeed() + "";
        CharSequence newGender = selectedPokemon.getGender();
        CharSequence newValue = selectedPokemon.getValor() + "";

        if (!editName.getCharacters().isEmpty())
            newName = editName.getCharacters();
        if (!editType.getCharacters().isEmpty())
            newType = editType.getCharacters();
        if (!editLvl.getCharacters().isEmpty())
            newLvl = editLvl.getCharacters();
        if (!editHp.getCharacters().isEmpty())
            newHp = editHp.getCharacters();
        if (!editAtk.getCharacters().isEmpty())
            newAtk = editAtk.getCharacters();
        if (!editDef.getCharacters().isEmpty())
            newDef = editDef.getCharacters();
        if (!editSpd.getCharacters().isEmpty())
            newSpd = editSpd.getCharacters();
        if (!editGender.getCharacters().isEmpty())
            newGender = editGender.getCharacters();
        if (!editValue.getCharacters().isEmpty())
            newValue = editValue.getCharacters();

        String json = "{\"id\":" + selectedPokemon.getId() + ",\"name\":\"" + newName + "\",\"type\":\"" + newType + "\",\"level\":" + newLvl + ",\"hp\":" + newHp + ",\"attack\":" + newAtk + ",\"defence\":" + newDef + ",\"speed\":" + newSpd + ",\"gender\":\"" + newGender + "\",\"valor\":" + newValue + ",\"hiresURL\":\"" + selectedPokemon.getHiresURL() + "\",\"entrenador\":" + selectedPokemon.getEntrenador() + ",\"mercado\":" + selectedPokemon.getMercado() + ",\"alineacion\":" + selectedPokemon.getAlineacion() + "}";

        UpdateApi update = new UpdateApi();
        update.updatePokemon(selectedPokemon.getId(), json);

        editPane.setVisible(false);
    }

    @FXML
    void buyHp(MouseEvent event) {
        UpdateApi api = new UpdateApi();
        api.buyHp(selectedPokemon.getId());
    }

    @FXML
    void nextClicked(MouseEvent event) {
        if (max < maxPokemon) {
            min += 9;
            max += 9;
            System.out.println("maxPoke: "+ maxPokemon);
            System.out.println("min:" + min + " max:" + max);
            initialize(null, null);
        }
    }

    @FXML
    void prevClicked(MouseEvent event) {
        if (min != 0) {
            min -= 9;
            max -= 9;
            System.out.println("min:" + min + " max:" + max);
            initialize(null, null);
        }
    }

    private List<Pokemon> getData() {
        List<Pokemon> mons = new ArrayList<>();
        Pokemon pokemon;
        int hpNum = 5;
        for (int i = 0; i < 10; i++) {
            //pokemon = new Pokemon(i, "PokemonName", "GRASS", 10, 10, 10, "POS", 1000, 2, hpNum, "https://static.wikia.nocookie.net/pokemon/images/2/21/001Bulbasaur.png/revision/latest?cb=20200620223551");
            //mons.add(pokemon);
            hpNum += 5;
        }
        return mons;
    }
}
