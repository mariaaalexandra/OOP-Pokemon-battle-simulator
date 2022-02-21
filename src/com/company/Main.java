package com.company;

import com.company.Adventure.Adventure;
import com.company.Adventure.Adventure_1_observer;
import com.company.Adventure.Adventure_2_observer;
import com.company.Adventure.Adventure_3_observer;
import com.company.CostlyObjects.CostlyObjectsPool;
import com.company.Helper.Helper;
import com.company.Helper.Logger;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.Input.InputManager;
import com.company.Trainer.TrainerDatabase;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        /* Get test file */
        String default_path = "src/JsonFiles/Test";
        final String extension = ".json";
        Integer test_number = 0;
        while(test_number > 10 || test_number <= 0) {
            System.out.printf("Test number from 1 to 10: ");
            try {
                test_number = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        default_path = default_path + test_number + extension;

        /* Read input from test file */
        try {
            InputManager.readData(default_path);
        } catch (Exception e) {
            System.out.println(e.getCause() + " " + e.getMessage());
        }

        /* Decide adventure */
        Adventure my_adventure = new Adventure();

        /* Add observers */
        my_adventure.addObserver(new Adventure_1_observer());
        my_adventure.addObserver(new Adventure_2_observer());
        my_adventure.addObserver(new Adventure_3_observer());

        /* Print starting info */
        Logger.printAllDatabases();

        /* Keep history for future logging history */
        FullDatabase my_state = new FullDatabase();
        CareTaker my_history_log = new CareTaker();

        /* Log starting status */
        Helper.logCurrentStateToHistory(my_state, my_history_log);

        /* Start an adventure from the 3 adventures */
        my_adventure.chooseAdventure(my_state, my_history_log);
    }
}
