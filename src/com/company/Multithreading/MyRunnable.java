package com.company.Multithreading;

import com.company.Helper.Logger;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.Pokemon.Pokemon;

public class MyRunnable implements Runnable {

    /* Fields */
    Pokemon my_pokemon = null;
    Pokemon enemy = null;
    FullDatabase my_state = null;
    CareTaker my_history_log = null;

    /* Constructor */
    public MyRunnable(Pokemon my_pokemon, Pokemon enemy, FullDatabase my_state, CareTaker my_history_log) {
        this.my_pokemon = my_pokemon;
        this.enemy = enemy;
        this.my_state = my_state;
        this.my_history_log = my_history_log;
    }

    /* Methods */
    @Override
    public void run() {
        Logger.pokemonBattle(my_pokemon, enemy, my_state, my_history_log);
    }
}
