package com.company.Adventure;

import com.company.CostlyObjects.CostlyObjectsPool;
import com.company.Helper.Logger;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.Pokemon.Pokemon;
import com.company.Pokemon.PokemonDatabase;
import com.company.Trainer.Trainer;
import com.company.Trainer.TrainerDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class Adventure_2_observer extends Adventure_1_observer implements Observer {


    /* Constructor */
    public Adventure_2_observer() {
        this.neutrelType = 2;
        this.fullName = this.pokemonType + this.neutrelType;
        my_items = new ArrayList<>();
        Integer nr_items = new Random().nextInt(3);
        var my_item_total_list = new CostlyObjectsPool().create();
        for(int i = 0; i < nr_items; ++i) {
            my_items.add(my_item_total_list.extractItem());
        }
    }

    /* Methods */
    @Override
    public void update(Integer adventureType, FullDatabase my_state, CareTaker my_history_log) {
        if(adventureType == 2) {
            battle(my_state, my_history_log);
        }
    }

    /* Battle the neutrel enemy */
    @Override
    protected void battle(FullDatabase my_state, CareTaker my_history_log) {

        /* Get neutrel */
        Pokemon Neutrel2_enemy = new Pokemon.CreatePokemon(this.fullName, this.maxHealth,
                this.maxDamage, this.maxSpecialDamage, this.maxDefense,
                this.maxSpecialDefense, this.my_ability1, this.my_ability2, my_items).create();

        System.out.println("\n------------ Beginning adventure: Battle to neutrel2 ------------ \n");

        /* Get best pokemon from each trainer */
        var trainer1_pokemon = TrainerDatabase.getInstance().getTrainerList().get(0).getBestPokemon();
        var trainer2_pokemon = TrainerDatabase.getInstance().getTrainerList().get(1).getBestPokemon();

        /* Battle pokemons */
        Logger.pokemonBattle(trainer1_pokemon, Neutrel2_enemy, my_state, my_history_log);
        Neutrel2_enemy.setHitPoints(new Random().nextInt(50));
        Logger.pokemonBattle(trainer2_pokemon, Neutrel2_enemy, my_state, my_history_log);
    }
}
