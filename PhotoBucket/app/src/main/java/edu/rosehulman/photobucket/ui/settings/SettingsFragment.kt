package edu.rosehulman.photobucket.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.Constants
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.databinding.FragmentSettingsBinding
import edu.rosehulman.photobucket.model.Photo

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var ref: CollectionReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        ref = Firebase.firestore.collection("settings")
        val subscription = ref.document("settings")
            .addSnapshotListener { snapshot: DocumentSnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.d(Constants.TAG, "Error: $error")
                    return@addSnapshotListener
                }
                Log.d(Constants.TAG, snapshot?.get("title") as String)
                binding.editName.setText(snapshot?.get("title") as String)
            }

        binding.doneButton.setOnClickListener{
            ref.document("settings").update("title", binding.editName.text.toString())
            findNavController().navigate(R.id.nav_list)
        }

        return binding.root
    }

}