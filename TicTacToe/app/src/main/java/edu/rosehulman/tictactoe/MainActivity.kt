package edu.rosehulman.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import edu.rosehulman.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var tttButtons: Array<Array<Button>>
    private lateinit var game: TicTacToeGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tttButtons =
            Array(TicTacToeGame.NUM_ROWS) { Array(TicTacToeGame.NUM_COLUMNS) { Button(this) } }
        game = TicTacToeGame(this)
        setupButtons()
        updateView()
    }

    private fun setupButtons() {
        for (row in 0 until TicTacToeGame.NUM_ROWS) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS) {
                // TODO: make an identifier from row and column.
                val id = resources.getIdentifier("button$row$col", "id", packageName)
                tttButtons[row][col] = binding.root.findViewById<Button>(id)
                tttButtons[row][col].setOnClickListener {
                    Log.d("TTT", "Button $row, $col was pressed")
                    game.pressButtonAt(row, col)
                    updateView()
                }
            }
            binding.newGameButton.setOnClickListener {
                game.reset()
                updateView()
                Log.d("TTT", "resetting game")
            }
        }
    }

    private fun updateView() {
        binding.gameStateTextView.text = game.stringForGameState()
        for (row in 0 until TicTacToeGame.NUM_ROWS) {
            for (col in 0 until TicTacToeGame.NUM_COLUMNS) {
                tttButtons[row][col].text = game.stringForButtonAt(row, col)
            }
        }
    }
}