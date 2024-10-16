package com.example.demo_celsius_activities_demointents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var boton1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton1 = findViewById(R.id.button1)
        boton1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                val celsius = findViewById<EditText>(R.id.editText1)
                val resultado = celsius_fahrenheit(celsius.text.toString().toDouble())

                // Crear Intent para navegar a la segunda actividad
                val intent = Intent(this, SecondActivity::class.java)

                // Enviar un URI como extra en lugar de usar data
                val webpage: Uri = Uri.parse("https://fisc.utp.ac.pa")
                intent.putExtra("URI", webpage.toString()) // Enviar URI como extra

                // Acción personalizada
                intent.action = "com.example.ACTION_CONVERT"

                // Categoría personalizada
                intent.addCategory(Intent.CATEGORY_DEFAULT)

                // Enviar el resultado
                intent.putExtra("RESULTADO", resultado)

                startActivity(intent)
            }
        }
    }

    private fun celsius_fahrenheit(celsius: Double): Double {
        return (celsius * 9 / 5) + 32
    }
}
