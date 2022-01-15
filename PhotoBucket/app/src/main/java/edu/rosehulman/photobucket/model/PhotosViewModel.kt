package edu.rosehulman.photobucket.model

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PhotosViewModel : ViewModel() {
    var photos = ArrayList<Photo>()
    var currentPos = 0

    fun getPhotoAt(pos: Int) = photos[pos]
    fun getCurrentPhoto() = getPhotoAt(currentPos)

    fun addPhoto() {
        val idx = Random.nextInt(urls.size)
        val newPhoto = Photo(captions[idx], urls[idx])
        photos.add(newPhoto)
    }

    fun useGivenOrRandom(given: String): String {
        if (given.isNotBlank()) {
            return given
        }
        val idx = Random.nextInt(urls.size)
        return urls[idx]
    }

    fun updateCurrentPhoto(caption: String, URL: String){
        photos[currentPos].caption = caption
        photos[currentPos].URL = useGivenOrRandom(URL)

    }

    fun removePhoto(){
        photos.removeAt(currentPos)
        currentPos = 0
    }

    fun updatePos(pos: Int){
        currentPos = pos
    }

    fun size() = photos.size

    fun toggleFavourite() {
        photos[currentPos].isFavourited = !photos[currentPos].isFavourited
    }

    companion object {
        val urls = arrayListOf(
            "https://static.wikia.nocookie.net/pokemon/images/2/21/001Bulbasaur.png/revision/latest/scale-to-width-down/868?cb=20200620223551",
            "https://static.wikia.nocookie.net/pokemon/images/7/73/004Charmander.png/revision/latest/scale-to-width-down/1000?cb=20200620223744",
            "https://static.wikia.nocookie.net/pokemon/images/3/39/007Squirtle.png/revision/latest/scale-to-width-down/992?cb=20200620223922",
            "https://static.wikia.nocookie.net/pokemon/images/e/e2/133Eevee.png/revision/latest/scale-to-width-down/475?cb=20140328210732",
            "https://img1.svg.com/img/gallery/how-sobble-sent-this-pokemon-player-to-jail/l-intro-1612477099.jpg",
        )

        val captions = arrayListOf(
            "Bulbasaur",
            "Charmander",
            "Squirtle",
            "Evee",
            "Sobble"
        )
    }

}