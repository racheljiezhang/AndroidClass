package edu.rosehulman.exam2zhangrj

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.rosehulman.exam2zhangrj.databinding.FragmentRemovedItemsBinding
import edu.rosehulman.exam2zhangrj.model.AppointmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RemovedItemsFragment : Fragment() {

    private lateinit var binding: FragmentRemovedItemsBinding
    private lateinit var model: AppointmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRemovedItemsBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity()).get(AppointmentViewModel::class.java)
        updateView()
        return binding.root
    }

    fun updateView(){
        binding.removedItems.text = model.removedItemsToString()
    }
}