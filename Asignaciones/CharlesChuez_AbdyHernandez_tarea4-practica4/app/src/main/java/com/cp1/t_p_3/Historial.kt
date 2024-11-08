package com.cp1.t_p_3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class Historial : AppCompatActivity(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    @SuppressLint("MissingInflatedId")

    // SwipeRefreshLayout
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    // RecyclerView y adaptador
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TemperatureAdapter
    var temperatureList = mutableListOf<Temperature>()

    // Database helper
    lateinit var dbHelper: TemperatureDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        val conversor = findViewById<Button>(R.id.button3)
        conversor.setOnClickListener(this)

        // Inicializar SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipe_ly)
        swipeRefreshLayout.setOnRefreshListener(this)

        // Inicializar el RecyclerView y configurarlo como invisible inicialmente
        recyclerView = findViewById(R.id.recyclerViewTemperatures)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.visibility = View.GONE // Ocultar al inicio

        // Inicializar la base de datos
        dbHelper = TemperatureDBHelper(this)

        // Crear el adaptador con la lista vacía y asignarlo al RecyclerView
        adapter = TemperatureAdapter(temperatureList)
        recyclerView.adapter = adapter
    }

    // Función para cargar las temperaturas desde SQLite
    private fun loadTemperaturesFromDatabase() {
        // Limpia la lista antes de cargar nuevos datos
        temperatureList.clear()

        val cursor = dbHelper.getAllTemperatures()
        if (cursor.moveToFirst()) {
            do {
                val type = cursor.getString(cursor.getColumnIndexOrThrow(TemperatureDBHelper.COLUMN_TYPE))
                val value = cursor.getDouble(cursor.getColumnIndexOrThrow(TemperatureDBHelper.COLUMN_VALUE))

                // Agregar el resultado a la lista de temperaturas
                temperatureList.add(Temperature(type, value))
            } while (cursor.moveToNext())
        }
        cursor.close()

        // Notificar al adaptador de los cambios
        adapter.notifyDataSetChanged()
    }

    // Volver al MainActivity
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button3 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onRefresh() {
        // Cargar datos desde la base de datos y actualizar la interfaz de usuario
        Handler(Looper.getMainLooper()).postDelayed({
            // Cargar los datos y mostrarlos
            loadTemperaturesFromDatabase()

            // Mostrar el RecyclerView después de actualizar
            recyclerView.visibility = View.VISIBLE

            // Detener la animación de refresco y mostrar mensaje
            swipeRefreshLayout.isRefreshing = false
            Toast.makeText(this, "Refreshed", Toast.LENGTH_LONG).show()
        }, 300) // Puedes ajustar el retraso según sea necesario
    }
}