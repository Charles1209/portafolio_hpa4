package com.cp1.t_p_3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// Clase para manejar la base de datos de temperaturas
class TemperatureDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "temperature.db"
        private const val DATABASE_VERSION = 1

        // Nombre de la tabla y columnas
        const val TABLE_NAME = "temperatures"
        const val COLUMN_ID = "id"
        const val COLUMN_TYPE = "type"  // Tipo de temperatura (Celsius, Fahrenheit, Rankine)
        const val COLUMN_VALUE = "value"  // Número de temperatura
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_TYPE TEXT, "
                + "$COLUMN_VALUE REAL)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Método PUT: Insertar una nueva temperatura
    fun insertTemperature(type: String, value: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TYPE, type)
            put(COLUMN_VALUE, value)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Método GET: Obtener todas las temperaturas
    fun getAllTemperatures(): Cursor {
        val db = readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME ORDER BY 1 DESC",
            null
        )
    }
}