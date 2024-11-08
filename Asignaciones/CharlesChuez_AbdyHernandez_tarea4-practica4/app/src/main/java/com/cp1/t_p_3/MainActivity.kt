// Abdy Hernandez   8-966-1927
// Charles Chuez    8-960-2188

package com.cp1.t_p_3

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import android.database.Cursor
import android.util.Log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dbHelper: TemperatureDBHelper // db

    lateinit var historial: Button
    lateinit var radioButton: RadioButton
    var radioGroup: RadioGroup? = null

    //@SuppressLint("MissingInflatedId") // No sé para qué es esto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge() // No se para qué es esto
        setContentView(R.layout.activity_main)

        dbHelper = TemperatureDBHelper(this) // db

        val input = findViewById<EditText>(R.id.editTextText)
        val output = findViewById<TextView>(R.id.textView3)

        val conversor = findViewById<Button>(R.id.button)
        historial = findViewById(R.id.button2)
        historial.setOnClickListener(this)

        val radioGroup: RadioGroup? = findViewById<RadioGroup>(R.id.rg_Temp)

        //Llamada a las clases
        val ConFar = Far()
        val ConKel = Kel()
        val ConRan = Ran()

        conversor.setOnClickListener {
            val selectedId: Int = radioGroup!!.checkedRadioButtonId
            val Cel = input.text.toString().toDoubleOrNull()

            radioButton = findViewById(selectedId)

                            //Error por verificar: CRASH cuando el radio boton es null (No hay ningun radio-boton seleccionado)
            if (Cel != null /*&& selectedId != null*/) {

                when (radioButton.id) {
                    R.id.rb_F -> {
                        if (Cel >= -273.15) {
                            val resultado = ConFar.convertidorCel(Cel)
                            output.text = "Fahrenheit: $resultado°F"
                            dbHelper.insertTemperature("Fahrenheit", resultado)
                        } else {
                            output.text = "Error. Ingrese un valor válido"
                        }
                    }

                    R.id.rb_K -> {
                        if (Cel >= -273.15) {
                            val resultado = ConKel.convertidorCel(Cel)
                            output.text = "Kelvin: $resultado°K"
                            dbHelper.insertTemperature("Kelvin", resultado)
                        } else {
                            output.text = "Error. Ingrese un valor válido"
                        }
                    }

                    R.id.rb_R -> {
                        if (Cel >= -273.15) {
                            val resultado = ConRan.convertidorCel(Cel)
                            output.text = "Rankine: $resultado°R"
                            dbHelper.insertTemperature("Rankine", resultado)
                        } else {
                            output.text = "Error. Ingrese un valor válido"
                        }
                    }
                }
            }
            else{
                output.text = "Error. Ingrese un valor válido"
            }
        }
    }

    //IR A LA BASE DE DATOS #NO TOCAR#
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button2 -> {
                    //Codigo Interno
                    val intent = Intent(this, Historial::class.java)
                    startActivity(intent)
            }
        }
    }
}
