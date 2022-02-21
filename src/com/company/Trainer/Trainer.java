package com.company.Trainer;

import com.company.Pokemon.Pokemon;
import com.company.Pokemon.PokemonDatabase;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    /* Fields */
    private String name = "";
    private Integer age = 0;
    private List<Pokemon> my_pokemons;

    /* Constructors */
    public Trainer() {}

    public Trainer(String trainer_name, Integer trainer_age) {
        this.name = trainer_name;
        this.age = trainer_age;
    }

    /* Getters and setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Pokemon> getMy_pokemons() {
        return my_pokemons;
    }

    public void setMy_pokemons(List<Pokemon> my_pokemons) {
        this.my_pokemons = my_pokemons;
    }

    /* Methods */
    @Override
    public String toString() {
        String trainer = "Trainer: " + this.name + " | Age: " + this.age + "\n";
        String pokemons = "";
        if(my_pokemons != null) {
            for (var pokemon : this.my_pokemons) {
                pokemons += pokemon.getName() + "\n";
            }
        }
        return trainer + pokemons;
    }

    /* Get best pokemon by score */
    public Pokemon getBestPokemon() {
        Integer max = Integer.MIN_VALUE;
        Pokemon wanted_pokemon = this.my_pokemons.get(0);
        for(var pokemon : this.my_pokemons) {
            if(max < pokemon.calculatePokemonScore()) {
                max = pokemon.calculatePokemonScore();
                wanted_pokemon = pokemon;
            }
        }
        return wanted_pokemon;
    }
}
