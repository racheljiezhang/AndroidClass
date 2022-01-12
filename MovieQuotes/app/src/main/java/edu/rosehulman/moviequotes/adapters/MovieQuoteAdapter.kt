package edu.rosehulman.moviequotes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.moviequotes.R
import edu.rosehulman.moviequotes.ui.QuotesListFragment
import edu.rosehulman.moviequoteslayout.MovieQuote
import edu.rosehulman.moviequoteslayout.MovieQuoteViewModel

class MovieQuoteAdapter(fragment: QuotesListFragment) : RecyclerView.Adapter<MovieQuoteAdapter.MovieQuoteViewHolder>() {
    val model = ViewModelProvider(fragment.requireActivity()).get(MovieQuoteViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieQuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_quote, parent, false)
        return MovieQuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieQuoteViewHolder, position: Int) {
        holder.bind(model.getQuoteAt(position))
    }

    override fun getItemCount() = model.size()
    }

    inner class MovieQuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteTextView: TextView = itemView.findViewById(R.id.row_quote_text_view)
        val movieTextView: TextView = itemView.findViewById(R.id.row_movie_text_view)

        fun bind(movieQuote: MovieQuote) {
            quoteTextView.text = movieQuote.quote
            movieTextView.text = movieQuote.movie
        }
    }

}