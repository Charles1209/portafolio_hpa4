package com.cp1.t_p_3

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dbHelper: TemperatureDBHelper
    lateinit var historial: Button
    lateinit var radioButton: RadioButton
    var radioGroup: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configura la Toolbar como ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicializa las vistas y variables, y configura los listeners
        dbHelper = TemperatureDBHelper(this)
        val input = findViewById<EditText>(R.id.editTextText)
        val output = findViewById<TextView>(R.id.textView3)
        val conversor = findViewById<Button>(R.id.button)
        historial = findViewById(R.id.button2)
        historial.setOnClickListener(this)
        radioGroup = findViewById<RadioGroup>(R.id.rg_Temp)

        // Configuración de listeners y lógica adicional
        // ...
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_descargas -> {
                // Reemplazar el fragmento y agregarlo a la pila de retroceso
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DescargarHistorial()) // Reemplaza el fragmento actual
                    .addToBackStack(null)  // Guarda la transacción en la pila de retroceso
                    .commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button2 -> {
                val intent = Intent(this, Historial::class.java)
                startActivity(intent)
            }
        }
    }
}