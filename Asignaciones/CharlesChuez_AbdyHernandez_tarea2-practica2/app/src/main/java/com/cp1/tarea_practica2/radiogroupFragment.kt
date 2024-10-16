package com.cp1.tarea_practica2

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class radiogroupFragment : Fragment() {

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_radiogroup, container, false)

        /**/
        val radioGroup: RadioGroup? = view.findViewById(R.id.radioGroup)
        val button : Button = view.findViewById(R.id.btnApply)

        button.setOnClickListener {
            val selectedId: Int = radioGroup!!.checkedRadioButtonId/****Problemaaaassssss*****/

            radioButton = view.findViewById(selectedId)

            // Cambiar el color de fondo
            when (radioButton.id) {
                R.id.radioButton_Rojo -> {
                    view.setBackgroundColor(Color.RED)
                    Toast.makeText(requireContext(), "Rojo", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton_Verde -> {
                    view.setBackgroundColor(Color.GREEN)
                    Toast.makeText(requireContext(), "Verde", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton_Azul -> {
                    view.setBackgroundColor(Color.BLUE)
                    Toast.makeText(requireContext(), "Azul", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //Impresión
                    Toast.makeText(requireContext(), "Seleccione un color", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

}