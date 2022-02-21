package com.company.Input;

import com.company.Item.Item;
import com.company.Pokemon.*;
import com.company.Trainer.Trainer;
import com.company.Trainer.TrainerDatabase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputManager {
    /**
     * Read all data from json
     */
    public static void readData(String input_file) throws IOException, ParseException {
        /* Take file into object */
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(input_file));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* Get JSON file */
        JSONObject jsonFile = (JSONObject) obj;

        /* Get list of trainers */
        JSONArray my_trainers = (JSONArray) jsonFile.get("Trainers");
        readTrainerData(my_trainers);

        /* Get list of pokemons and their items */
        JSONArray my_pokemons = (JSONArray) jsonFile.get("Pokemons");
        readPokemonData(my_pokemons);
    }

    /**
     * Read the trainers from the json file to the trainer database singleton
     * @param array_of_objects - objects from json
     */
    private static void readTrainerData(JSONArray array_of_objects) throws IOException, ParseException {
        /* Get trainers database */
        var database = TrainerDatabase.getInstance();

        /* Get trainers */
        for(var trainer : array_of_objects) {
            /* Get trainer object */
            JSONObject trainerOBJ = (JSONObject) trainer;

            /* Get name */
            String trainer_name = (String) trainerOBJ.get("name");

            /* Get age */
            long tmp = (long) trainerOBJ.get("age");
            Integer trainer_age = (int) tmp;

            /* Create trainer */
            Trainer my_trainer = new Trainer(trainer_name, trainer_age);

            /* Add trainer to the database */
            database.addTrainer(my_trainer);
        }
    }

    /**
     * Read the pokemons from the json file to the pokemon database
     * @param array_of_objects - array of objects from json
     */
    private static void readPokemonData(JSONArray array_of_objects) throws IOException, ParseException {
        /* Get database */
        var database = PokemonDatabase.pokemonList;

        /* Traverse list */
        for(Object pokemon : array_of_objects) {
            /* Get name */
            JSONObject pokemonOBJ = (JSONObject) pokemon;
            String pokemon_name = (String) pokemonOBJ.get("name");

            /* Get HP */
            long tmp = (long) pokemonOBJ.get("HP");
            Integer pokemon_HP = (int) tmp;

            /* Get normal attack */
            Integer pokemon_normal_attack;
            Object tmpx = pokemonOBJ.get("Normal Attack");
            if(tmpx == null) {
               pokemon_normal_attack = null;
            } else
            {
                tmp = (long) pokemonOBJ.get("Normal Attack");
                pokemon_normal_attack = (int) tmp;
            }

            /* Get special attack */
            Integer pokemon_special_attack;
            tmpx = pokemonOBJ.get("Special Attack");
            if(tmpx == null) {
                pokemon_special_attack = null;
            } else
            {
                tmp = (long) pokemonOBJ.get("Special Attack");
                pokemon_special_attack = (int) tmp;
            }

            /* Get def */
            tmp = (long) pokemonOBJ.get("Def");
            Integer pokemon_Def = (int) tmp;

            /* Get special def */
            tmp = (long) pokemonOBJ.get("Def");
            Integer pokemon_special_Def = (int) tmp;

            /* Get ability1 */
            tmpx = pokemonOBJ.get("Ability 1");
            Ability pokemon_ability_1 = null;
            if(tmpx != null) {
                /* Instantiate class */
                pokemon_ability_1 = new Ability();

                /* Dmg */
                tmp = (long) ((JSONObject) tmpx).get("Dmg");
                pokemon_ability_1.setDmg((int) tmp);

                /* Cd */
                tmp = (long) ((JSONObject) tmpx).get("Cd");
                pokemon_ability_1.setCd((int) tmp);

                /* Stun */
                pokemon_ability_1.setStun ((String) ((JSONObject)tmpx).get("Stun"));

                /* Dodge */
                pokemon_ability_1.setDodge((String) ((JSONObject)tmpx).get("Dodge"));
            }

            /* Get ability2 */
            tmpx = pokemonOBJ.get("Ability 2");
            Ability pokemon_ability_2 = null;
            if(tmpx != null) {
                /* Instantiate class */
                pokemon_ability_2 = new Ability();

                /* Dmg */
                tmp = (long) ((JSONObject) tmpx).get("Dmg");
                pokemon_ability_2.setDmg((int) tmp);

                /* Cd */
                tmp = (long) ((JSONObject) tmpx).get("Cd");
                pokemon_ability_2.setCd((int) tmp);

                /* Stun */
                pokemon_ability_2.setStun ((String) ((JSONObject)tmpx).get("Stun"));

                /* Dodge */
                pokemon_ability_2.setDodge((String) ((JSONObject)tmpx).get("Dodge"));
            }

            /* Get item list */
            JSONArray itemList = (JSONArray) pokemonOBJ.get("Items");
            List<Item> my_items = new ArrayList<>();
            readItemData(itemList, my_items);

            /* Create pokemon */
            Pokemon my_pokemon = new Pokemon.CreatePokemon(pokemon_name,
                    pokemon_HP, pokemon_normal_attack, pokemon_special_attack,
                    pokemon_Def, pokemon_special_Def, pokemon_ability_1,
                    pokemon_ability_2, my_items).create();

            /* Add the pokemon to the database */
            database.add(my_pokemon);
        }
    }

    /**
     * Read the items from the json file to the item database of the pokemon
     * @param array_of_objects - array of objects from json
     */
    private static void readItemData(JSONArray array_of_objects, List<Item> database) throws IOException, ParseException {
        /* Traverse list */
        for(Object item : array_of_objects) {
            /* Get name */
            JSONObject itemOBJ = (JSONObject) item;
            String item_name = (String) itemOBJ.get("name");

            /* Get HP */
            long tmp = (long) itemOBJ.get("hitPoints");
            Integer item_HP = (int) tmp;

            /* Get attack */
            Attack itemAttack = null;
            Object tmpx = itemOBJ.get("attack");
            if(tmpx == null) {
                itemAttack = null;
            } else {
                itemAttack = new Attack();

                Object tmpy = (Object) ((JSONObject) tmpx).get("normalAttack");
                if(tmpy == null) {
                    itemAttack.setNormalAttack(null);
                } else {
                    tmp = (long) ((JSONObject) tmpx).get("normalAttack");
                    itemAttack.setNormalAttack((int) tmp);
                }

                tmpy = (Object) ((JSONObject) tmpx).get("specialAttack");
                if(tmpy == null) {
                    itemAttack.setSpecialAttack(null);
                } else {
                    tmp = (long) ((JSONObject) tmpx).get("specialAttack");
                    itemAttack.setSpecialAttack((int) tmp);
                }
            }

            /* Get defense */
            Defense itemDefense = null;
            tmpx = itemOBJ.get("defense");
            if(tmpx == null) {
                itemDefense = null;
            } else {
                itemDefense = new Defense();

                Object tmpy = (Object) ((JSONObject) tmpx).get("normalDefense");
                if(tmpy == null) {
                    itemDefense.setNormalDefense(null);
                } else {
                    tmp = (long) ((JSONObject) tmpx).get("normalDefense");
                    itemDefense.setNormalDefense((int) tmp);
                }

                tmpy = (Object) ((JSONObject) tmpx).get("specialDefense");
                if(tmpy == null) {
                    itemDefense.setSpecialDefense(null);
                } else {
                    tmp = (long) ((JSONObject) tmpx).get("specialDefense");
                    itemDefense.setSpecialDefense((int) tmp);
                }
            }

            /* Create item */
            var my_item = new Item(item_name, item_HP, itemDefense, itemAttack);

            /* Add it to the database */
            database.add(my_item);
        }
    }
}
