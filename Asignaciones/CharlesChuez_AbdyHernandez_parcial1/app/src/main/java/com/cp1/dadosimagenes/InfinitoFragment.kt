package com.cp1.dadosimagenes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class InfinitoFragment : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_infinito, container, false)

        //Elementos a utilizar
        val btn1 = view.findViewById<Button>(R.id.btnINF)
        val txtView = view.findViewById<TextView>(R.id.txtResINF)
        val txtCont = view.findViewById<TextView>(R.id.txtCntI)
        val txtVic = view.findViewById<TextView>(R.id.txtVicI)
        val imagen = view.findViewById<ImageView>(R.id.imgINF)

        //Inicializacion de variables importantes
        val dado = 1..6
        var contador: Int = 0
        var contvic: Int = 0
        txtView.text = "?"

        btn1.setOnClickListener{
            val rndNum = dado.random()

            //Acciones por número
            when (rndNum) {
                1 -> {
                    imagen.setImageResource(R.drawable.num1)
                    view.setBackgroundColor(Color.parseColor("#8D949D"))
                }
                2 -> {
                    imagen.setImageResource(R.drawable.num2)
                    view.setBackgroundColor(Color.parseColor("#8D949D"))
                }
                3 -> {
                    imagen.setImageResource(R.drawable.num3)
                    view.setBackgroundColor(Color.parseColor("#8D949D"))
                }
                4 -> {
                    imagen.setImageResource(R.drawable.num4)
                    view.setBackgroundColor(Color.parseColor("#8D949D"))
                }
                5 -> {
                    imagen.setImageResource(R.drawable.num5)
                    view.setBackgroundColor(Color.parseColor("#8D949D"))
                }
                6 -> {
                    imagen.setImageResource(R.drawable.num6)
                    view.setBackgroundColor(Color.parseColor("#FF9800"))
                    contvic ++

                    //DIALOGO DE VICTORIA
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
            }

            //OUTPUTS
            contador ++
            txtView.text = "$rndNum"
            txtCont.text = "Intentos: "+contador.toString()
            txtVic.text = "Victorias: "+contvic.toString()
        }

        return view
    }
}
