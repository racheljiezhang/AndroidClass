package edu.rosehulman.exam2zhangrj.model

class Appointment ( var name: String="", var hour: Int=0, var minute: Int=0,var isSelected: Boolean = false) {
    override fun toString(): String {
        return if (name.isNotBlank()) String.format("$name %02d:%02d", hour, minute) else ""
    }
}