package edu.rosehulman.photobucket.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.adapters.PhotoAdapter
import edu.rosehulman.photobucket.databinding.AppBarMainBinding
import edu.rosehulman.photobucket.databinding.ContentMainBinding
import edu.rosehulman.photobucket.databinding.FragmentPhotosListBinding



class PhotosListFragment : Fragment() {

    lateinit var binding: FragmentPhotosListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosListBinding.inflate(inflater, container, false)
        val adapter = PhotoAdapter(this)
        //set recyclerview and adapter properties
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.fab.setOnClickListener{
            adapter.addPhoto()
        }

        return binding.root
    }
}