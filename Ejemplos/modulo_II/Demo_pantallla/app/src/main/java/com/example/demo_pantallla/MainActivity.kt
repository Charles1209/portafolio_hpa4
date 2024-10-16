package com.example.demo_pantallla

import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mensaje = findViewById<TextView>(R.id.textView)

        mensaje.text = "El Tamaño de la pantalla es: ${getScreenSize()}\ny su densidad es: ${getDensityCategory()}"
    }

    private fun getScreenSize(): String {
        return when (resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_SMALL -> "Pequeño"
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> "Normal"
            Configuration.SCREENLAYOUT_SIZE_LARGE -> "Grande"
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> "Extra Grande"
            else -> "Indefinido"
        }
    }

    private fun getDensityCategory(): String {
        val densityDpi = resources.displayMetrics.densityDpi
        return when (densityDpi) {
            DisplayMetrics.DENSITY_LOW -> "Baja"
            DisplayMetrics.DENSITY_MEDIUM -> "Media"
            DisplayMetrics.DENSITY_HIGH -> "Alta"
            DisplayMetrics.DENSITY_XHIGH -> "Extra Alta"
            DisplayMetrics.DENSITY_XXHIGH -> "Extra Extra Alta"
            DisplayMetrics.DENSITY_XXXHIGH -> "Extra Extra Extra Alta"
            else -> "Indefinido"
        }
    }
}

