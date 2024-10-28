package com.cp1.p2_dados

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/*
Abdy Hernandez  8-966-1927
Charles Chuez   8-960-2188
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var usu : EditText     //%%%
    private lateinit var pass : EditText    //%%%
    private lateinit var inicio : Button

    //PRUEBA LOCAL
    private val users = mapOf(
        "Pedro5" to "pizza_time",
        "#Maria" to "movieparty",
        "J_uan" to "playplay12"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usu = findViewById(R.id.editUsuario)
        pass = findViewById(R.id.editContracena)
        inicio = findViewById(R.id.btnInicio)
        inicio.setOnClickListener(this)
    }

    //INICIAR SESION #NO TOCAR#
    override fun onClick(v: View?) {
        if (v?.id == R.id.btnInicio) {
            val user = usu.text.toString()
            val password = pass.text.toString()

            //Validacion de credenciales
            if (user.isNotEmpty() && users[user] == password) {
                val intent = Intent(this, MenuInicial::class.java).apply {
                    putExtra("usuario", user)
                    putExtra("puntos", 1000)
                }
                startActivity(intent)
                usu.text.clear()
                pass.text.clear()


                } else {
                    // Mostrar un mensaje de error no coinciden
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
        }
    }
}



