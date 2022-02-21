package com.company.Pokemon;

public class Attack {
    /* Fields */
    private Integer normalAttack = 0;
    private Integer specialAttack = 0;

    /* Constructors */
    public Attack() {}

    public Attack(Integer normalAttack, Integer specialAttack) {
        this.normalAttack = normalAttack;
        this.specialAttack = specialAttack;
    }

    /* Getters & setters */
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

    /* Methods */
    @Override
    public String toString() {
        return "Attack - normalAttack: " + this.normalAttack + " | specialAttack: " + this.specialAttack;
    }
}
