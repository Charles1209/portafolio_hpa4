package com.cp1.dadosimagenes

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*
Abdy Hernandez  8-966-1927
Charles Chuez   8-960-2188
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Extender (Termino en Kotlin Inflar) el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)

        //Click Boton CLASICO
        btn1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, ClasicoFragment())
                addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                commit()

                btn1.visibility = View.GONE
                btn2.visibility = View.GONE
            }
        }

        //Click Boton INFINITO
        btn2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, InfinitoFragment())
                addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                commit()

                btn1.visibility = View.GONE
                btn2.visibility = View.GONE
            }
        }

        //Interceptar el botón de retroceso
        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 0) {   /*Verificar FRGMT*/
                supportFragmentManager.popBackStack()               /*Elimina el ultimo FRGMT*/

                btn1.visibility = View.VISIBLE
                btn2.visibility = View.VISIBLE
            } else {
                finish()                                            /*Cerrar activity*/
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}