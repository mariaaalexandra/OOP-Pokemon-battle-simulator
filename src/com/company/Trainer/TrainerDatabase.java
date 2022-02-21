package com.company.Trainer;

import com.company.Trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

/* Singleton */
/* Hold Trainer database */
public class TrainerDatabase {
    /* Fields */
    private List<Trainer> TrainerList = new ArrayList<>();

    /* Singleton */
    private TrainerDatabase(){}

    private static TrainerDatabase instance = null;

    public static TrainerDatabase getInstance() {
        if (instance == null) {
            instance = new TrainerDatabase();
        }
        return instance;
    }

    /* Methods */
    public void addTrainer(Trainer Trainer) {
        this.TrainerList.add(Trainer);
    }

    public List<Trainer> getTrainerList() {
        return this.TrainerList;
    }

    public static void destroyMe() {
        TrainerDatabase.instance = null;
    }

    public void destroyDatabase() {
        var my_list  = getTrainerList();
        my_list = null;
    }
}
