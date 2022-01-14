package edu.rosehulman.moviequotes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import edu.rosehulman.moviequotes.databinding.FragmentQuotesDetailBinding
import edu.rosehulman.moviequotes.model.MovieQuoteViewModel

class QuotesDetailFragment : Fragment() {
    private lateinit var binding: FragmentQuotesDetailBinding
    private lateinit var model: MovieQuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuotesDetailBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity()).get(MovieQuoteViewModel::class.java)
        updateView()
        return binding.root
    }
    fun updateView(){
        Log.d("MQ", "in detail update view")
        binding.quoteTextView.text = model.getCurrentQuote().quote
        binding.movieTextView.text = model.getCurrentQuote().movie
    }

}