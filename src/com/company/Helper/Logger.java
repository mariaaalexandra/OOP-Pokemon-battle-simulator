package com.company.Helper;

import com.company.Adventure.Adventure_3_observer;
import com.company.History.CareTaker;
import com.company.History.FullDatabase;
import com.company.History.MomentoState;
import com.company.Pokemon.Ability;
import com.company.Pokemon.Pokemon;
import com.company.Pokemon.PokemonDatabase;
import com.company.Trainer.Trainer;
import com.company.Trainer.TrainerDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Logger {
    /* Fields */
    private static int battleNumber = 0;

    /* Print the pokemon database */
    public static void printPokemonDatabase() {
        System.out.println("---------------- Pokemons -------------------");
        for(var pokemon : PokemonDatabase.pokemonList) {
            System.out.println(pokemon);
            for(var item : pokemon.getMy_items()) {
                System.out.println(item);
            }
            System.out.println("");
        }
    }

    /* Print the item database */
    public static void printItemDatabase(Pokemon my_pokemon) {
        System.out.println("---------------- Items of pokemon: " + my_pokemon.getName() + "-------------------");
        for(var item : my_pokemon.getMy_items()) {
            System.out.println(item);
        }
    }

    /* Print the item database */
    public static void printItemDatabase() {
        System.out.println("---------------- Items -------------------");
        var my_pokemons = PokemonDatabase.pokemonList;
        for(var pokemon : my_pokemons) {
            for(var item : pokemon.getMy_items()) {
                System.out.println(item);
            }
        }
    }

    /* Print the trainer database */
    public static void printTrainerDatabase() {
        System.out.println("---------------- Trainers -------------------");
        var database = TrainerDatabase.getInstance();
        for(var trainer : database.getTrainerList()) {
            System.out.println(trainer);
        }
    }

    /* Print all three databases */
    public static void printAllDatabases() {
        System.out.println("");

        /* Get trainer 1 and his pokemon list */
        Trainer trainer1 = TrainerDatabase.getInstance().getTrainerList().get(0);
        List<Pokemon> pokemonList = new ArrayList<>();
        for(int i = 0 ; i < 3; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer1.setMy_pokemons(pokemonList);

        /* Get trainer 2 and his pokemon list */
        Trainer trainer2 = TrainerDatabase.getInstance().getTrainerList().get(1);
        pokemonList = new ArrayList<>();
        for(int i = 3 ; i < 6; ++i) {
            pokemonList.add(PokemonDatabase.pokemonList.get(i));
        }
        trainer2.setMy_pokemons(pokemonList);

        /* Update trainers pokemon`s to the newest stats (with items) */
        for(var pokemon: trainer1.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        for(var pokemon : trainer2.getMy_pokemons()) {
            pokemon.upgradePokemon();
        }

        System.out.println("Battle starts with the two trainers:");
        Logger.printTrainerDatabase();

        System.out.println("The pokemons in order are:");
        Logger.printPokemonDatabase();
    }

    /* Battle two pokemons */
    public static void pokemonBattle(Pokemon my_pokemon, Pokemon enemy, FullDatabase my_state, CareTaker my_history_log) {
        System.out.println("\n======== START BATTLE nr. " + ++Logger.battleNumber + " between " + my_pokemon.getName() +
                " and " + enemy.getName() + " =========\n");
        /* Display starting pokemons */
        System.out.println(my_pokemon);
        System.out.println("VS");
        System.out.println(enemy);

        /* Get pokemons` abilities */
        Ability p1_first_ability = my_pokemon.getAbility1();
        Integer p1_first_ability_cooldown  = -1;
        if(p1_first_ability != null) {
           p1_first_ability_cooldown = p1_first_ability.getCd();
        }
        Ability p1_second_ability = my_pokemon.getAbility2();
        Integer p1_second_ability_cooldown  = -1;
        if(p1_second_ability != null) {
           p1_second_ability_cooldown = p1_second_ability.getCd();
        }
        Ability p2_first_ability = enemy.getAbility1();
        Integer p2_first_ability_cooldown  = -1;
        if(p2_first_ability != null) {
            p2_first_ability_cooldown = p2_first_ability.getCd();
        }
        Ability p2_second_ability = enemy.getAbility2();
        Integer p2_second_ability_cooldown  = -1;
        if(p2_second_ability != null) {
            p2_second_ability_cooldown = p2_second_ability.getCd();
        }

        Boolean pokemon_stunned = false;
        Boolean enemy_stunned = false;

        /* Fight until one pokemon is dead */
        Integer turn_counter = 0;
        while(my_pokemon.getHitPoints() > 0 && enemy.getHitPoints() > 0) {
            /* Display turn */
            System.out.println("\n==== TURN " + ++turn_counter + " ====\n");

            /* Select move for pokemon 1 */
            var pokemon_attack = new Random().nextInt(4);
            Integer pokemon_attack_dmg = null;
            Ability pokemon_ability = null;
            boolean pokemon_1_inactive = false;
            boolean is_attack = true;
            boolean is_normal_attack = true;
            boolean pokemon_dodge = false;

            if (pokemon_attack == 0) { // Normal attack
                pokemon_attack_dmg = my_pokemon.getNormalAttack(); // Can be a value or can be null (do nothing for null)
            } else if (pokemon_attack == 1) { // Special attack
                pokemon_attack_dmg = my_pokemon.getSpecialAttack();
                is_normal_attack = false;
            } else if (pokemon_attack == 2) { // Ability 1
                pokemon_ability = my_pokemon.getAbility1();
                is_attack = false;
                is_normal_attack = false;
            } else if (pokemon_attack == 3) { // Ability 2
                pokemon_ability = my_pokemon.getAbility2();
                is_attack = false;
                is_normal_attack = false;
            }

            if ((pokemon_attack_dmg == null || pokemon_attack_dmg == 0) &&
                    pokemon_ability == null) {
                pokemon_1_inactive = true;
            }

            /* Select move for pokemon 2 */
            var pokemon_attack_2 = new Random().nextInt(4);
            Integer pokemon_attack_dmg_2 = null;
            Ability pokemon_ability_2 = null;
            boolean pokemon_2_inactive = false;
            boolean is_attack_2 = true;
            boolean is_normal_attack_2 = false;
            boolean enemy_dodge = false;

            if (pokemon_attack_2 == 0) { // Normal attack
                pokemon_attack_dmg_2 = enemy.getNormalAttack(); // Can be a value or can be null (do nothing for null)
            } else if (pokemon_attack_2 == 1) { // Special attack
                pokemon_attack_dmg_2 = enemy.getSpecialAttack();
                is_normal_attack_2 = false;
            } else if (pokemon_attack_2 == 2) { // Ability 1
                pokemon_ability_2 = enemy.getAbility1();
                is_attack_2 = false;
                is_normal_attack_2 = false;
            } else if (pokemon_attack_2 == 3) { // Ability 2
                pokemon_ability_2 = enemy.getAbility2();
                is_attack_2 = false;
                is_normal_attack_2 = false;
            }

            if ((pokemon_attack_dmg_2 == null || pokemon_attack_dmg_2 == 0) &&
                    pokemon_ability_2 == null) {
                pokemon_2_inactive = true;
            }

            String pokemon_1_spell = null;
            if (pokemon_1_inactive) {
                System.out.println("Pokemon: " + my_pokemon.getName() + " did nothing!");
            } else if (pokemon_stunned) {
                System.out.println("Pokemon: " + my_pokemon.getName() + " was stunned and did nothing!");
                pokemon_stunned = false;
            } else if (is_attack && is_normal_attack) {
                System.out.println("Pokemon: " + my_pokemon.getName() + " used normal attack!");
                if(pokemon_stunned == true) {
                    pokemon_stunned = false;
                    System.out.println("Pokemon " + my_pokemon.getName() + " was stunned!");
                } else if(enemy_dodge == true) {
                    enemy_dodge = false;
                    System.out.println("Pokemon " + enemy.getName() + " dodged!");
                } else {
                    enemy.setHitPoints(enemy.getHitPoints() - pokemon_attack_dmg);
                }
            } else if (is_attack && !is_normal_attack) {
                System.out.println("Pokemon: " + my_pokemon.getName() + " used special attack!");
                if(pokemon_stunned == true) {
                    pokemon_stunned = false;
                    System.out.println("Pokemon " + my_pokemon.getName() + " was stunned!");
                } else if(enemy_dodge == true) {
                    enemy_dodge = false;
                    System.out.println("Pokemon " + enemy.getName() + " dodged!");
                } else {
                    enemy.setHitPoints(enemy.getHitPoints() - pokemon_attack_dmg);
                }
            } else if (!is_attack && is_normal_attack) {
                System.out.println("Pokemon: " + my_pokemon.getName() + " used ability 1!");
                pokemon_1_spell = "Ab1";
            } else if (!is_attack && !is_normal_attack) {
                System.out.println("Pokemon: " + my_pokemon.getName() + " used ability 2!");
                pokemon_1_spell = "Ab2";
            }

            String pokemon_2_spell = null;
            if (pokemon_2_inactive) {
                System.out.println("Pokemon: " + enemy.getName() + " did nothing!");
            } else if (enemy_stunned) {
                System.out.println("Pokemon: " + enemy.getName() + " was stunned and did nothing!");
                enemy_stunned = false;
            } else if (is_attack_2 && is_normal_attack_2) {
                System.out.println("Pokemon: " + enemy.getName() + " used normal attack!");
                if(enemy_stunned == true) {
                    enemy_stunned = false;
                    System.out.println("Pokemon " + enemy.getName() + " was stunned!");
                } else if(pokemon_dodge == true) {
                    pokemon_dodge = false;
                    System.out.println("Pokemon " + my_pokemon.getName() + " dodged!");
                } else {
                    my_pokemon.setHitPoints(my_pokemon.getHitPoints() - pokemon_attack_dmg_2);
                }
            } else if (is_attack_2 && !is_normal_attack_2) {
                System.out.println("Pokemon: " + enemy.getName() + " used special attack!");
                if(enemy_stunned == true) {
                    enemy_stunned = false;
                    System.out.println("Pokemon " + enemy.getName() + " was stunned!");
                } else if(pokemon_dodge == true) {
                    pokemon_dodge = false;
                    System.out.println("Pokemon " + my_pokemon.getName() + " dodged!");
                } else {
                    my_pokemon.setHitPoints(my_pokemon.getHitPoints() - pokemon_attack_dmg_2);
                }
            } else if (!is_attack_2 && is_normal_attack_2) {
                System.out.println("Pokemon: " + enemy.getName() + " used ability 1!");
                pokemon_2_spell = "Ab11";
            } else if (!is_attack_2 && !is_normal_attack_2) {
                System.out.println("Pokemon: " + enemy.getName() + " used ability 2!");
                pokemon_2_spell = "Ab22";
            }

            /* If only one spell was casted */
            if (pokemon_1_spell != null && pokemon_2_spell == null) {
                if (pokemon_ability.getCd() != 0 && pokemon_stunned == false && enemy_dodge == false) {
                    pokemon_ability.setCd(0);
                    if (pokemon_ability.getStun().equals("yes")) {
                        enemy_stunned = true;
                    }
                    if (pokemon_ability.getDodge().equals("yes")) {
                        pokemon_dodge = true;
                    }
                    enemy.setHitPoints(enemy.getHitPoints() - pokemon_ability.getDmg());

                } else if (enemy_dodge == true) {
                    System.out.println("Pokemon " + enemy.getName() + " dodget hit!");
                    enemy_dodge = false;
                } else if((pokemon_ability.getCd() != p1_first_ability_cooldown && pokemon_1_spell.equals("Ab1")) ||
                            (pokemon_ability.getCd() != p1_second_ability_cooldown && pokemon_1_spell.equals("Ab2"))) {
                        System.out.println("Ability from " + my_pokemon.getName() + " is still on cooldown!");
                } else {
                    System.out.println("Spell from " + my_pokemon.getName() + " could not be casted because it was on cooldown!");
                }
            } else if (pokemon_1_spell == null && pokemon_2_spell != null) {
                if (pokemon_ability_2.getCd() != 0 && enemy_stunned == false && pokemon_dodge == false) {
                    pokemon_ability_2.setCd(0);
                    if (pokemon_ability_2.getStun().equals("yes")) {
                        pokemon_stunned = true;
                    }
                    if (pokemon_ability_2.getDodge().equals("yes")) {
                        enemy_dodge = true;
                    }
                    my_pokemon.setHitPoints(my_pokemon.getHitPoints() - pokemon_ability_2.getDmg());
                } else if (pokemon_dodge == true) {
                    System.out.println("Pokemon " + my_pokemon.getName() + " dodged hit!");
                    pokemon_dodge = false;
                } else if((pokemon_ability_2.getCd() != p2_first_ability_cooldown && pokemon_2_spell.equals("Ab11")) ||
                        (pokemon_ability_2.getCd() != p2_second_ability_cooldown && pokemon_2_spell.equals("Ab22"))) {
                    System.out.println("Ability from " + enemy.getName() + " is still on cooldown!");
                } else {
                    System.out.println("Spell from " + enemy.getName() + " could not be casted because it was on cooldown!");
                }
            } else if(pokemon_1_spell != null && pokemon_2_spell != null) {
                if (pokemon_ability.getCd() == 0 && pokemon_stunned == false && enemy_dodge == false) {
                    pokemon_ability.setCd(0);
                    if (pokemon_ability.getStun().equals("yes")) {
                        enemy_stunned = true;
                    }
                    if (pokemon_ability.getDodge().equals("yes")) {
                        pokemon_dodge = true;
                    }
                    enemy.setHitPoints(enemy.getHitPoints() - pokemon_ability.getDmg());

                } else if (enemy_dodge == true) {
                    System.out.println("Pokemon " + enemy.getName() + " dodget hit!");
                    enemy_dodge = false;
                } else if((pokemon_ability.getCd() != p1_first_ability_cooldown && pokemon_1_spell.equals("Ab1")) ||
                        (pokemon_ability.getCd() != p1_second_ability_cooldown && pokemon_1_spell.equals("Ab2"))) {
                    System.out.println("Ability from " + my_pokemon.getName() + " is still on cooldown!");
                } else {
                    System.out.println("Spell from " + my_pokemon.getName() + " could not be casted because it was on cooldown!");
                }
                if (pokemon_ability_2.getCd() == 0 && enemy_stunned == false && pokemon_dodge == false) {
                    pokemon_ability_2.setCd(0);
                    if (pokemon_ability_2.getStun().equals("yes")) {
                        pokemon_stunned = true;
                    }
                    if (pokemon_ability_2.getDodge().equals("yes")) {
                        enemy_dodge = true;
                    }
                    my_pokemon.setHitPoints(my_pokemon.getHitPoints() - pokemon_ability_2.getDmg());
                } else if (pokemon_dodge == true) {
                    System.out.println("Pokemon " + my_pokemon.getName() + " dodged hit!");
                    pokemon_dodge = false;
                } else if((pokemon_ability_2.getCd() != p2_first_ability_cooldown && pokemon_2_spell.equals("Ab11")) ||
                        (pokemon_ability_2.getCd() != p2_second_ability_cooldown && pokemon_2_spell.equals("Ab22"))) {
                    System.out.println("Ability from " + enemy.getName() + " is still on cooldown!");
                } else {
                    System.out.println("Spell from " + enemy.getName() + " could not be casted because it was on cooldown!");
                }
            }

            if(pokemon_ability != null) {
                pokemon_ability.setCd(pokemon_ability.getCd() + 1);
            }
            if(pokemon_ability_2 != null) {
                pokemon_ability_2.setCd(pokemon_ability_2.getCd() + 1);
            }

            System.out.println("\nPokemon: " + my_pokemon.getName() + " remaining HP: " + my_pokemon.getHitPoints());
            System.out.println("Pokemon: " + enemy.getName() + " remaining HP: " + enemy.getHitPoints());

            /* If it`s the case, display winner */
            if(enemy.getHitPoints() <= 0) {
                System.out.println("\n" + my_pokemon.getName() + " is the winner!\n");
                Helper.logCurrentStateToHistory(my_state, my_history_log);
                if(enemy.getName().equals("Neutrel1") == false && enemy.getName().equals("Neutrel2") == false) {
                    Adventure_3_observer.trainer1_points++;
                }
                break;
            } else if(my_pokemon.getHitPoints() <= 0) {
                System.out.println("\n" + enemy.getName() + " is the winner!\n");
                Helper.logCurrentStateToHistory(my_state, my_history_log);
                if(my_pokemon.getName().equals("Neutrel1") == false && my_pokemon.getName().equals("Neutrel2") == false) {
                    Adventure_3_observer.trainer2_points++;
                }
                break;
            } else if(enemy.getHitPoints() <= 0 && my_pokemon.getHitPoints() <= 0) {
                System.out.println("\nDRAW\n");
                Helper.logCurrentStateToHistory(my_state, my_history_log);
            }

            /* After each turn, log history */
            my_state.setState(new MomentoState(TrainerDatabase.getInstance().getTrainerList(), PokemonDatabase.pokemonList, Helper.getAllItems()));
            Helper.logCurrentStateToHistory(my_state, my_history_log);
        }

        System.out.println("\n========= END BATTLE ========\n");
    }
}
