package edu.rosehulman.moviequotes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import edu.rosehulman.moviequotes.R
import edu.rosehulman.moviequotes.databinding.FragmentQuoteEditBinding
import edu.rosehulman.moviequotes.model.MovieQuote
import edu.rosehulman.moviequotes.model.MovieQuoteViewModel

class QuoteEditFragment : Fragment() {

    private lateinit var model: MovieQuoteViewModel
    private lateinit var binding: FragmentQuoteEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model =
            ViewModelProvider(requireActivity()).get(MovieQuoteViewModel::class.java)
        binding = FragmentQuoteEditBinding.inflate(inflater, container, false)
        setupButtons()
        updateView()

        return binding.root
    }

    private fun setupButtons() {
        binding.doneButton.setOnClickListener {
            val q = binding.quoteEditText.text.toString()
            val m = binding.movieEditText.text.toString()
            model.updateCurrentQuote(q,m)
            updateView()
            // navigate to detail screen
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)

                    .setTitleText("Select Appointment time")
                    .build()
            picker.addOnPositiveButtonClickListener {
                val s = String.format("Time: %d:%2d", picker.hour, picker.minute)
//                Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
                Snackbar.make(requireView(), s, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Continue"){
                        findNavController().navigate(R.id.navigation_quotes_detail)
                    }
                    .setAnchorView(requireActivity().findViewById(R.id.nav_view))
                    .show()


            }
            picker.show(parentFragmentManager, "tag");
        }

        binding.clearButton.setOnClickListener {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle("")
                .setMessage("Are you sure you want to clear?")
                .setPositiveButton(android.R.string.ok){dialog, which->
                    //TODO
                    model.removeCurrentQuote()
                    binding.quoteEditText.setText("")   //use setText!!!
                    binding.movieEditText.setText("")
                    updateView()
                    findNavController().navigate(R.id.navigation_quotes)
                }.setNegativeButton(android.R.string.cancel, null)
                .show()


        }
    }

    private fun updateView() {
        binding.currentQuoteTextView.setText("${model.getCurrentQuote().quote}, from ${model.getCurrentQuote().movie}")
        binding.quoteEditText.setText(model.getCurrentQuote().quote)
        binding.movieEditText.setText(model.getCurrentQuote().movie)
    }

}