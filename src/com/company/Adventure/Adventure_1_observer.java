package com.company.Adventure;

import com.company.CostlyObjects.CostlyObjectsPool;
import com.company.Helper.Helper;
import com.company.Helper.Logger;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.Item.Item;
import com.company.Item.ItemDatabase;
import com.company.Item.ItemsList;
import com.company.Pokemon.Pokemon;
import com.company.Pokemon.PokemonDatabase;
import com.company.Trainer.Trainer;
import com.company.Trainer.TrainerDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adventure_1_observer extends Neutrel implements Observer {
    /* Fields */
    protected List<Item> my_items = null;

    /* Constructor */
    public Adventure_1_observer() {
        /* Create randomized neutrel pokemon */
        var randomObject = new Random();
        this.neutrelType = 1;
        this.fullName = this.pokemonType + this.neutrelType;
        this.maxHealth = randomObject.nextInt(maxHealth);
        this.maxDamage = randomObject.nextInt(maxDamage);
        this.maxSpecialDamage = randomObject.nextInt(maxSpecialDamage);
        this.maxDefense = randomObject.nextInt(maxDefense);
        this.maxSpecialDefense = randomObject.nextInt(maxSpecialDefense);
        
        this.my_ability1.setCd(randomObject.nextInt(5));
        this.my_ability1.setDmg(randomObject.nextInt(2));
        this.my_ability1.setDodge("yes");
        this.my_ability1.setStun("no");
        
        this.my_ability2.setCd(randomObject.nextInt(5));
        this.my_ability2.setDmg(randomObject.nextInt(2));
        this.my_ability2.setDodge("no");
        this.my_ability2.setStun("no");

        my_items = new ArrayList<>();
        Integer nr_items = randomObject.nextInt(3);
        var my_item_total_list = new CostlyObjectsPool().create();
        for(int i = 0; i < nr_items; ++i) {
            my_items.add(my_item_total_list.extractItem());
        }
    }

    /* Methods */
    @Override
    public void update(Integer adventureType, FullDatabase my_state, CareTaker my_history_log) {
        if(adventureType == 1) {
            battle(my_state, my_history_log);
        }
    }

    /* Battle the neutrel enemy */
    protected void battle(FullDatabase my_state, CareTaker my_history_log) {
        /* Get neutrel */
        Pokemon Neutrel1_enemy = new Pokemon.CreatePokemon(this.fullName, this.maxHealth,
                    this.maxDamage, this.maxSpecialDamage, this.maxDefense,
                this.maxSpecialDefense, this.my_ability1, this.my_ability2, my_items).create();

        System.out.println("\n------------ Beginning adventure: Battle to neutrel1 ------------ \n");

        /* Get best pokemon from each trainer */
        var trainer1_pokemon = TrainerDatabase.getInstance().getTrainerList().get(0).getBestPokemon();
        var trainer2_pokemon = TrainerDatabase.getInstance().getTrainerList().get(1).getBestPokemon();

        /* Battle pokemons */
        Logger.pokemonBattle(trainer1_pokemon, Neutrel1_enemy, my_state, my_history_log);
        Neutrel1_enemy.setHitPoints(new Random().nextInt(50));
        Logger.pokemonBattle(trainer2_pokemon, Neutrel1_enemy, my_state, my_history_log);
    }
}
