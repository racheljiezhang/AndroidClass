package edu.rosehulman.photobucket.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import edu.rosehulman.photobucket.databinding.FragmentPhotoDetailBinding
import edu.rosehulman.photobucket.model.Photo
import edu.rosehulman.photobucket.model.PhotoViewModel


class PhotoDetailFragment : Fragment() {
    private lateinit var binding: FragmentPhotoDetailBinding
    private lateinit var model: PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoDetailBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity()).get(PhotoViewModel::class.java)
        updateView(model.photo)
        return binding.root
    }
    fun updateView(photo: Photo){
        binding.imageView.load(photo.URL){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.detailCaption.text = photo.caption


    }

}