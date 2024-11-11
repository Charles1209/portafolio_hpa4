package com.cp1.t_p_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class DescargarHistorial : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_descargas, container, false)

        val botonCVS = view.findViewById<Button>(R.id.botonCVS)
        val botonXLS = view.findViewById<Button>(R.id.botonXLS)
        val botonXLSX = view.findViewById<Button>(R.id.botonXLSX)
        val botonRegresar = view.findViewById<Button>(R.id.boton_regresar)

        botonCVS.setOnClickListener {
            // Lógica para el botón CSV
        }

        botonXLS.setOnClickListener {
            // Lógica para el botón XLS
        }

        botonXLSX.setOnClickListener {
            // Lógica para el botón XLSX
        }

        // Configura el OnClickListener para el botón regresar
        botonRegresar.setOnClickListener {
            // Pop de la pila de retroceso para regresar a la actividad anterior (MainActivity)
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view // Asegúrate de devolver `view` aquí
    }
}