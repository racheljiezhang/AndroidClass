package edu.rosehulman.moviequotes.ui

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.rosehulman.moviequotes.adapters.MovieQuoteAdapter
import edu.rosehulman.moviequoteslayout.MovieQuote

class QuotesListFragment : Fragment() {

    private lateinit var binding: FragmentQuotesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuotesListBinding.inflate(inflater, container, false)
        val adapter = MovieQuoteAdapter(this)
        binding.recyclerView.adapter = adapter
        binding recyclerView.inputManager = LinearLayoutManger(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        return binding.root
    }
}