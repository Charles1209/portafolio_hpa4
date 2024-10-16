package com.example.demo_celsius_activities_api_py

import android.content.Intent
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
                val conversion = "F"

                //Enviar datos entre actividades
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("RESULTADO", resultado)
                intent.putExtra("CONVERSION", conversion)
                startActivity(intent)
            }
        }
    }

    private fun celsius_fahrenheit(celsius:Double): Double {
        val fahrenheit = (celsius * 9/5)+32
        return fahrenheit
    }

}
