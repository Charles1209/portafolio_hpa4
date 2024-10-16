package com.cp1.tarea_practica2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/*
Abdy Hernandez 8-966-1927
Charles Chuez   8-960-2188


 */
class MainActivity : AppCompatActivity() {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_spinner -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, spinnerFragment())
                    addToBackStack(null)
                    commit()
                }
                true
            }

            R.id.action_radiogroup -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, radiogroupFragment())
                    addToBackStack(null)
                    commit()
                }
                true
            }

            R.id.action_datepicker -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, datepickerFragment())
                    addToBackStack(null)
                    commit()
                }
                true
            }

            R.id.action_listview -> {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fragment_container, listviewFragment())
                    addToBackStack(null)
                    commit()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}