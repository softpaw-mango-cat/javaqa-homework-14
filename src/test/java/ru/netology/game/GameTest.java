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
        player1 = new Player(1, 20);
        player2 = new Player(2, 17);
        player3 = new Player(3, 21);
    }

    @Test
    public void shouldRegisterNewPlayer() {
        Game game = new Game();
        game.register("Teferi", player1);

        Assertions.assertEquals(game.getPlayers().get("Teferi"), player1);
    }

    @Test
    public void shouldNotRegisterExistingPlayer() {
        Game game = new Game();
        game.register("Teferi", player1);

        Assertions.assertThrows(AlreadyRegisteredException.class, () ->
                game.register("Teferi", player1), "Такой игрок уже зарегистрирован");
    }

    @Test
    public void shouldFindExistingPlayerByName() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        Player result = game.findByName("Jace");

        Assertions.assertTrue(game.getPlayers().containsValue(result));
    }

    @Test
    public void shouldNotFindNonExistingPlayerByName() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        Assertions.assertThrows(NotRegisteredException.class, () ->
                game.findByName("Vraska"), "Этот игрок не зарегистрирован в системе");
    }

    @Test
    public void testGameRoundWhenOnePlayerNotFound() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        int expectedResult = -1;
        int actualResult = game.round("Jace", "Vraska");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenBothPlayersNotFound() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        int expectedResult = -1;
        int actualResult = game.round("Liliana", "Vraska");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenDraw() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        player2.setStrength(20);

        int expectedResult = 0;
        int actualResult = game.round("Teferi", "Jace");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenFirstPlayerWins() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        int expectedResult = 1;
        int actualResult = game.round("Chandra", "Jace");

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGameRoundWhenSecondPlayerWins() {
        Game game = new Game();
        game.register("Teferi", player1);
        game.register("Jace", player2);
        game.register("Chandra", player3);

        int expectedResult = 2;
        int actualResult = game.round("Jace", "Chandra");

        Assertions.assertEquals(expectedResult, actualResult);
    }
}


