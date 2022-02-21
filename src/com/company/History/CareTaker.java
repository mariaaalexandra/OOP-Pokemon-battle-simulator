package com.company.History;

import java.util.ArrayList;
import java.util.List;

/* Momento design pattern */
public class CareTaker {
    /* Fields */
    private List<MomentoState> mementoList = new ArrayList<MomentoState>();

    /* Constructor */
   public CareTaker() {}

    /* Methods */
    public void add(MomentoState state){
        mementoList.add(state);
    }

    public MomentoState get(int index){
        return mementoList.get(index);
    }
}
