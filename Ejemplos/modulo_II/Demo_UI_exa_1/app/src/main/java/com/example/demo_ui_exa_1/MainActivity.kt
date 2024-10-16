package com.example.demo_ui_exa_1

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Extender (Termino en Kotlin Inflar) el Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /** no es necesario agregar un OnClickListener para las opciones del menú cuando estás utilizando un menú de opciones estándar en Android.
     * El manejo de las selecciones de menú se realiza a través del método onOptionsItemSelected.
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_option1 -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, FragmentOption1())
                    addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                    commit()
                }
                true
            }
            R.id.action_option2 -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, FragmentOption2())
                    addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                    commit()
                }
                true
            }
            R.id.action_option3 -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, FragmentOption3())
                    addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                    commit()
                }
                true
            }
            R.id.action_option4 -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, FragmentOption4())
                    addToBackStack(null) // Si deseas agregar este fragmento a la pila de retroceso
                    commit()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}