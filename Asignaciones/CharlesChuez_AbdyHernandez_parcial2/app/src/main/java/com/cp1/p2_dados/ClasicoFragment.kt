package com.cp1.p2_dados

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class ClasicoFragment : Fragment() {

    interface OnPuntosActualizadosListener {        //$$$
        fun onPuntosActualizados(nuevosPuntos: Int)
    }

    private var listener: OnPuntosActualizadosListener? = null  //$$$

    companion object {                  //%%%
        fun newInstance(usuario: String, puntos: Int) = ClasicoFragment().apply {
            arguments = Bundle().apply {
                putString("usuario", usuario)
                putInt("puntos", puntos)
            }
        }
    }

    private var usuario : String = ""   //%%%
    private var puntos : Int = 1000     //%%%


    override fun onAttach(context: Context) {   //$$$
        super.onAttach(context)
        if (context is OnPuntosActualizadosListener) {
            listener = context
        } else {
            throw RuntimeException("$context debe implementar OnPuntosActualizadosListener")
        }
    }

    override fun onDetach() {           //$$$
        super.onDetach()
        listener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_clasico, container, false)

        //Elementos a utilizar
        val btn1 = view.findViewById<Button>(R.id.btnCLA)
        val resultadoTextView = view.findViewById<TextView>(R.id.txtResCLA)
        val txtCont = view.findViewById<TextView>(R.id.txtCntC)
        val txtVic = view.findViewById<TextView>(R.id.txtVicC)
        val imagen = view.findViewById<ImageView>(R.id.imgCLA)

        val nombreTextView = view.findViewById<TextView>(R.id.txtNombre)
        val puntosTextView = view.findViewById<TextView>(R.id.txtPuntos)

        usuario = arguments?.getString("usuario") ?: "Usuario"  //%%%
        puntos = arguments?.getInt("puntos") ?: 1000            //%%%

        nombreTextView.text = usuario
        puntosTextView.text = puntos.toString()

        //Inicializacion de variables importantes
        var contador: Int = 0
        var contvic: Int = 0

        btn1.setOnClickListener{
            val resultado = (1..6).random()
            resultadoTextView.text = resultado.toString()

            puntos = if (resultado == 6) {      //$$$
                puntos + 500
            } else {
                puntos - 100
            }

            if (resultado == 6) {
                view.setBackgroundColor(Color.parseColor("#FF9800"))
                contvic ++
                // Crear y mostrar el diálogo de victoria
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("¡VICTORIA!")
                builder.setMessage("Usted ha ganado🎉🎉🎉🎉")

                // Botón OK para cerrar el diálogo
                builder.setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss() // Cerrar el diálogo
                }

                // Mostrar el diálogo
                builder.show()
            }
            else{
                view.setBackgroundColor(Color.parseColor("#8D949D"))
            }

            //Acciones por número
            val drawableResource = when (resultado) {
                1 -> R.drawable.num1
                2 -> R.drawable.num2
                3 -> R.drawable.num3
                4 -> R.drawable.num4
                5 -> R.drawable.num5
                else -> R.drawable.num6
            }
            imagen.setImageResource(drawableResource)

            //OUTPUTS
            contador ++
            resultadoTextView.text = "$resultado"
            puntosTextView.text = puntos.toString()     //$$$
            txtCont.text = "Intentos: "+contador.toString()
            txtVic.text = "Victorias: "+contvic.toString()

            //Deshabilitar boton
            if (puntos == 0) {
                btn1.isEnabled = false
                txtCont.text = "Se acabaron los intentos"
            }

            listener?.onPuntosActualizados(puntos)
        }

        return view
    }
}

/*
                    //DIALOGO DE DERROTA
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("DERROTA")
                    builder.setMessage("Perdiste :c")

                    // Botón OK para cerrar el diálogo
                    builder.setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss() // Cerrar el diálogo
                    }

                    // Mostrar el diálogo
                    builder.show()*/