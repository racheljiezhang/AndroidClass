package edu.rosehulman.alarmnotifier.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat
import com.leinardi.android.speeddial.SpeedDialView
import edu.rosehulman.alarmnotifier.R
import edu.rosehulman.alarmnotifier.databinding.FragmentSettingsBinding
import edu.rosehulman.alarmnotifier.model.AlarmViewModel
import edu.rosehulman.alarmnotifier.utils.NotificationUtils
import java.util.*

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var model: AlarmViewModel
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        model = ViewModelProvider(requireActivity())[AlarmViewModel::class.java]
        model.setCurrentTime()
        initializeButtons()
        updateView()
        NotificationUtils.createChannel(requireContext())
        return binding.root
    }

    private fun updateView() {
        binding.currentTimeTextView.text = model.currentTimeString()
        binding.alarmTimeTextView.text = model.alarmTimeString()
    }

    private fun initializeButtons() {
        binding.alarmTimeTextView.setOnClickListener {
            launchTimePicker()
        }
        binding.speedDialFab.inflate(R.menu.menu_speed_dial)
        binding.speedDialFab.setOnActionSelectedListener(
            SpeedDialView.OnActionSelectedListener { actionItem ->
                when (actionItem.id) {
                    R.id.action_notify_now -> {
                        NotificationUtils.createAndLaunch(
                            requireContext(),
                            AlarmViewModel.AlarmType.NOW.toString().lowercase()
                        )
                        requireActivity().finish()
                        binding.speedDialFab.close() // To close the Speed Dial with animation
                        return@OnActionSelectedListener true // false will close it without animation
                    }
                    R.id.action_notify_soon -> {
                        model.setAlarmSoon()
                        requireActivity().finish()
                        return@OnActionSelectedListener false
                    }
                    R.id.action_notify_scheduled -> {
                        model.setAlarmScheduled()
                        requireActivity().finish()
                        return@OnActionSelectedListener false
                    }
                    R.id.action_notify_recurring -> {
                        model.setAlarmRecurring()
                        requireActivity().finish()
                        return@OnActionSelectedListener false
                    }
                    R.id.action_alarm_cancel_all -> {
                        model.cancelAllAlarms()
                        return@OnActionSelectedListener false
                    }
                }
                false
            })
    }


    private fun launchTimePicker() {
        val futureCalendar = Calendar.getInstance().apply {
            set(Calendar.MINUTE, get(Calendar.MINUTE) + 1)
        }
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(futureCalendar.get(Calendar.HOUR_OF_DAY))
            .setMinute(futureCalendar.get(Calendar.MINUTE))
            .setTitleText("Select Alarm time")
            .setInputMode(INPUT_MODE_KEYBOARD)
            .build()
        picker.addOnPositiveButtonClickListener {
            model.setAlarmTime(picker.hour, picker.minute)
            updateView()
        }
        picker.addOnNegativeButtonClickListener { }
        picker.show(parentFragmentManager, "tag")
    }
}