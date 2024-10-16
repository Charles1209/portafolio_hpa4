package com.example.demo_actividades_categorias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var boton1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton1 = findViewById(R.id.button1)
        boton1.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> {
                //Codigo Interno
                val intent = Intent(this, OtraActivity::class.java)
                startActivity(intent)
            }
        }
    }


}