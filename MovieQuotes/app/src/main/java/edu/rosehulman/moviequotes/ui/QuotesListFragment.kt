package edu.rosehulman.moviequotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import edu.rosehulman.moviequotes.R
import edu.rosehulman.moviequotes.adapters.MovieQuoteAdapter
import edu.rosehulman.moviequotes.databinding.FragmentQuotesListBinding
import edu.rosehulman.moviequotes.model.MovieQuote


class QuotesListFragment : Fragment() {

    lateinit var binding: FragmentQuotesListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuotesListBinding.inflate(inflater, container, false)
        // TODO: recycler
        val adapter = MovieQuoteAdapter(this)
        //set recyclerview and adapter properties
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        binding.fab.setOnClickListener{
            adapter.addQuote(null)
        }
        return binding.root
    }
}
