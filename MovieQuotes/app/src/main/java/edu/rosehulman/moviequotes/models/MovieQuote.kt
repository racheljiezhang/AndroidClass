package edu.rosehulman.moviequoteslayout

data class MovieQuote ( var quote: String="", var movie: String = "" ) {
    override fun toString(): String {
        return if (quote.isNotBlank()) "'$quote', from $movie" else ""
    }
}