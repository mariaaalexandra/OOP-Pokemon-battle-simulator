package com.company.History;

import com.company.Item.Item;
import com.company.Item.ItemsList;
import com.company.Pokemon.Pokemon;
import com.company.Trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class MomentoState {
    /* Fields */
    List<Trainer> trainerList = new ArrayList<>();
    List<Pokemon> pokemonList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();

    /* Constructors */
    public MomentoState() {}

    public MomentoState(MomentoState state) {
        this.trainerList = state.trainerList;
        this.pokemonList = state.pokemonList;
        this.itemList = state.itemList;
    }

    public MomentoState(List<Trainer> trainerList, List<Pokemon> pokemonList, List<Item> itemList) {
        this.trainerList = trainerList;
        this.pokemonList = pokemonList;
        this.itemList = itemList;
    }

    /* Getters */
    public MomentoState getState() {
        return this;
    }
}
