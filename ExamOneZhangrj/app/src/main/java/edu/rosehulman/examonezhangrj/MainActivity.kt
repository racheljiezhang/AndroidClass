package edu.rosehulman.examonezhangrj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.rosehulman.examonezhangrj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var coor = Coordinate()
    private var board = Board();
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
        updateView()
    }

    private fun setupButtons(){
        binding.downButton.setOnClickListener{
            if(coor.y < 8){
                board.checker(coor.y, coor.x);
                coor.yup()
                updateView()

            }
        }
        binding.upButton.setOnClickListener{
            if(coor.y > 0){
                board.checker(coor.y, coor.x);
                coor.ydown()
                updateView()

            }
        }
        binding.leftButton.setOnClickListener{
            if(coor.x > 0){
                board.checker(coor.y, coor.x);
                coor.xdown()
                updateView()

            }
        }
        binding.rightButton.setOnClickListener{
            if(coor.x < 8){
                board.checker(coor.y, coor.x);
                coor.xup()
                updateView()

            }
        }
        binding.resetButton.setOnClickListener{
            coor.x = 4;
            coor.y = 4;
            board.remove()
            updateView()
        }
        binding.editButton.setOnClickListener{
            board.reversePen()
            updateView()
        }
    }


    private fun updateView(){
        binding.coordinates.text = "(" + coor.x + ", " + coor.y + ")"
        binding.matrix.text = board.toString(this.coor)
    }
}