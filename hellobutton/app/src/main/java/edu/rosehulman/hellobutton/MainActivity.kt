package edu.rosehulman.hellobutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.rosehulman.hellobutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding //basically ActivityMainBinding binding;
    var nClicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //layoutInflater comes from AppCompatActivity
        setContentView(binding.root)

        binding.button.setOnClickListener {
            nClicks++
            updateView()
        }

        updateView()

    }

    fun updateView() {
        val message = "You clicked $nClicks times"
        binding.textView.text = message
        if (nClicks > 5){
            binding.textView.setTextColor(getColor(R.color.black))
        }
    }
}