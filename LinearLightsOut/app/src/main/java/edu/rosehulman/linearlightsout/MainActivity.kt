package edu.rosehulman.linearlightsout

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import edu.rosehulman.linearlightsout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var allButtons : Array<Button?>
    private lateinit var model : GameViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        model = ViewModelProvider(this).get(GameViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println(model.game.toString())
        setupButtons()
        updateView()

    }

    private fun setupButtons() {
        allButtons = Array<Button?>(7, { null });
        allButtons[0] = binding.button0
        allButtons[1] = binding.button1
        allButtons[2] = binding.button2
        allButtons[3] = binding.button3
        allButtons[4] = binding.button4
        allButtons[5] = binding.button5
        allButtons[6] = binding.button6

        for (i in allButtons.indices){
            allButtons[i]?.setOnClickListener {
                model.game.pressButton(i)
                println(model.game.toString())
                updateView()
            }
        }

        binding.newGame.setOnClickListener {
            model.game = GameModel()
            for (b in allButtons) {
                b?.isEnabled = true;
            }
            binding.onlyText.setText("Make the buttons match")
            binding.newGame.isEnabled = false;
            binding.sendEmail.isEnabled = false;
            updateView()
        }
        binding.newGame.isEnabled = false;
        binding.sendEmail.isEnabled = false;

        binding.sendEmail.setOnClickListener {
            val subject = "I won!"
            val n = model.game.round
            val message = "I won in $n"

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("imabiggirl928@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(intent, "Send Email using:"));

        }
    }

    private fun updateView(){
        for(i in allButtons.indices){
            allButtons[i]?.text = if (model.game.checkChecked(i)) "1" else "0"
        }
        if(model.game.round > 0){
            if(model.game.checkWin()){
                binding.onlyText.setText("You Win!")
                for (b in allButtons){
                    b?.isEnabled = false
                }
                binding.newGame.isEnabled = true;
                binding.sendEmail.isEnabled = true;
            }
            else if (model.game.round == 1){
                binding.onlyText.setText("You have taken 1 turn")
            }
            else{
                val n = model.game.round
                binding.onlyText.setText("You have taken $n turn")
            }
        }
    }
}