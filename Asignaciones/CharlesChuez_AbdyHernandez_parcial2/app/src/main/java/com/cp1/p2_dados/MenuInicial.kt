package com.cp1.p2_dados

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MenuInicial : AppCompatActivity(){

    private var puntos : Int = 1000         //%%%
    private lateinit var usuario: String    //%%%
    private lateinit var nombreTextView: TextView
    private lateinit var puntosTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_inicial)

        // Extender (Termino en Kotlin Inflar) el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Inicializacion
        nombreTextView = findViewById(R.id.txtNombre)
        puntosTextView = findViewById(R.id.txtPuntos)
        val btn1 = findViewById<Button>(R.id.btn1)

        //Recibe usuario ingresado
        usuario = intent.getStringExtra("usuario") ?: "Usuario"    //%%%
        puntos = intent.getIntExtra("puntos",1000)      //%%%
        //DEBERIA TRAER PUNTAJE TAMBIEN

        //Aqui se coloca el texto de usuario y puntaje
        nombreTextView.text = usuario               //Nombre de la base de datos
        puntosTextView.text = puntos.toString()     //Puntaje de la base de datos

        //Click Boton CLASICO
        btn1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, ClasicoFragment.newInstance(usuario, puntos))
                addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                commit()

                btn1.visibility = View.GONE
            }
        }

        //Interceptar el botón de retroceso
        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 0) {   /*Verificar FRGMT*/
                supportFragmentManager.popBackStack()               /*Elimina el ultimo FRGMT*/

                btn1.visibility = View.VISIBLE
            } else {
                finish()                                           /*Cerrar activity*/
            }
        }
    }

    fun onPuntosActualizados(nuevosPuntos: Int) {
        puntos = nuevosPuntos
        puntosTextView.text = puntos.toString()
    }
}