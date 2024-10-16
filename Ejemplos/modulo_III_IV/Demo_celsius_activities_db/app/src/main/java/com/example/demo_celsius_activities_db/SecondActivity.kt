package com.example.demo_celsius_activities_db

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, Fragment_Resultados())
            .commit()

        //Ejecutar la respuesta del MainActivity cuando se crea
        val resultado = intent.getDoubleExtra("RESULTADO", 0.0)

        /** Guardar el resultado en la base de datos, comumente el archivo de se crea en:
        /data/data/com.tu.paquete/databases/tu_base_de_datos.db **/
        val dbHelper = DatabaseHelper(this)
        dbHelper.addConversionResult(resultado)
    }
}