package edu.rosehulman.moviequotes.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

data class MovieQuote(
    var quote: String="",
    var movie: String = "",
    var isSelected: Boolean = false

){
    @get:Exclude
    var id = ""

    @ServerTimestamp
    var created: Timestamp? = null

    override fun toString(): String {
        return if(quote.isNotBlank()) "$quote, from $movie" else ""
    }

    companion object {
        const val COLLECTION_PATH = "quotes"
        const val CREATED_KEY = "created"
        fun  from(snapshot: DocumentSnapshot):MovieQuote{
            val mq = snapshot.toObject(MovieQuote::class.java)!!
            mq.id = snapshot.id
            return mq
        }

    }


}
