package ru.netology.game;

import ru.netology.exceptions.AlreadyRegisteredException;
import ru.netology.exceptions.NotRegisteredException;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private Map<Integer, Player> players = new HashMap<>();
    private int id = 0;

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public int getId() {
        return id;
    }

    public void register(Player newPlayer) {

        if (players.containsValue(newPlayer)) {
            throw new AlreadyRegisteredException("Такой игрок уже зарегистрирован");
        }

        players.put(id++, newPlayer);
        System.out.println("Новый игрок зарегистрирован: " + newPlayer.toString());
    }

    public Player findByName(String name) {

        for (Player player : players.values()) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        throw new NotRegisteredException("Этот игрок не зарегистрирован в системе");
    }

    public int round(String playerName1, String playerName2) {
        Player player1 = null;
        Player player2 = null;

        try {
            player1 = findByName(playerName1);
            player2 = findByName(playerName2);
        } catch (NotRegisteredException e) {
            return -1;
        }

        if (player1.getStrength() == player2.getStrength()) {
            System.out.println("Ничья");
            return 0;
        } else if (player1.getStrength() > player2.getStrength()) {
            System.out.println("Выиграл первый игрок");
            return 1;
        } else {
            System.out.println("Выиграл второй игрок");
            return 2;
        }
    }
}

