package edu.rosehulman.photobucket.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.ServerTimestamp

class Photo (
    var caption: String="",
    var url: String = "",
    var uid: String ="",
    var isFavourited: Boolean = false
){
    @get:Exclude
    var id = ""

    @ServerTimestamp
    var created: Timestamp? = null

    override fun toString(): String {
        return if (url.isNotBlank()) "$caption: $url" else ""
    }

    companion object {
        const val COLLECTION_PATH = "photos"
        const val CREATED_KEY = "created"
        fun  from(snapshot: DocumentSnapshot):Photo{
            val mq = snapshot.toObject(Photo::class.java)!!
            mq.id = snapshot.id
            return mq
        }

    }
}