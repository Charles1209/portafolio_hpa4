package com.cp1.tarea_practica2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class listviewFragment : Fragment() {

    @SuppressLint("MissingInflatedId")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_listview, container, false)

        /**/
        val listView: ListView = view.findViewById(R.id.listViewGeneral)

        val options = arrayOf("Combo #1", "Combo #2", "Combo #3", "Combo #4", "Combo #5")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = options[position]

            //Impresión
            Toast.makeText(requireContext(), "Seleccionaste: $selectedItem", Toast.LENGTH_SHORT).show()
        }


        return view
    }

}