package com.cp1.p2_dados

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuInicial : AppCompatActivity(), ClasicoFragment.OnUpdateListener {
    private lateinit var dbHelper: DatabaseHelper

    private lateinit var nombreTextView: TextView
    private lateinit var puntosTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_inicial)

        dbHelper = DatabaseHelper(this)

        // Obtener el ID del usuario
        val idUsuario = intent.getIntExtra("idUsuario", -1) // -1 es el valor por defecto si no se encuentra el extra

        // Empieza aquí
        ////////////////////////////////////////////////////////////////
        val fragment = ClasicoFragment() // Crea una instancia del fragmento
        fragment.setOnUpdateListener(this)

        // Resto de la configuración del Fragmento
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Crear un Bundle para pasar el idUsuario
        val bundle = Bundle().apply {
            putInt("idUsuario", idUsuario) // Añadir el idUsuario al bundle
        }

        // Establecer el bundle en el fragmento
        fragment.arguments = bundle

        // Termina aquí
        ////////////////////////////////////////////////////////////////

        // Extender (Termino en Kotlin Inflar) el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicialización
        nombreTextView = findViewById(R.id.txtNombre)
        puntosTextView = findViewById(R.id.txtPuntos)
        val btn1 = findViewById<Button>(R.id.btn1)

        // Guardamos el nombre y los puntos del usuario
        val nombreUsuario = dbHelper.obtenerNombreUsuario(idUsuario)
        val puntosUsuario = dbHelper.obtenerPuntosUsuario(idUsuario)

        // Aquí se coloca el texto de usuario y puntaje
        nombreTextView.text = nombreUsuario // Nombre de la base de datos
        puntosTextView.text = puntosUsuario?.toString() ?: "NULL" // Puntaje de la base de datos; muestra 0 si es null

        // Click Botón CLASICO
        btn1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                // replace(R.id.fragment_container, ClasicoFragment.newInstance(nombreUsuario.toString(), puntosUsuario.toInt()))
                replace(R.id.fragment_container, fragment)
                addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                commit()

                btn1.visibility = View.GONE
            }
        }

        // Interceptar el botón de retroceso // Esto para que es?????
        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 0) { // Verificar FRGMT
                supportFragmentManager.popBackStack() // Elimina el último FRGMT
                btn1.visibility = View.VISIBLE
            } else {
                finish() // Cerrar activity
            }
        }
    }

    override fun onUpdate(nuevosPuntos: Int) {
        val idUsuario = intent.getIntExtra("idUsuario", -1) // -1 es el valor por defecto si no se encuentra el extra
        puntosTextView.text = dbHelper.obtenerPuntosUsuario(idUsuario)?.toString() ?: "NULL"
    }
}