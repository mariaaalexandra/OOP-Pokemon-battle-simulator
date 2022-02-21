package com.company.Adventure;

import com.company.Helper.Helper;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adventure implements Observable {
    /* Fields */
    private Integer adventure_number = 0;
    private List<Observer> observerList = new ArrayList<>();

    /* Constructor */
    public Adventure() {}

    /* Methods */
    /* Decide on the adventure type */
    public void chooseAdventure(FullDatabase my_state, CareTaker my_history_log) {
        adventure_number = new Random().nextInt(3);
        notifyObservers(adventure_number, my_state, my_history_log);
    }

    @Override
    public void addObserver(Observer my_observer) {
        observerList.add(my_observer);
    }

    @Override
    public void removeObserver(Observer my_observer) {
        observerList.remove(my_observer);
    }

    @Override
    public void notifyObservers(Integer adventureType, FullDatabase my_state, CareTaker my_history_log) {
        for(var observer : observerList) {
            observer.update(adventureType, my_state, my_history_log);
        }
    }

    public List<Observer> getObserverList() {
        return this.observerList;
    }
}
