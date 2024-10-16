package com.example.demo_pantallla_imagenes

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var boton1 : Button
    lateinit var imageView : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton1 = findViewById(R.id.button1)
        boton1.setOnClickListener(this)

        imageView = findViewById(R.id.imageView)
        // Oculta la imagen cuando se crea la actividad
        imageView.visibility = ImageView.GONE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                // Cambia la visibilidad de la imagen cuando se hace clic en el botón
                if (imageView.visibility == ImageView.VISIBLE) {
                    imageView.visibility = ImageView.GONE
                } else {
                    imageView.visibility = ImageView.VISIBLE
                }
            }
        }
    }


}

