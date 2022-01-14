package edu.rosehulman.photobucket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.databinding.FragmentPhotoEditBinding
import edu.rosehulman.photobucket.model.PhotoViewModel


class PhotoEditFragment : Fragment() {

    private lateinit var model: PhotoViewModel
    private lateinit var binding: FragmentPhotoEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model =
            ViewModelProvider(requireActivity()).get(PhotoViewModel::class.java)
        binding = FragmentPhotoEditBinding.inflate(inflater, container, false)
        setupButtons()

        return binding.root
    }

    private fun setupButtons() {
        binding.showButton.setOnClickListener{
            val u = binding.urlEditText.text.toString()
            val c = binding.captionEditText.text.toString()
            model.updateCurrentPhoto(c,u)

            findNavController().navigate(R.id.nav_detail)
        }

    }

}