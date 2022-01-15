package edu.rosehulman.photobucket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
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
        updateView()
        return binding.root
    }
    fun updateView(){
        binding.imageView.load(model.getCurrentPhoto().URL){
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