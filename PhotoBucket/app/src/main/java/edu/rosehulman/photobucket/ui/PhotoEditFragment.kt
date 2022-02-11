package edu.rosehulman.photobucket.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.Constants
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.databinding.FragmentPhotoEditBinding
import edu.rosehulman.photobucket.model.Photo
import edu.rosehulman.photobucket.model.PhotosViewModel


class PhotoEditFragment : Fragment() {

    private lateinit var model: PhotosViewModel
    private lateinit var binding: FragmentPhotoEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model =
            ViewModelProvider(requireActivity()).get(PhotosViewModel::class.java)
        binding = FragmentPhotoEditBinding.inflate(inflater, container, false)
        setupButtons()

        return binding.root
    }

    private fun setupButtons() {
        val ref = Firebase.firestore.collection(Photo.COLLECTION_PATH)
        val subscription = ref.document(model.getCurrentPhoto().id)
            .addSnapshotListener { snapshot: DocumentSnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.d(Constants.TAG, "Error: $error")
                    return@addSnapshotListener
                }
                binding.urlEditText.setText(snapshot?.get("url") as String)
                binding.captionEditText.setText(snapshot?.get("caption") as String)
            }

        binding.showButton.setOnClickListener{
            val u = binding.urlEditText.text.toString()
            val c = binding.captionEditText.text.toString()
            model.updateCurrentPhoto(c,u)

            findNavController().navigate(R.id.nav_detail)
        }

        binding.removeButton.setOnClickListener {
            model.removePhoto()

            findNavController().navigate(R.id.nav_list)
        }

    }

}