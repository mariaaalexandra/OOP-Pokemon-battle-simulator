package com.company.Adventure;

import com.company.Helper.Helper;
import com.company.Helper.Logger;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.Multithreading.MyRunnable;
import com.company.Pokemon.Pokemon;
import com.company.Pokemon.PokemonDatabase;
import com.company.Trainer.Trainer;
import com.company.Trainer.TrainerDatabase;

import java.util.ArrayList;
import java.util.List;

public class Adventure_3_observer implements Observer {
    /* Fields */
    public static Integer trainer1_points = 0, trainer2_points = 0;

    /* Methods */
    @Override
    public void update(Integer adventureType, FullDatabase my_state, CareTaker my_history_log) {
        if(adventureType == 0) {
            battle(my_state, my_history_log);
        }
    }

    private void battle(FullDatabase my_state, CareTaker my_history_log) {
        /* Get trainer 1 and trainer 2  */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        Trainer trainer2 = TrainerDatabase.getInstance().getTrainerList().get(1);

        System.out.println("\n------------ Beginning adventure: Battle trainer: " +
                trainer1.getName() + " with trainer: " + trainer2.getName() + " ------------ \n");

        /* Get pokemons */
        var pok1_trainer1 = trainer1.getMy_pokemons().get(0);
        var pok1_trainer1_health = pok1_trainer1.getHitPoints();
        var pok2_trainer1 = trainer1.getMy_pokemons().get(1);
        var pok2_trainer1_health = pok2_trainer1.getHitPoints();
        var pok3_trainer1 = trainer1.getMy_pokemons().get(2);
        var pok3_trainer1_health = pok3_trainer1.getHitPoints();
        var pokbest_trainer1 = trainer1.getBestPokemon();
        var pokbest_trainer1_health = pokbest_trainer1.getHitPoints();
        var pok1_trainer2 = trainer2.getMy_pokemons().get(0);
        var pok1_trainer2_health = pok1_trainer2.getHitPoints();
        var pok2_trainer2 = trainer2.getMy_pokemons().get(1);
        var pok2_trainer2_health = pok2_trainer2.getHitPoints();
        var pok3_trainer2 = trainer2.getMy_pokemons().get(2);
        var pok3_trainer2_health = pok3_trainer2.getHitPoints();
        var pokbest_trainer2 = trainer2.getBestPokemon();
        var pokbest_trainer2_health = pokbest_trainer2.getHitPoints();

        /* Execute battles */
        /* Battle 1 */
        Integer prev_points = trainer1_points;
        Thread t1 = new Thread(new MyRunnable(pok1_trainer1, pok1_trainer2, my_state, my_history_log));
        t1.start();
        pok1_trainer1.setHitPoints(pok1_trainer1_health);
        pok1_trainer2.setHitPoints(pok1_trainer2_health);
        if(prev_points < trainer1_points) {
            /* Winner is pokemon 1 */
            pok1_trainer1.increaseAll();
        } else {
            pok1_trainer2.increaseAll();
        }
        Helper.logCurrentStateToHistory(my_state, my_history_log);


        /* Battle 2 */
        prev_points = trainer1_points;
        Thread t2 = new Thread(new MyRunnable(pok2_trainer1, pok2_trainer2, my_state, my_history_log));
        t2.start();
        pok2_trainer1.setHitPoints(pok2_trainer1_health);
        pok2_trainer2.setHitPoints(pok2_trainer2_health);
        if(prev_points < trainer1_points) {
            /* Winner is pokemon 1 */
            pok2_trainer1.increaseAll();
        } else {
            pok2_trainer2.increaseAll();
        }
        Helper.logCurrentStateToHistory(my_state, my_history_log);


        /* Battle 3 */
        prev_points = trainer1_points;
        Thread t3 = new Thread(new MyRunnable(pok3_trainer1, pok3_trainer2, my_state, my_history_log));
        t3.start();
        pok3_trainer1.setHitPoints(pok3_trainer1_health);
        pok3_trainer2.setHitPoints(pok3_trainer2_health);
        if(prev_points < trainer1_points) {
            /* Winner is pokemon 1 */
            pok2_trainer1.increaseAll();
        } else {
            pok2_trainer2.increaseAll();
        }
        Helper.logCurrentStateToHistory(my_state, my_history_log);


        /* Battle 4 */
        prev_points = trainer1_points;
        Thread t4 = new Thread(new MyRunnable(pokbest_trainer1, pokbest_trainer2, my_state, my_history_log));
        t4.start();
        pokbest_trainer1.setHitPoints(pokbest_trainer1_health);
        pokbest_trainer2.setHitPoints(pokbest_trainer2_health);
        if(prev_points < trainer1_points) {
            /* Winner is pokemon 1 */
            pok2_trainer1.increaseAll();
        } else {
            pok2_trainer2.increaseAll();
        }
        Helper.logCurrentStateToHistory(my_state, my_history_log);


        if(trainer1_points > trainer2_points) {
            System.out.println("Winner trainer " + trainer1.getName() + "!");
        } else {
            System.out.println("Winner trainer " + trainer2.getName() + "!");
        }

    }
}
