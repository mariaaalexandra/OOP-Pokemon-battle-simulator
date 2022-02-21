package com.company.Item;

import com.company.Pokemon.Attack;
import com.company.Pokemon.Defense;

public class Item {
    /* Fields */
    private String name = "";
    private Integer hitPoints = 0;
    private Attack attack = null;
    private Defense defense = null;

    /* Constructors */
    public Item() {}

    public Item(String name, Integer hitPoints, Defense defense, Attack attack) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.defense = defense;
        this.attack = attack;
    }

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

    public Defense getDefense() {
        return defense;
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    /* Methods */
    @Override
    public String toString() {
        return "Item: " + this.name + " | HP: " + this.hitPoints + " | Attack: " + this.attack + " | Defense: " + this.defense;
    }
}
