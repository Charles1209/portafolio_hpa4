package com.cp1.p2_dados

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
import android.widget.Toast

class ClasicoFragment : Fragment() {
    private lateinit var dbHelper: DatabaseHelper
    private var idUsuario: Int = -1 // Variable para almacenar el idUsuario
    private var puntosUsuario: Int = 0 // Cambiar a var para poder modificarlo

    //////////////////////////////////////////////////////////////////////77

    interface OnUpdateListener {
        fun onUpdate(nuevosPuntos: Int)
    }

    private var updateListener: OnUpdateListener? = null

    fun setOnUpdateListener(listener: MenuInicial) {
        updateListener = listener
    }

    private fun actualizarPuntos(nuevosPuntos: Int) {
        // Llama al callback cuando los puntos cambian
        updateListener?.onUpdate(nuevosPuntos)
    }

    ///////////////////////////////////////////////////////////////////////

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dbHelper = DatabaseHelper(requireContext()) // Inicializa aquí

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_clasico, container, false)

        //Elementos a utilizar
        val btn1 = view.findViewById<Button>(R.id.btnCLA)
        val resultadoTextView = view.findViewById<TextView>(R.id.txtResCLA)
        val txtCont = view.findViewById<TextView>(R.id.txtCntC)
        val txtVic = view.findViewById<TextView>(R.id.txtVicC)
        val imagen = view.findViewById<ImageView>(R.id.imgCLA)

        val nombreTextView = view.findViewById<TextView>(R.id.txtNombre)

        // Recibir usuario y idUsuario
        idUsuario = arguments?.getInt("idUsuario", -1) ?: -1 // Obtener el idUsuario

        // Obtener el nombre y los puntos del usuario
        val nombreUsuario = dbHelper.obtenerNombreUsuario(idUsuario)
        puntosUsuario = dbHelper.obtenerPuntosUsuario(idUsuario) ?: 0 // Inicializa aquí

        // Asignar valores a los TextViews
        nombreTextView.text = nombreUsuario ?: "Usuario" // Si es null, mostrar "Usuario"
        val puntosTextView = view.findViewById<TextView>(R.id.txtPuntos)
        puntosTextView.text = puntosUsuario.toString()

        // Inicialización de variables importantes
        var contador: Int = 0
        var contvic: Int = 0

        btn1.setOnClickListener {
            val resultado = (1..6).random()
            resultadoTextView.text = resultado.toString()

            // Actualizar puntosUsuario
            puntosUsuario = if (resultado == 6) {
                puntosUsuario + 500 // Incrementa 500 si el resultado es 6
            } else {
                puntosUsuario - 100 // Decrementa 100 en caso contrario
            }

            // Actualizar los puntos en la base de datos
            val exito = dbHelper.actualizarPuntosUsuario(idUsuario, puntosUsuario)
            if (exito) {
                puntosTextView.text = puntosUsuario.toString() // Actualizar el texto del puntaje en la interfaz
            } else {
                Toast.makeText(requireContext(), "Error al actualizar los puntos", Toast.LENGTH_SHORT).show()
            }

            // Actualizar los puntos en la base de datos
            /*val database = DatabaseHelper(requireContext())
            database.actualizarPuntosUsuario(idUsuario, puntosUsuario) // usuarioId debe estar definido
            */


            if (resultado == 6) {
                view.setBackgroundColor(Color.parseColor("#FF9800"))
                contvic++
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
            } else {
                view.setBackgroundColor(Color.parseColor("#8D949D"))
            }

            // Acciones por número
            val drawableResource = when (resultado) {
                1 -> R.drawable.num1
                2 -> R.drawable.num2
                3 -> R.drawable.num3
                4 -> R.drawable.num4
                5 -> R.drawable.num5
                else -> R.drawable.num6
            }
            imagen.setImageResource(drawableResource)

            // OUTPUTS
            contador++
            resultadoTextView.text = "$resultado"
            txtCont.text = "Intentos: $contador"
            txtVic.text = "Victorias: $contvic"

            // Deshabilitar botón
            if (puntosUsuario <= 0) {
                btn1.isEnabled = false
                txtCont.text = "Se acabaron los intentos"
            }
        }

        return view
    }
}