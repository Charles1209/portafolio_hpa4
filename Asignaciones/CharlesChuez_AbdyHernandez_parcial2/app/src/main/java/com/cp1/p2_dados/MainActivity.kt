package com.cp1.p2_dados

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/*
Abdy Hernandez  8-966-1927
Charles Chuez   8-960-2188
 */

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var editTextNombre : EditText     //%%%
    private lateinit var editTextContrasenna : EditText    //%%%
    private lateinit var buttonLogin : Button
    private lateinit var botonSalir : Button

    // base de datos
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var users: Map<String, String> // Usa lateinit si solo necesitas inicializar una vez

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Borrar la base de datos existente
        val databaseName = "dados.db"
        val isDeleted = deleteDatabase(databaseName) // Esta línea borra la base de datos

        if (isDeleted) {
            Toast.makeText(this, "Base de datos eliminada correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No se pudo eliminar la base de datos", Toast.LENGTH_SHORT).show()
        }

        editTextNombre = findViewById(R.id.editUsuario)
        editTextContrasenna = findViewById(R.id.editContracena)
        buttonLogin = findViewById(R.id.btnInicio)
        botonSalir = findViewById(R.id.botonSalir)
        botonSalir.setOnClickListener(this)
        buttonLogin.setOnClickListener(this)

        // val dbHelper = DatabaseHelper(this)

        /*
        // Consultar usuario
        val usuario = repository.obtenerDatosUsuarios(userId)
        Log.d("MainActivity", "Usuario: $usuario")

        // Consultar registro del usuario recién creado
        val orders = repository.obtenerRegistroUsuario(userId)
        Log.d("MainActivity", "Orders for user $userId: $orders")
        */

        //PRUEBA LOCALvvf                            b
        /*users = mapOf(
            idUsuario1 to idRegistro1,
            idUsuario2 to idRegistro2,
            idUsuario3 to idRegistro3
        )
        */

        ////////////////////////////////////////////////////////////////
    }

    //INICIAR SESION #NO TOCAR#
    override fun onClick(v: View?) {
        if (v?.id == R.id.btnInicio) {
            val usuario = editTextNombre.text.toString()
            val contrasenna = editTextContrasenna.text.toString()

            // Crear instancia de DatabaseHelper
            val dbHelper = DatabaseHelper(this)

            // Obtener el ID del usuario
            val idUsuario = dbHelper.obtenerIdUsuario(usuario, contrasenna)

            // Mostrar resultado
            if (idUsuario != null) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MenuInicial::class.java)
                intent.putExtra("idUsuario", idUsuario)

                startActivity(intent)
                editTextNombre.text.clear()
                editTextContrasenna.text.clear()
            } else {
                Toast.makeText(this, "Nombre o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        if (v?.id == R.id.botonSalir) {
            finish()
        }
    }
}