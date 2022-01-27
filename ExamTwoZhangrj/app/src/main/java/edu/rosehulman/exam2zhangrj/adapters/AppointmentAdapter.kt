package edu.rosehulman.exam2zhangrj.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.rosehulman.exam2zhangrj.AppointmentsFragment
import edu.rosehulman.exam2zhangrj.R
import edu.rosehulman.exam2zhangrj.model.Appointment
import edu.rosehulman.exam2zhangrj.model.AppointmentViewModel
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import android.widget.RelativeLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class AppointmentAdapter (val fragment: AppointmentsFragment) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {
    val model = ViewModelProvider(fragment.requireActivity()).get(AppointmentViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(model.getAppointmentAt(position))
        if(model.getAppointmentAt(position).isSelected){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.orange2));
        } else if(position % 2 == 0){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.orange));
        }else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.purple));
        }
    }

    override fun getItemCount() = model.size()

    fun addAppointment() {
        model.addAppointment()
        rearrange()
        notifyDataSetChanged()
    }

    fun rearrange(){
        model.appointments.sortWith(compareBy { it.hour })
        notifyDataSetChanged()
    }

    fun deleteAll(){
        model.deleteSelected()
        notifyDataSetChanged()
    }

    fun undo(){
        model.recoverDeleted()
        notifyDataSetChanged()
    }


    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.row_appointment_name_view)
        val timeTextView: TextView = itemView.findViewById(R.id.row_appointment_time_view)
        val timeImageView: ImageView = itemView.findViewById(R.id.photo_icon)

        init {

            timeImageView.setOnClickListener {
                val picker =
                    MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(0)
                        .setMinute(0)
                        .setTitleText("Select appointment time")
                        .build()

                picker.addOnPositiveButtonClickListener {
                    model.updatePos(adapterPosition)
                    model.updateTime(picker.hour, picker.minute)
                    notifyItemChanged(adapterPosition)
                    rearrange()
                }
                picker.show(fragment.parentFragmentManager, "tag");
            }

            itemView.setOnClickListener {
                model.updatePos(adapterPosition)
                model.toggleSelected()
                notifyItemChanged(adapterPosition)
                true
            }


        }

        fun bind(appointment: Appointment) {

            nameTextView.text = appointment.name

            timeTextView.text = String.format("%02d:%02d", appointment.hour, appointment.minute)

        }
    }


}