package ru.netology.game;

import java.util.Objects;

public class Player {
    private String name;
    private int strength;

    public Player(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return strength == player.strength && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, strength);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                '}';
    }
}
