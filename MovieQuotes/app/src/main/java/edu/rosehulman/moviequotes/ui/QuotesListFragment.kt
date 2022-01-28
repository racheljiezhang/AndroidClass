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

    private lateinit var binding: FragmentQuotesListBinding
    private lateinit var adapter: MovieQuoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuotesListBinding.inflate(inflater, container, false)
        adapter = MovieQuoteAdapter(this)
        //set recyclerview and adapter properties
        binding.recyclerView.adapter = adapter
        adapter.addListener(fragmentName)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        binding.fab.setOnClickListener{
            adapter.addQuote(null)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeListener(fragmentName)
    }
    companion object{
        const val fragmentName = "QuotesListFragment"
    }
}
