package ru.netology.game;

import java.util.Objects;

public class Player {
    private int id;
    private int strength;

    public Player(int id, int strength) {
        this.id = id;
        this.strength = strength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == player.id && strength == player.strength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, strength);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", strength=" + strength +
                '}';
    }
}

