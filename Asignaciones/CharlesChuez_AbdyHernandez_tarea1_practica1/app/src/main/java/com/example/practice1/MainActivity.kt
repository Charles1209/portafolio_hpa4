/* Charles Chuez 8-960-2188*/

package com.example.practice1
    
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Declaracion de Botones
        val fahrenheit = findViewById<Button>(R.id.button1)
        val kelvin = findViewById<Button>(R.id.button2)
        val rankine = findViewById<Button>(R.id.button3)

        // declaracion de otras
        val etInput = findViewById<EditText>(R.id.inputTemperatura)
        val tvOutput = findViewById<TextView>(R.id.outputTemperatura)

        // Declaracion de Funciones
        val toFahrenheit = Fahrenheit()
        val toKelvin = Kelvin()
        val toRankine = Rankine()

        fahrenheit.setOnClickListener {
            val celcius = etInput.text.toString().toDoubleOrNull()

            if (celcius == null){
                tvOutput.text = "Dato nulo"
            }
            else if (celcius > -274) {
                val result = toFahrenheit.conversionFun(celcius)

                tvOutput.text = "Fahrenheit: $result°F"
            }
            else {
                tvOutput.text = "Temperatura en Celcius inexistente."
            }
        }

        kelvin.setOnClickListener {
            val celcius = etInput.text.toString().toDoubleOrNull()

            if (celcius == null){
                tvOutput.text = "Dato nulo"
            }
            else if (celcius > -274) {
                val result = toKelvin.conversionFun(celcius)

                tvOutput.text = "Kelvin: $result°K"
            }
            else {
                tvOutput.text = "Temperatura en Celcius inexistente."
            }
        }

        rankine.setOnClickListener {
            val celcius = etInput.text.toString().toDoubleOrNull()

            if (celcius == null){
                tvOutput.text = "Dato nulo"
            }
            else if (celcius > -274) {
                val result = toRankine.conversionFun(celcius)

                tvOutput.text = "Rakkine: $result°R"
            }
            else {
                tvOutput.text = "Temperatura en Celcius inexistente."
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}