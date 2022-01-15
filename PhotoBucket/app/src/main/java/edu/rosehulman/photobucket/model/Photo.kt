package edu.rosehulman.photobucket.model

class Photo ( var caption: String="", var URL: String = "", var isFavourited: Boolean = false) {
    override fun toString(): String {
        return if (URL.isNotBlank()) "$caption: $URL" else ""
    }
}