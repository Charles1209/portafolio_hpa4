package com.example.demo_actividades_action


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class OtraActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var boton2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otra)
        boton2 = findViewById(R.id.button2)
        boton2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button2 -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:123")
                startActivity(intent)
            }
        }
    }

}