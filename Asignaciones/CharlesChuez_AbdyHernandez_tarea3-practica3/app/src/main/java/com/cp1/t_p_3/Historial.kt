package com.cp1.t_p_3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.database.Cursor
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Historial : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("MissingInflatedId")

    // db
    lateinit var dbHelper: TemperatureDBHelper
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TemperatureAdapter
    var temperatureList = mutableListOf<Temperature>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        val  conversor = findViewById<Button>(R.id.button3)
        conversor.setOnClickListener(this)

        // db

        dbHelper = TemperatureDBHelper(this)

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewTemperatures)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer y cargar todas las temperaturas guardadas desde la base de datos
        loadTemperaturesFromDatabase()

        // Crear el adaptador con la lista de temperaturas
        adapter = TemperatureAdapter(temperatureList)
        recyclerView.adapter = adapter
    }

    // Función para cargar las temperaturas desde SQLite
    private fun loadTemperaturesFromDatabase() {
        val cursor: Cursor = dbHelper.getAllTemperatures()

        if (cursor.moveToFirst()) {
            do {
                val type = cursor.getString(cursor.getColumnIndexOrThrow(TemperatureDBHelper.COLUMN_TYPE))
                val value = cursor.getDouble(cursor.getColumnIndexOrThrow(TemperatureDBHelper.COLUMN_VALUE))

                // Agregar el resultado a la lista de temperaturas
                temperatureList.add(Temperature(type, value))

            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    //VOLVER AL MAIN ACTIVITY #NO TOCAR#
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button3 -> {
                //Codigo Interno
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}