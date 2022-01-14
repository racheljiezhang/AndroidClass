package edu.rosehulman.photobucket.model

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PhotoViewModel : ViewModel() {
    var photo = Photo()

    fun useGivenOrRandom(given: String): String {
        if (given.isNotBlank()) {
            return given
        }
        val idx = Random.nextInt(urls.size)
        return urls[idx]
    }

    fun updateCurrentPhoto(caption: String, URL: String){
        photo.caption = caption
        photo.URL = useGivenOrRandom(URL)

    }

    companion object {
        val urls = arrayListOf(
            "https://static.wikia.nocookie.net/pokemon/images/2/21/001Bulbasaur.png/revision/latest/scale-to-width-down/868?cb=20200620223551",
            "https://static.wikia.nocookie.net/pokemon/images/7/73/004Charmander.png/revision/latest/scale-to-width-down/1000?cb=20200620223744",
            "https://static.wikia.nocookie.net/pokemon/images/3/39/007Squirtle.png/revision/latest/scale-to-width-down/992?cb=20200620223922",
            "https://static.wikia.nocookie.net/pokemon/images/e/e2/133Eevee.png/revision/latest/scale-to-width-down/475?cb=20140328210732",
            "https://static.wikia.nocookie.net/pokemon/images/0/0d/025Pikachu.png/revision/latest?cb=20200620223403",
        )
    }

}