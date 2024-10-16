package com.example.demo_ui_exa_1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast


class FragmentOption3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_option3, container, false)

        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        val button = view.findViewById<Button>(R.id.button)

        // Configurar evento clic del botón
        button.setOnClickListener {
            // Verificar si el CheckBox está marcado
            val message = if (checkBox.isChecked) {
                "El CheckBox está marcado"
            } else {
                "El CheckBox no está marcado"
            }
            // Mostrar el mensaje
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        return view
    }

}
