package com.company.Pokemon;

public class Ability {
    /* Fields */
    private Integer Dmg = 0;
    private Integer Cd = 0;
    private String Dodge = "";
    private String Stun = "";

    /* Constructors */

    public Ability() {}

    public Ability(Integer dmg, Integer cd, String dodge, String stun) {
        Dmg = dmg;
        Cd = cd;
        Dodge = dodge;
        Stun = stun;
    }

    /* Getters & setters */
    public Integer getDmg() {
        return Dmg;
    }

    public void setDmg(Integer dmg) {
        Dmg = dmg;
    }

    public Integer getCd() {
        return Cd;
    }

    public void setCd(Integer cd) {
        Cd = cd;
    }

    public String getDodge() {
        return Dodge;
    }

    public void setDodge(String dodge) {
        Dodge = dodge;
    }

    public String getStun() {
        return Stun;
    }

    public void setStun(String stun) {
        Stun = stun;
    }

    /* Methods */
    @Override
    public String toString() {
        return "Ability with Dmg: " + this.getDmg() + " | Cd: " +
                this.getCd() + " | Dodge: " + this.getDodge() + " | Stun: " + this.getStun();
    }
}
