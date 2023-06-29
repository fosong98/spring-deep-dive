package com.example.toby2.pojo;

public class Robot {
    String name;
    Weapon weapon;

    public String sayName() {
        return "I am " + name;
    }

    public String attack() {
        return name + " " + weapon.use();
    }

    public void setName(String name) { this.name = name; }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
