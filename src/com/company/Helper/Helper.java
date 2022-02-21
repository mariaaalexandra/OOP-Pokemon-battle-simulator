package com.company.Helper;

import com.company.CostlyObjects.CostlyObjectsPool;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.History.MomentoState;
import com.company.Item.Item;
import com.company.Item.ItemDatabase;
import com.company.Item.ItemsList;
import com.company.Pokemon.PokemonDatabase;
import com.company.Trainer.TrainerDatabase;

import javax.swing.text.Caret;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static void logCurrentStateToHistory(FullDatabase my_state, CareTaker my_history_log) {
        my_state.setState(new MomentoState(TrainerDatabase.getInstance().getTrainerList(),
                PokemonDatabase.pokemonList, new CostlyObjectsPool().create().getItems()));
        my_history_log.add(my_state.saveStateToMemento());
    }

    public static List<Item> getAllItems() {
        List<Item> my_list = new ArrayList<>();
        for(var pokemon : PokemonDatabase.pokemonList) {
            for(var item : pokemon.getMy_items()) {
                my_list.add(item);
            }
        }
        return my_list;
    }
}
