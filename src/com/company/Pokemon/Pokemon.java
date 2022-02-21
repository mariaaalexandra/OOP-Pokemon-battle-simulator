package com.company.Pokemon;

import com.company.Item.Item;
import com.company.Item.ItemDatabase;

import java.util.List;

public class Pokemon {
    /* Fields */
    private String name = "";
    private Integer hitPoints = 0;
    private Integer normalAttack = 0;
    private Integer specialAttack = 0;
    private Integer def = 0;
    private Integer specialDef = 0;
    private Ability Ability1 = null;
    private Ability Ability2 = null;
    private List<Item> my_items = null;

    /* Getters & setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getNormalAttack() {
        return normalAttack;
    }

    public void setNormalAttack(Integer normalAttack) {
        this.normalAttack = normalAttack;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getSpecialDef() {
        return specialDef;
    }

    public void setSpecialDef(Integer specialDef) {
        this.specialDef = specialDef;
    }

    public Ability getAbility1() {
        return Ability1;
    }

    public void setAbility1(Ability ability1) {
        Ability1 = ability1;
    }

    public Ability getAbility2() {
        return Ability2;
    }

    public void setAbility2(Ability ability2) {
        Ability2 = ability2;
    }

    public List<Item> getMy_items() {
        return my_items;
    }

    public void setMy_items(List<Item> my_items) {
        this.my_items = my_items;
    }

    /* Builder design pattern */
    public static class CreatePokemon {
        private String name = "";
        private Integer hitPoints = 0;
        private Integer normalAttack = 0;
        private Integer specialAttack = 0;
        private Integer def = 0;
        private Integer specialDef = 0;
        private Ability Ability1 = null;
        private Ability Ability2 = null;
        private List<Item> my_items = null;

        /* Builder constructor */
        public CreatePokemon(String name, Integer hitPoints, Integer normalAttack,
                             Integer specialAttack, Integer def, Integer specialDef,
                             Ability ability1, Ability ability2, List<Item> my_items) {
            this.name = name;
            this.hitPoints = hitPoints;
            this.normalAttack = normalAttack;
            this.specialAttack = specialAttack;
            this.def = def;
            this.specialDef = specialDef;
            this.Ability1 = ability1;
            this.Ability2 = ability2;
            this.my_items = my_items;
        }

        /* Setters for builder */
        public void setName(String name) {
            this.name = name;
        }

        public void setHitPoIntegers(Integer hitPoints) {
            this.hitPoints = hitPoints;
        }

        public void setNormalAttack(Integer normalAttack) {
            this.normalAttack = normalAttack;
        }

        public void setSpecialAttack(Integer specialAttack) {
            this.specialAttack = specialAttack;
        }

        public void setDef(Integer def) {
            this.def = def;
        }

        public void setSpecialDef(Integer specialDef) {
            this.specialDef = specialDef;
        }

        public void setAbility1(Ability ability1) {
            Ability1 = ability1;
        }

        public void setAbility2(Ability ability2) {
            Ability2 = ability2;
        }

        public void setMy_items(List<Item> my_items) {
            this.my_items = my_items;
        }

        public Pokemon create() {
            return new Pokemon(this);
        }
    }

    /* Constructor */
    private Pokemon(CreatePokemon createPokemon) {
        this.name = createPokemon.name;
        this.hitPoints = createPokemon.hitPoints;
        this.normalAttack = createPokemon.normalAttack;
        this.specialAttack = createPokemon.specialAttack;
        this.def = createPokemon.def;
        this.specialDef = createPokemon.specialDef;
        this.Ability1 = createPokemon.Ability1;
        this.Ability2 = createPokemon.Ability2;
        this.my_items = createPokemon.my_items;
    }

    /* Methods */
    @Override
    public String toString() {
        return "Pokemon name: " + this.name + " | HP: " + this.hitPoints +
                " | Normal Attack: " + this.normalAttack + " | Special attack: " +
                this.specialAttack + " | Def: " + this.def + " | Special Def: " +
                this.specialDef + " | Ability_1: " + this.Ability1 + " | Ability_2: " + this.Ability2;
    }

    /* Upgrade pokemon with his items */
    public void upgradePokemon() {

        for(var item : this.getMy_items()) {
            var itemAttack = item.getAttack();
            var itemDefense = item.getDefense();
            var itemHP = item.getHitPoints();

            /* Set everything to 0 if null */
            if (itemHP == null)
                itemHP = 0;

            if (itemAttack == null) {
                itemAttack = new Attack();
            }
            if(itemDefense == null) {
                itemDefense = new Defense();
            }
            if (itemAttack.getNormalAttack() == null)
                itemAttack.setNormalAttack(0);
            if (itemAttack.getSpecialAttack() == null)
                itemAttack.setSpecialAttack(0);
            if (itemDefense.getNormalDefense() == null)
                itemDefense.setNormalDefense(0);
            if (itemDefense.getSpecialDefense() == null)
                itemDefense.setSpecialDefense(0);

            /* Set this pokemon */
            this.setHitPoints(this.getHitPoints() + itemHP);
            if(this.getSpecialAttack() == null)
                this.setSpecialAttack(0);
            if(this.getSpecialDef() == null)
                this.setSpecialDef(0);
            if(this.getNormalAttack() == null)
                this.setNormalAttack(0);
            if(this.getDef() == null)
                this.setDef(0);
            this.setNormalAttack(this.getNormalAttack() + itemAttack.getNormalAttack());
            this.setSpecialAttack(this.getSpecialAttack() + itemAttack.getSpecialAttack());
            this.setDef(this.getDef() + itemDefense.getNormalDefense());
            this.setSpecialDef(this.getSpecialDef() + itemDefense.getSpecialDefense());
        }
    }

    /* Calculate pokemon score used for deciding best pokemon */
    public Integer calculatePokemonScore() {
        return this.hitPoints + this.specialAttack + this.normalAttack + this.def + this.specialDef;
    }

    /* Increase all stats by 1 (for a winner pokemon */
    public void increaseAll() {
        this.setHitPoints(this.getHitPoints() + 1);
        this.setDef(this.getDef() + 1);
        this.setSpecialDef(this.getSpecialDef() + 1);
        this.setSpecialAttack(this.getSpecialAttack() + 1);
        this.setNormalAttack(this.getNormalAttack() + 1);
    }
}
