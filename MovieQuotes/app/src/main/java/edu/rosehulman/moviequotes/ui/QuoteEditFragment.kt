package edu.rosehulman.moviequotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.rosehulman.moviequotes.R
import edu.rosehulman.moviequotes.databinding.FragmentQuoteEditBinding
import edu.rosehulman.moviequoteslayout.MovieQuote
import edu.rosehulman.moviequoteslayout.MovieQuoteViewModel

class QuoteEditFragment : Fragment() {

    private lateinit var model: MovieQuoteViewModel
    private lateinit var binding: FragmentQuoteEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model =
            ViewModelProvider(requireActivity()).get(MovieQuoteViewModel::class.java)
        binding = FragmentQuoteEditBinding.inflate(inflater, container, false)
        setupButtons()
        updateView()

        return binding.root
    }

    private fun setupButtons() {
        binding.doneButton.setOnClickListener {
            val q = binding.quoteEditText.text.toString()
            val m = binding.movieEditText.text.toString()
            model.movieQuote = MovieQuote(q, m)
            updateView()
            // navigate to detail screen
            findNavController().navigate(R.id.navigation_quotes_detail)
        }
        binding.clearButton.setOnClickListener {
            model.movieQuote = MovieQuote()
            binding.quoteEditText.setText("")
            binding.movieEditText.setText("")
            updateView()
        }
    }

    private fun updateView() {
        binding.currentQuoteTextView.text = model.movieQuote.toString()
    }
}