package com.cp1.tarea_practica2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class spinnerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_spinner, container, false)

        /**/
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val button = view.findViewById<Button>(R.id.btn1)

        val items = arrayOf("America", "Asia", "Africa", "Europa", "Oceania")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        button.setOnClickListener {
            val selectedItem = spinner.selectedItem.toString()

            //Impresión
            Toast.makeText(requireContext(), "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()
        }



        return view
    }

}