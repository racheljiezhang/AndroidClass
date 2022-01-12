package edu.rosehulman.moviequoteslayout
import androidx.lifecycle.ViewModel

import kotlin.random.Random

class MovieQuoteViewModel : ViewModel() {
    var movieQuotes = ArrayList<MovieQuote>()
    var currentPos = 0

    fun getQuoteAt(pos: Int) = movieQuotes[pos]
    fun getCurrentQuote() = getQuoteAt(currentPos)

    fun addQuote(movieQuote: MovieQuote?) {
        val random = getRandom()
        val newQuote = movieQuote ?: MovieQuote("quote$random", "movie$random")
        movieQuotes.add(newQuote)
    }

    fun updateCurrentQuote(quote: String, movie: String){
        movieQuotes[currentPos].quote = quote
        movieQuotes[currentPos].movie = movie
    }

    fun removeQuote(){
        movieQuotes.removeAt(currentPos)
        currentPos = 0
    }

    fun updatePos(pos: Int){
        currentPos = pos
    }

    fun size() = movieQuotes.size

    fun getRandom() = Random.nextInt(100)
}