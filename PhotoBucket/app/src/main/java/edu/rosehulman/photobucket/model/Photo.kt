package edu.rosehulman.photobucket.model

class Photo ( var caption: String="", var URL: String = "" ) {
    override fun toString(): String {
        return if (URL.isNotBlank()) "$caption: $URL" else ""
    }
}