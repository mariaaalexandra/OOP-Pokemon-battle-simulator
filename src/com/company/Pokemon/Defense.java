package com.company.Pokemon;

public class Defense {
    /* Fields */
    private Integer normalDefense = 0;
    private Integer specialDefense = 0;

    /* Constructors */
    public Defense(){}

    public Defense(Integer normalDefense, Integer specialDefense) {
        this.normalDefense = normalDefense;
        this.specialDefense = specialDefense;
    }

    /* Getters & setters */
    public Integer getNormalDefense() {
        return normalDefense;
    }

    public void setNormalDefense(Integer normalDefense) {
        this.normalDefense = normalDefense;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    /* Methods */

    @Override
    public String toString() {
        return "Defense - normalDefense: " + this.normalDefense + " | specialDefense: " + this.specialDefense;
    }
}
