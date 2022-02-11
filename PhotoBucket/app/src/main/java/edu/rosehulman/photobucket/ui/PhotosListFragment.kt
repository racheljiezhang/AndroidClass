package edu.rosehulman.photobucket.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.Constants
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.adapters.PhotoAdapter
import edu.rosehulman.photobucket.databinding.AppBarMainBinding
import edu.rosehulman.photobucket.databinding.ContentMainBinding
import edu.rosehulman.photobucket.databinding.FragmentPhotosListBinding
import edu.rosehulman.photobucket.model.Photo


class PhotosListFragment : Fragment() {

    private lateinit var binding: FragmentPhotosListBinding
    private lateinit var adapter: PhotoAdapter
    var onlyUser = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentPhotosListBinding.inflate(inflater, container, false)
        adapter = PhotoAdapter(this)
        //set recyclerview and adapter properties
        binding.recyclerView.adapter = adapter
        if(onlyUser) {
            adapter.addOnlyListener(fragmentName)
        }
        else{
            adapter.addAllListener(fragmentName)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)

        setTitle()

        binding.fab.setOnClickListener{
            adapter.addPhoto()
        }

        return binding.root
    }

    fun setTitle() {
        val ref = Firebase.firestore.collection("settings")
        val subscription = ref.document("settings")
            .addSnapshotListener { snapshot: DocumentSnapshot?, error: FirebaseFirestoreException? ->
                error?.let {
                    Log.d(Constants.TAG, "Error: $error")
                    return@addSnapshotListener
                }
                setActivityTitle(snapshot?.get("title") as String)
            }
    }

    fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

    fun Fragment.setActivityTitle(title: String) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.removeListener(fragmentName)
    }
    companion object{
        const val fragmentName = "PhotosListFragment"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_switch -> {
                this.onlyUser = !onlyUser
                adapter.removeListener(fragmentName)
                Log.d(Constants.TAG, "oogie")
                if(onlyUser) {
                    adapter.addOnlyListener(fragmentName)
                }
                else{
                    adapter.addAllListener(fragmentName)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}