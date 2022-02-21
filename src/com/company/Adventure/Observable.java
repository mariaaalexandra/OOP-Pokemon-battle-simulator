package com.company.Adventure;

import com.company.History.CareTaker;
import com.company.History.FullDatabase;

public interface Observable {
    public void addObserver(Observer my_observer);
    public void removeObserver(Observer my_observer);
    public void notifyObservers(Integer adventureType, FullDatabase my_state, CareTaker my_history_log);
}
