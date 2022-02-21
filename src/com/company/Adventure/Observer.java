package com.company.Adventure;

import com.company.History.CareTaker;
import com.company.History.FullDatabase;

public interface Observer {
    public void update(Integer adventureType, FullDatabase my_state, CareTaker my_history_log);
}
