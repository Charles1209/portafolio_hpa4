package com.cp1.p2_dados

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dados.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_USUARIOS = "Usuarios"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CONTRASENNA = "contrasenna"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Definición de las tablas y sus columnas
        val CREAR_TABLA_USUARIOS = """
            CREATE TABLE IF NOT EXISTS $TABLE_USUARIOS (
                id_usuario INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_CONTRASENNA TEXT NOT NULL,
                fecha_registro TEXT NOT NULL
            );
        """

        val CREAR_TABLA_REGISTROS = """
            CREATE TABLE IF NOT EXISTS Registros (
                id_registro INTEGER PRIMARY KEY AUTOINCREMENT,
                id_usuario INTEGER NOT NULL,
                cantidad_puntos INTEGER,
                fecha_registro TEXT NOT NULL,
                FOREIGN KEY(id_usuario) REFERENCES $TABLE_USUARIOS(id_usuario) ON DELETE CASCADE
            );
        """

        db.execSQL(CREAR_TABLA_USUARIOS)
        db.execSQL(CREAR_TABLA_REGISTROS)

        // Inserciones iniciales
        db.execSQL("INSERT INTO $TABLE_USUARIOS ($COLUMN_NOMBRE, $COLUMN_CONTRASENNA, fecha_registro) VALUES ('Pedro', '1234', '2024-10-27')")
        db.execSQL("INSERT INTO $TABLE_USUARIOS ($COLUMN_NOMBRE, $COLUMN_CONTRASENNA, fecha_registro) VALUES ('Maria', '5678', '2024-10-27')")
        db.execSQL("INSERT INTO $TABLE_USUARIOS ($COLUMN_NOMBRE, $COLUMN_CONTRASENNA, fecha_registro) VALUES ('Juan', '9012', '2024-10-27')")

        // **No realices lecturas aquí. Es mejor hacerlo después de la creación.**
        // Aquí deberías realizar las inserciones de registros basadas en IDs válidos
        // Puedes hacerlo en la primera vez que accedas a la app o desde una actividad

        // Por simplicidad, simplemente puedes añadir algunos registros después de asegurarte que la tabla existe

        db.execSQL("INSERT INTO Registros (id_usuario, cantidad_puntos, fecha_registro) VALUES (1, 1000, '2024-10-28')")
        db.execSQL("INSERT INTO Registros (id_usuario, cantidad_puntos, fecha_registro) VALUES (2, 1000, '2024-10-28')")
        db.execSQL("INSERT INTO Registros (id_usuario, cantidad_puntos, fecha_registro) VALUES (3, 1000, '2024-10-28')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS") // Cambia "Usuario" por "Usuarios"
        db.execSQL("DROP TABLE IF EXISTS Registros") // Cambia "Registro" por "Registros"
        onCreate(db)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.execSQL("PRAGMA foreign_keys=ON;") // Habilitar llaves foráneas
    }

    // Método para obtener nombres y contraseñas de los usuarios
    fun obtenerUsuarios(): List<Pair<String, String>> {
        val usuarios =
            mutableListOf<Pair<String, String>>() // Lista para almacenar pares (nombre, contraseña)
        val db = this.readableDatabase // Abrir la base de datos en modo lectura
        val cursor = db.rawQuery(
            "SELECT $COLUMN_NOMBRE, $COLUMN_CONTRASENNA FROM $TABLE_USUARIOS",
            null
        ) // Ejecutar la consulta

        // Recorrer los resultados del cursor
        if (cursor.moveToFirst()) {
            do {
                val nombre =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)) // Obtener el nombre
                val contrasenna =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTRASENNA)) // Obtener la contraseña
                usuarios.add(
                    Pair(
                        nombre,
                        contrasenna
                    )
                ) // Añadir el par (nombre, contraseña) a la lista
            } while (cursor.moveToNext())
        }

        cursor.close() // Cerrar el cursor
        db.close() // Cerrar la base de datos
        return usuarios // Retornar la lista de usuarios
    }

    fun obtenerIdUsuario(nombre: String, contrasenna: String): Int? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT id_usuario FROM $TABLE_USUARIOS WHERE $COLUMN_NOMBRE = ? AND $COLUMN_CONTRASENNA = ?",
            arrayOf(nombre, contrasenna)
        )

        var idUsuario: Int? = null
        if (cursor.moveToFirst()) {
            idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"))
        }

        cursor.close()
        db.close()
        return idUsuario
    }

    fun obtenerNombreUsuario(idUsuario: Int): String? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT $COLUMN_NOMBRE FROM $TABLE_USUARIOS WHERE id_usuario = ?",
            arrayOf(idUsuario.toString())
        )

        var nombreUsuario: String? = null // Cambiamos la variable para almacenar el nombre
        if (cursor.moveToFirst()) {
            nombreUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
        }

        cursor.close()
        db.close()
        return nombreUsuario // Devolvemos el nombre del usuario
    }

    fun obtenerPuntosUsuario(idUsuario: Int): Int? {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT cantidad_puntos FROM Registros WHERE id_usuario = ?",
            arrayOf(idUsuario.toString())
        )

        var puntosUsuario: Int? = null // Cambiamos la variable para almacenar la cantidad de puntos
        if (cursor.moveToFirst()) {
            puntosUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad_puntos")) // Obtenemos la cantidad de puntos
        }

        cursor.close()
        db.close()
        return puntosUsuario // Devolvemos la cantidad de puntos del usuario
    }

    fun actualizarPuntosUsuario(idUsuario: Int, nuevosPuntos: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("cantidad_puntos", nuevosPuntos) // Columna de puntos en la tabla
        }

        val resultado = db.update("Registros", values, "id_usuario = ?", arrayOf(idUsuario.toString()))
        db.close()

        return resultado > 0 // Retorna true si la actualización fue exitosa
    }
}