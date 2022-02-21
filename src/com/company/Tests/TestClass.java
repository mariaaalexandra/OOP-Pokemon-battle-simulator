package com.company.Tests;

import com.company.Adventure.Adventure;
import com.company.Adventure.Adventure_1_observer;
import com.company.Adventure.Adventure_2_observer;
import com.company.Adventure.Adventure_3_observer;
import com.company.CostlyObjects.CostlyObjectsPool;
import com.company.Helper.Helper;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.Input.InputManager;
import com.company.Item.ItemDatabase;
import com.company.Item.ItemsList;
import com.company.Pokemon.Pokemon;
import com.company.Pokemon.PokemonDatabase;
import com.company.Trainer.Trainer;
import com.company.Trainer.TrainerDatabase;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestClass {
    /* Fields */
    private TrainerDatabase myDatabase = null;
    private Integer only_once = 0;

    @Before
    public void setup() throws IOException, ParseException {
        if(only_once == 0) {
            /* Get test file */
            String default_path = "src/jsonFiles/Test1.json";
            try {
                InputManager.readData(default_path);
                myDatabase = TrainerDatabase.getInstance();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            only_once = 1;
        }
    }

   @After
    public void deleteDatabase() {
        myDatabase.destroyDatabase();
        TrainerDatabase.destroyMe();
        myDatabase = null;
    }

    @BeforeEach
    public void clearDatabase() {
        TrainerDatabase.destroyMe();
    }

    @AfterEach
    public void exit() {
        TrainerDatabase.destroyMe();
    }

    @Test
    public void checkIfReadingWasSuccessful() {
        Assert.assertEquals(myDatabase.getTrainerList().size(), 2);
    }

    @Test
    public void checkIfTrainersReceivedPokemons() {
        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        Assert.assertEquals(myDatabase.getTrainerList().get(0).getMy_pokemons().size(), 3);
    }

    @Test
    public void checkIfPokemonsReceivedItems() {
        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        Assert.assertNotEquals(myDatabase.getTrainerList().get(0).getMy_pokemons().
                get(0).getMy_items().size(), 0);
    }

    @Test
    public void checkPokemonCreation() {
        Pokemon mypok = new Pokemon.CreatePokemon("",0,0,0,0,0,null,null,null).create();
        Assert.assertNotEquals(mypok, null);
    }

    @Test
    public void checkAddingObservers() {
        Adventure my_adventure = new Adventure();
        my_adventure.addObserver(new Adventure_1_observer());
        my_adventure.addObserver(new Adventure_2_observer());
        Assert.assertEquals(my_adventure.getObserverList().size(), 2);
    }

    @Test
    public void checkHistoryMomentoPattern() {
        /* Decide adventure */
        Adventure my_adventure = new Adventure();

        /* Add observers */
        my_adventure.addObserver(new Adventure_1_observer());
        my_adventure.addObserver(new Adventure_2_observer());
        my_adventure.addObserver(new Adventure_3_observer());

        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        /* Get trainer 2 and his pokemon list */
        Trainer trainer2 = TrainerDatabase.getInstance().getTrainerList().get(1);
        pokemonList = new ArrayList<>();
        for(int i = 3 ; i < 6; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer2.setMy_pokemons(pokemonList);

        /* Update trainers pokemon`s to the newest stats (with items) */
        for(var pokemon: trainer1.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        for(var pokemon : trainer2.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        /* Keep history for future logging history */
        FullDatabase my_state = new FullDatabase();
        CareTaker my_history_log = new CareTaker();

        /* Log starting status */
        Helper.logCurrentStateToHistory(my_state, my_history_log);

        Assert.assertNotEquals(my_history_log.get(0), null);
    }

    @Test
    public void checkHistoryMomentoPatternLong() {
        /* Decide adventure */
        Adventure my_adventure = new Adventure();

        /* Add observers */
        my_adventure.addObserver(new Adventure_1_observer());
        my_adventure.addObserver(new Adventure_2_observer());
        my_adventure.addObserver(new Adventure_3_observer());

        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        /* Get trainer 2 and his pokemon list */
        Trainer trainer2 = TrainerDatabase.getInstance().getTrainerList().get(1);
        pokemonList = new ArrayList<>();
        for(int i = 3 ; i < 6; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer2.setMy_pokemons(pokemonList);

        /* Update trainers pokemon`s to the newest stats (with items) */
        for(var pokemon: trainer1.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        for(var pokemon : trainer2.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        /* Keep history for future logging history */
        FullDatabase my_state = new FullDatabase();
        CareTaker my_history_log = new CareTaker();

        /* Log starting status */
        Helper.logCurrentStateToHistory(my_state, my_history_log);

        /* Start an adventure from the 3 adventures */
        my_adventure.chooseAdventure(my_state, my_history_log);

        Assert.assertNotEquals(my_history_log.get(3), null);
    }

    @Test
    public void checkThatConsecutiveHistoriesAreDifferent() {
        /* Decide adventure */
        Adventure my_adventure = new Adventure();

        /* Add observers */
        my_adventure.addObserver(new Adventure_1_observer());
        my_adventure.addObserver(new Adventure_2_observer());
        my_adventure.addObserver(new Adventure_3_observer());

        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        /* Get trainer 2 and his pokemon list */
        Trainer trainer2 = TrainerDatabase.getInstance().getTrainerList().get(1);
        pokemonList = new ArrayList<>();
        for(int i = 3 ; i < 6; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer2.setMy_pokemons(pokemonList);

        /* Update trainers pokemon`s to the newest stats (with items) */
        for(var pokemon: trainer1.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        for(var pokemon : trainer2.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        /* Keep history for future logging history */
        FullDatabase my_state = new FullDatabase();
        CareTaker my_history_log = new CareTaker();

        /* Log starting status */
        Helper.logCurrentStateToHistory(my_state, my_history_log);

        /* Start an adventure from the 3 adventures */
        my_adventure.chooseAdventure(my_state, my_history_log);

        Assert.assertNotEquals(my_history_log.get(0), my_history_log.get(1));
    }

    @Test
    public void checkThatNonConsecutiveHistoriesAreDifferent() {
        /* Decide adventure */
        Adventure my_adventure = new Adventure();

        /* Add observers */
        my_adventure.addObserver(new Adventure_1_observer());
        my_adventure.addObserver(new Adventure_2_observer());
        my_adventure.addObserver(new Adventure_3_observer());

        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        /* Get trainer 2 and his pokemon list */
        Trainer trainer2 = TrainerDatabase.getInstance().getTrainerList().get(1);
        pokemonList = new ArrayList<>();
        for(int i = 3 ; i < 6; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer2.setMy_pokemons(pokemonList);

        /* Update trainers pokemon`s to the newest stats (with items) */
        for(var pokemon: trainer1.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        for(var pokemon : trainer2.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        /* Keep history for future logging history */
        FullDatabase my_state = new FullDatabase();
        CareTaker my_history_log = new CareTaker();

        /* Log starting status */
        Helper.logCurrentStateToHistory(my_state, my_history_log);

        /* Start an adventure from the 3 adventures */
        my_adventure.chooseAdventure(my_state, my_history_log);

        Assert.assertNotEquals(my_history_log.get(2), my_history_log.get(4));
    }

    @Test
    public void checkSuccessfulExtractionFromItemsList() {
        Assert.assertNotEquals(new ItemsList().extractItem(), null);
    }
}

