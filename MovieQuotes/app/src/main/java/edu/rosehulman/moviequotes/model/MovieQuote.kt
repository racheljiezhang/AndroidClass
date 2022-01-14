package edu.rosehulman.moviequotes.model

data class MovieQuote ( var quote: String="", var movie: String = "", var isSelected: Boolean = false ) {
    override fun toString(): String {
        return if (quote.isNotBlank()) "'$quote', from $movie" else ""
    }
}