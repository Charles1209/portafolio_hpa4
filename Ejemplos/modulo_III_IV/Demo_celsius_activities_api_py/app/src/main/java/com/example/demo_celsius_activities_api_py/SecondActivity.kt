package com.example.demo_celsius_activities_api_py

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment_Resultados())
            .commit()

        val resultado = intent.getDoubleExtra("RESULTADO", 0.0)
        val conversion = intent.getStringExtra("CONVERSION") ?: ""

        //Insertamos el resultado a traves del metodo asignado al POST dentro del DatabaseHelper
        dbHelper = DatabaseHelper()
        dbHelper.addConversionResult(resultado, conversion)
    }
}
