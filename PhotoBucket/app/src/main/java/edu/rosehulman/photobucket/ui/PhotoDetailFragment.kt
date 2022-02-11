package edu.rosehulman.photobucket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.databinding.FragmentPhotoDetailBinding
import edu.rosehulman.photobucket.model.Photo
import edu.rosehulman.photobucket.model.PhotosViewModel


class PhotoDetailFragment : Fragment() {
    private lateinit var binding: FragmentPhotoDetailBinding
    private lateinit var model: PhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity()).get(PhotosViewModel::class.java)
        if(Firebase.auth.currentUser!!.uid != model.getCurrentPhoto().uid) {
            Toast.makeText(
                requireContext(),
                "This pic belongs to another user",
                Toast.LENGTH_LONG
            ).show()
        }
        updateView()
        return binding.root
    }
    fun updateView(){
        binding.imageView.load(model.getCurrentPhoto().url){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.detailCaption.text = model.getCurrentPhoto().caption

        binding.photoIcon
        binding.photoIcon.setImageResource(
            if(model.getCurrentPhoto().isFavourited) {
                R.drawable.ic_baseline_favorite_24
            } else {
                R.drawable.ic_baseline_photo_24
            }
        )

    }

}