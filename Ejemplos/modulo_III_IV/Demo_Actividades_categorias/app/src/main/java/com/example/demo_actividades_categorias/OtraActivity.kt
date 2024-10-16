package com.example.demo_actividades_categorias

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class OtraActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var boton1: Button
    lateinit var boton2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otra)

        boton1 = findViewById(R.id.btnOpenApp)
        boton2 = findViewById(R.id.btnGoToHome)

        boton1.setOnClickListener(this)
        boton2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnOpenApp -> {
                // Abrir navegador con una URL
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
                browserIntent.addCategory(Intent.CATEGORY_BROWSABLE)
                startActivity(browserIntent)
            }
            R.id.btnGoToHome -> {
                // Ir a la pantalla principal del dispositivo
                val homeIntent = Intent(Intent.ACTION_MAIN)
                homeIntent.addCategory(Intent.CATEGORY_HOME)
                homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(homeIntent)
            }
        }
    }
}
