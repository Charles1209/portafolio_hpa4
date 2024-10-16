package com.example.demo_celsius_activities_demointents

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var boton2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        boton2 = findViewById(R.id.button2)
        boton2.setOnClickListener(this)

        // Obtener datos del intent
        val action = intent.action // Obtener la acción
        val uriString = intent.getStringExtra("URI") // Obtener el URI desde extras
        val uri = if (uriString != null) Uri.parse(uriString) else null // Convertir a Uri
        val category = intent.categories // Obtener categorías
        val resultado = intent.getDoubleExtra("RESULTADO", 0.0) // Obtener los extras

        // Mostrar el resultado de la conversión
        val mensaje = findViewById<TextView>(R.id.textView1)
        mensaje.text = "La conversión de celsius a fahrenheit es: ${resultado} °F"

        // Mostrar los datos del intent
        val ejemplos_estructuras = findViewById<TextView>(R.id.textView2)
        ejemplos_estructuras.text = "Acción: ${action}\nData (URI): ${uri}\nCategoría: ${category}"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button2 -> {
                finish()
            }
        }
    }
}
