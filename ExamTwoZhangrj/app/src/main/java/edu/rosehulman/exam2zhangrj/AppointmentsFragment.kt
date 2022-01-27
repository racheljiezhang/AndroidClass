package edu.rosehulman.exam2zhangrj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import edu.rosehulman.exam2zhangrj.adapters.AppointmentAdapter
import edu.rosehulman.exam2zhangrj.databinding.FragmentAppointmentsBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AppointmentsFragment : Fragment() {

    lateinit var binding: FragmentAppointmentsBinding
    lateinit var adapter: AppointmentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentAppointmentsBinding.inflate(inflater, container, false)
        adapter = AppointmentAdapter(this)
        //set recyclerview and adapter properties
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))


        binding.fab.setOnClickListener{
            adapter.addAppointment()
        }

        fun delete(){
            adapter.deleteAll()
        }

        return binding.root
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete -> {
                adapter.deleteAll()
                Snackbar.make(requireView(), "Deleting", Snackbar.LENGTH_LONG)
                    .setAction("Undo"){
                        adapter.undo()
                    }
                    .setAnchorView(requireActivity().findViewById(R.id.AppointmentsFragment))
                    .show()
                true
            }
            R.id.action_undo -> {
                findNavController().navigate(R.id.RemovedItemsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}