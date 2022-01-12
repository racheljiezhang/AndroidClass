package edu.rosehulman.tictactoe

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TicTacToeTest {
    @Test
    fun reset() {
        val game = TicTacToeGame()
        for ( row in 0 until TicTacToeGame.NUM_ROWS ) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS ) {
                assertEquals("", game.stringForButtonAt(row, col))
            }
        }
        assertEquals(TicTacToeGame.GameState.X_TURN , game.state);
    }

    @Test
    fun press() {
        val game = TicTacToeGame()
        game.pressButtonAt(1, 2)
        assertEquals("X", game.stringForButtonAt(1, 2))
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(2, 2)
        assertEquals("O", game.stringForButtonAt(2, 2))
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(2, 2)
        assertEquals("O", game.stringForButtonAt(2, 2))
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
    }

    @Test
    fun pressOutOfBoundsIsIgnored() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(3, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(-1, 1)
        game.pressButtonAt(1, 3)
        game.pressButtonAt(1, -1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
    }

    @Test
    fun detectWinEasy() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(0, 0) // X
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(2, 0) // O
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(0, 1) // X
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(2, 2) // O
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(0, 2) // X
        assertEquals(TicTacToeGame.GameState.X_WINS, game.state)
    }

    @Test
    fun detectWinForce() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(1, 1)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(0, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(0, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(2, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(2, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(0, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(1, 0)
        assertEquals(TicTacToeGame.GameState.X_WINS, game.state)
    }

    @Test
    fun detectTie() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(1, 1)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(0, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(0, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(2, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(2, 0)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(0, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(1, 2)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(1, 0)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(2, 1)
        assertEquals(TicTacToeGame.GameState.TIE_GAME, game.state)
    }

    @Test
    fun detectXWinsBottomLeftToUpperRightDiagonal() {
        val game = TicTacToeGame()
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(0, 2)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(0, 1)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(1, 1)
        assertEquals(TicTacToeGame.GameState.O_TURN, game.state)
        game.pressButtonAt(2, 2)
        assertEquals(TicTacToeGame.GameState.X_TURN, game.state)
        game.pressButtonAt(2, 0)
        assertEquals(TicTacToeGame.GameState.X_WINS, game.state)
    }


}