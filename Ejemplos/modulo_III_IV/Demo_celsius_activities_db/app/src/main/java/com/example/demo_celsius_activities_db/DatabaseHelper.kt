package com.example.demo_celsius_activities_db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONVERSION_TABLE = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY," +
                "$COLUMN_RESULT REAL)")
        db.execSQL(CREATE_CONVERSION_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //Insertar datos en la db
    fun addConversionResult(result: Double) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_RESULT, result)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    //Select bajo la modalidad de ver todos los datos en la db
    @SuppressLint("Range")
    /** fun getAllResults(): List<Double> {
        val resultList = mutableListOf<Double>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_RESULT FROM $TABLE_NAME", null)
        cursor.use {
            while (cursor.moveToNext()) {
                val result = cursor.getDouble(cursor.getColumnIndex(COLUMN_RESULT))
                resultList.add(result)
            }
        }
        cursor.close()
        return resultList
    } **/
    fun getAllResults(): List<Pair<Int, Double>> {
        val resultList = mutableListOf<Pair<Int, Double>>() // Cambiar el tipo de la lista
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_ID, $COLUMN_RESULT FROM $TABLE_NAME", null) // Modificar la consulta SQL
        cursor.use {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)) // Obtener el ID
                val result = cursor.getDouble(cursor.getColumnIndex(COLUMN_RESULT))
                resultList.add(Pair(id, result))
            }
        }
        cursor.close()
        return resultList
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "conversiones.db"
        private const val TABLE_NAME = "conversion_temperatura"
        private const val COLUMN_ID = "id"
        private const val COLUMN_RESULT = "resultado"
    }
}
