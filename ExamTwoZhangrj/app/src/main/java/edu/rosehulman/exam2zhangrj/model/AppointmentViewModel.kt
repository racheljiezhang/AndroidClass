package edu.rosehulman.exam2zhangrj.model

import android.util.Log
import android.util.Log.INFO
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class AppointmentViewModel : ViewModel(){
    var appointments = ArrayList<Appointment>()
    var deleted = ArrayList<Appointment>()
    var currentPos = 0

    fun getAppointmentAt(pos: Int) = appointments[pos]
    fun getCurrentAppointment() = getAppointmentAt(currentPos)

    fun addAppointment() {
        val idx = Random.nextInt(nameOptions.size)
        val newAppointment = Appointment(nameOptions[idx], Random.nextInt(24), Random.nextInt(60))
        appointments.add(newAppointment)
        appointments.sortWith(compareBy<Appointment> { it.hour }.thenBy {it.minute})
        Log.println(INFO,"A" ,this.toString())

    }

    override fun toString(): String{
        var b = ""
        for(a in appointments){
            b = b + "\n" + a
        }
        return b;
    }


    fun updatePos(pos: Int){
        currentPos = pos
    }

    fun updateTime(hour:Int, minute:Int){
        appointments[currentPos].hour = hour
        appointments[currentPos].minute = minute
    }

    fun size() = appointments.size


    fun toggleSelected() {
        appointments[currentPos].isSelected = !appointments[currentPos].isSelected
    }

    fun deleteSelected() {
        for(a in appointments){
            if(a.isSelected){
                deleted.add(a)
            }
        }
        appointments.removeAll(deleted)
        appointments.sortWith(compareBy<Appointment> { it.hour }.thenBy {it.minute})
    }

    fun recoverDeleted(){
        appointments.addAll(deleted)
        deleted.clear()
        appointments.sortWith(compareBy<Appointment> { it.hour }.thenBy {it.minute})
    }

    fun removedItemsToString(): String{
        var b = ""
        for(a in deleted){
            b = b + "\n" + a
        }
        return b;
    }

    companion object {
        private val nameOptions = arrayOf(
            "Evan",
            "Stephen",
            "Charlie",
            "Natalie",
            "Michael",
            "Jasmine",
            "Xingheng",
            "Elvis",
            "Nick",
            "Adithya",
            "Jasmine",
            "Nicholas",
            "Blake",
            "Elle",
            "Rachel",
            "Darren",
            "Deng",
            "Vik",
            "Max",
            "Vuk",
            "Brandon",
            "Edward",
            "Lyra",
            "Jack",
            "Hannah", // plus some other very-common names.
            "Emily",
            "Sarah",
            "Madison",
            "Brianna",
            "Kaylee",
            "Kaitlyn",
            "Michael",
            "Jacob",
            "Matthew",
            "Nicholas",
            "Christopher",
            "Joseph",
            "Zachary",
            "Anna",
            "Cornelius"
        )
    }

}