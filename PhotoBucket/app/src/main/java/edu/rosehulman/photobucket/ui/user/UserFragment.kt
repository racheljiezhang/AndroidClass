package edu.rosehulman.photobucket.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.databinding.FragmentUserBinding
import edu.rosehulman.photobucket.model.PhotosViewModel

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var model: PhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
        }
        model = ViewModelProvider(requireActivity()).get(PhotosViewModel::class.java)
        updateView()
        return binding.root
    }

    fun updateView(){
        binding.centerImage.load("https://cdn.discordapp.com/attachments/705497334615769182/930635560261922856/beauty_1627268700975.JPG")
    }

}