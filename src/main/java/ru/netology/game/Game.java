package ru.netology.game;

import ru.netology.exceptions.AlreadyRegisteredException;
import ru.netology.exceptions.NotRegisteredException;

import java.util.HashMap;
import java.util.Map;

public class Game {

    private Map<String, Player> players = new HashMap<>();

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void register(String name, Player newPlayer) {

        if (players.containsValue(newPlayer)) {
            throw new AlreadyRegisteredException("Такой игрок уже зарегистрирован");
        }

        players.put(name, newPlayer);

        System.out.println("Новый игрок зарегистрирован: " + name);
    }

    public Player findByName(String name) {

        if (players.containsKey(name)) {
            return players.get(name);
        } else {
            throw new NotRegisteredException("Этот игрок не зарегистрирован в системе");
        }

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

