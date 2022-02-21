package com.company.Pokemon;

import java.util.ArrayList;
import java.util.List;

/* Hold pokemon database */
public class PokemonDatabase {
    /* Fields */
    public static List<Pokemon> pokemonList = new ArrayList<>();

    /* Singleton */
    public PokemonDatabase(){}

    /* Getters & setters */
    public List<Pokemon> getPokemonList() {
        return this.pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    /* Methods */
    public void addPokemon(Pokemon pokemon) {
        this.pokemonList.add(pokemon);
    }
}
