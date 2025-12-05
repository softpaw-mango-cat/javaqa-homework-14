package ru.netology.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.AlreadyRegisteredException;
import ru.netology.exceptions.NotRegisteredException;

public class GameTest {

    Player player1;
    Player player2;
    Player player3;

    @BeforeEach
    public void setUpGame() {
        player1 = new Player("Teferi", 20);
        player2 = new Player("Jace", 17);
        player3 = new Player("Chandra", 21);
    }

    @Test
    public void shouldRegisterNewPlayer() {
        Game game = new Game();
        game.register(player1);

        Assertions.assertTrue(game.getPlayers().containsValue(player1));
    }

    @Test
    public void shouldNotRegisterExistingPlayer() {
        Game game = new Game();
        game.register(player1);

        Assertions.assertThrows(AlreadyRegisteredException.class, () ->
                game.register(player1), "Такой игрок уже зарегистрирован");
    }

    @Test
    public void shouldFindExistingPlayerByName() {
        Game game = new Game();
        game.register(player1); // Teferi
        game.register(player2); // Jace
        game.register(player3); // Chandra

        Player result = game.findByName("Jace");

        Assertions.assertTrue(game.getPlayers().containsValue(result));
    }

    @Test
    public void shouldNotFindNonExistingPlayerByName() {
        Game game = new Game();
        game.register(player1); // Teferi
        game.register(player2); // Jace
        game.register(player3); // Chandra

        Assertions.assertThrows(NotRegisteredException.class, () ->
                game.findByName("Vraska"), "Этот игрок не зарегистрирован в системе");
    }

    @Test
    public void testGameRoundWhenOnePlayerNotFound() {
        Game game = new Game();
        game.register(player1); // Teferi
        game.register(player2); // Jace
        game.register(player3); // Chandra

        int expectedResult = -1;
        int actualResult = game.round("Jace", "Vraska");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenBothPlayersNotFound() {
        Game game = new Game();
        game.register(player1); // Teferi
        game.register(player2); // Jace
        game.register(player3); // Chandra

        int expectedResult = -1;
        int actualResult = game.round("Liliana", "Vraska");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenDraw() {
        Game game = new Game();
        game.register(player1); // Teferi, str 20
        game.register(player2); // Jace, str 17
        game.register(player3); // Chandra, str 21

        player2.setStrength(20);

        int expectedResult = 0;
        int actualResult = game.round("Teferi", "Jace");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenFirstPlayerWins() {
        Game game = new Game();
        game.register(player1); // Teferi, str 20
        game.register(player2); // Jace, str 17
        game.register(player3); // Chandra, str 21

        int expectedResult = 1;
        int actualResult = game.round("Chandra", "Jace");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenSecondPlayerWins() {
        Game game = new Game();
        game.register(player1); // Teferi, str 20
        game.register(player2); // Jace, str 17
        game.register(player3); // Chandra, str 21

        int expectedResult = 2;
        int actualResult = game.round("Jace", "Chandra");

        Assertions.assertEquals(expectedResult, actualResult);
    }
}


