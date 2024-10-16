package com.cp1.tarea_practica2

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class datepickerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_datepicker, container, false)

        /**/
        val buttonPickDate: Button = view.findViewById(R.id.btnDate)
        val textViewDate: TextView = view.findViewById(R.id.textView_date)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        buttonPickDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->

                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    textViewDate.text = "Fecha seleccionada: $formattedDate"

                },
                year, month, day
            )
            datePickerDialog.show()
        }

        return view
    }

}