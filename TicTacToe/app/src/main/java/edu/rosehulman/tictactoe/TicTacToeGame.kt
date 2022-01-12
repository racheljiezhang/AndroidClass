package edu.rosehulman.tictactoe

import android.content.Context

class TicTacToeGame() {
    private var board = Array(NUM_ROWS) { Array(NUM_COLUMNS) { Mark.NONE } }
    var state = GameState.X_TURN

    lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }


    fun reset() {
        board = Array(NUM_ROWS) { Array(NUM_COLUMNS) { Mark.NONE } }
        state = GameState.X_TURN
    }

    fun stringForButtonAt(row: Int, col: Int): String {
        if (row in 0 until NUM_ROWS && col in 0 until NUM_COLUMNS) {
            if (board[row][col] == Mark.X) {
                return "X";
            } else if (board[row][col] == Mark.Oh) {
                return "O"
            }
        }
        return ""
    }

    fun pressButtonAt(row: Int, col: Int) {
        if (row !in 0 until NUM_ROWS || col !in 0 until NUM_COLUMNS) {
            return
        }
        if (board[row][col] != Mark.NONE) {
            return
        }
        if (state == GameState.X_TURN) {
            board[row][col] = Mark.X
            state = GameState.O_TURN
            checkForWin()
        } else if (state == GameState.O_TURN) {
            board[row][col] = Mark.Oh
            state = GameState.X_TURN
            checkForWin()
        }

    }

//    fun stringForGameState(): String {
//        return when (state) {
//            GameState.X_TURN -> "X Turn"
//            GameState.O_TURN -> "O Turn"
//            GameState.X_WINS -> "X Wins"
//            GameState.O_WINS -> "O Wins"
//            GameState.TIE_GAME -> "Tie Game"
//        }
//    }

    // this is the function above but a tiny bit more concise
    fun stringForGameState(): String = when (state) {
        GameState.X_TURN -> context.getString(R.string.x_turn)
        GameState.O_TURN -> context.getString(R.string.o_turn)
        GameState.X_WINS -> context.getString(R.string.x_wins)
        GameState.O_WINS -> context.getString(R.string.o_wins)
        GameState.TIE_GAME -> context.getString(R.string.tie_game)
    }

    fun checkForWin() {
        if (state != GameState.X_TURN && state != GameState.O_TURN) {
            return
        }

        if (didPieceWin(Mark.X)) {
            state = GameState.X_WINS
        } else if (didPieceWin(Mark.Oh)) {
            state = GameState.O_WINS
        } else if (isBoardFull()) {
            state = GameState.TIE_GAME
        }
    }

    private fun isBoardFull(): Boolean {
        for (row in 0 until 3) {
            for (column in 0 until 3) {
                if (board[row][column] == Mark.NONE) {
                    return false
                }
            }
        }
        return true
    }

    private fun didPieceWin(mark: Mark): Boolean {
        return didPieceWinAcross(mark) ||
                didPieceWinDown(mark) ||
                didPieceWinMainDiagonal(mark) ||
                didPieceWinOffDiagonal(mark)

    }

    private fun didPieceWinAcross(mark: Mark): Boolean {
        for (row in 0 until 3) {
            var winHere = true
            for (column in 0 until 3) {
                if (board[row][column] != mark) {
                    winHere = false
                }
            }
            if (winHere) {
                return true
            }
        }
        return false
    }

    private fun didPieceWinDown(mark: Mark): Boolean {
        for (column in 0 until 3) {
            var winHere = true
            for (row in 0 until 3) {
                if (board[row][column] != mark) {
                    winHere = false
                }
            }
            if (winHere) {
                return true
            }
        }
        return false
    }

    private fun didPieceWinMainDiagonal(mark: Mark): Boolean {
        var winHere = true
        for (row in 0 until 3) {
            if (board[row][row] != mark) {
                winHere = false
            }
        }
        return winHere

    }

    private fun didPieceWinOffDiagonal(mark: Mark): Boolean {
        var winHere = true
        for (row in 0 until 3) {
            if (board[row][2 - row] != mark) {
                winHere = false
            }
        }
        return winHere
    }


    enum class Mark {
        NONE,
        X,
        Oh,
    }

    enum class GameState {
        X_TURN,
        O_TURN,
        X_WINS,
        O_WINS,
        TIE_GAME,
    }

    companion object {
        const val NUM_ROWS = 3
        const val NUM_COLUMNS = 3
    }
}