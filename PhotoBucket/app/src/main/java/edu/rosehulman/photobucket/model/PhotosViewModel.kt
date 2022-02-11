package edu.rosehulman.photobucket.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.Constants
import kotlin.random.Random

class PhotosViewModel : ViewModel() {
    var photos = ArrayList<Photo>()
    var myPhotos = ArrayList<Photo>()
    var currentPos = 0

    fun getPhotoAt(pos: Int) = photos[pos]
    fun getCurrentPhoto() = getPhotoAt(currentPos)

    private lateinit var ref: CollectionReference

    val subscriptions = HashMap<String, ListenerRegistration>()

    fun addAllListener(fragmentName: String, observer: () -> Unit) {
        ref = Firebase.firestore.collection(Photo.COLLECTION_PATH)
        val subscription = ref
            .orderBy(Photo.CREATED_KEY, Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot: QuerySnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.d(Constants.TAG, "Error: $error")
                    return@addSnapshotListener
                }

                photos.clear()
                snapshot?.documents?.forEach {
                    photos.add(Photo.from(it))
                }
                observer()
            }
        subscriptions[fragmentName] = subscription
    }

    fun addOnlyListener(fragmentName: String, observer: () -> Unit) {
        ref = Firebase.firestore.collection(Photo.COLLECTION_PATH)
        val subscription = ref
            .orderBy(Photo.CREATED_KEY, Query.Direction.ASCENDING)
            .whereEqualTo("uid", Firebase.auth.currentUser!!.uid)
            .addSnapshotListener { snapshot: QuerySnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.d(Constants.TAG, "Error: $error")
                    return@addSnapshotListener
                }

                photos.clear()
                snapshot?.documents?.forEach {
                    photos.add(Photo.from(it))
                }
                observer()
            }
        subscriptions[fragmentName] = subscription
    }

    fun removeListener(fragmentName: String) {
        subscriptions[fragmentName]?.remove()   //tells firebase to stop listening
        subscriptions.remove(fragmentName)  //removes from map
    }

    fun addPhoto() {
        val idx = Random.nextInt(urls.size)
        val newPhoto = Photo(captions[idx], urls[idx], Firebase.auth.currentUser!!.uid)
        ref.add(newPhoto)
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
        photos[currentPos].url = useGivenOrRandom(URL)
        ref.document(getCurrentPhoto().id).set(getCurrentPhoto())

    }

    fun removePhoto(){
        ref.document(getCurrentPhoto().id).delete()
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