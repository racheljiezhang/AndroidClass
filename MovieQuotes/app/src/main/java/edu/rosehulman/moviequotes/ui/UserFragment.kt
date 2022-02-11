package edu.rosehulman.moviequotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.rosehulman.moviequotes.R
import edu.rosehulman.moviequotes.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
        }

        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_user_edit)
        }
        return binding.root
    }

}