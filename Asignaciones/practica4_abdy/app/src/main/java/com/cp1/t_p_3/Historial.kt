package com.cp1.t_p_3

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.database.Cursor
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.io.ByteArrayOutputStream
import java.io.File

class Historial : AppCompatActivity(), View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    @SuppressLint("MissingInflatedId")

    // Para exportar
    companion object {
        private const val REQUEST_WRITE_PERMISSION = 100
    }

    // db
    lateinit var dbHelper: TemperatureDBHelper
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TemperatureAdapter
    var temperatureList = mutableListOf<Temperature>()

    // swipe refresh layout
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        // Para exportar
        // Verificar y solicitar permiso de escritura en almacenamiento
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_PERMISSION
            )
        }

    val  conversor = findViewById<Button>(R.id.button3)
        conversor.setOnClickListener(this)

        // db

        dbHelper = TemperatureDBHelper(this)

        // Inicializar el RecyclerView
        recyclerView = findViewById(R.id.recyclerViewTemperatures)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer y cargar todas las temperaturas guardadas desde la base de datos
        loadTemperaturesFromDatabase()

        // Crear el adaptador con la lista de temperaturas
        adapter = TemperatureAdapter(temperatureList)
        recyclerView.adapter = adapter

        // Swipe Refresh Layour
        listView = findViewById(R.id.List_view)
        swipeRefreshLayout = findViewById(R.id.swipe_ly)
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    // Función para cargar las temperaturas desde SQLite
    private fun loadTemperaturesFromDatabase() {
        val cursor: Cursor = dbHelper.getAllTemperatures()

        if (cursor.moveToFirst()) {
            do {
                val type = cursor.getString(cursor.getColumnIndexOrThrow(TemperatureDBHelper.COLUMN_TYPE))
                val value = cursor.getDouble(cursor.getColumnIndexOrThrow(TemperatureDBHelper.COLUMN_VALUE))

                // Agregar el resultado a la lista de temperaturas
                temperatureList.add(Temperature(type, value))

            } while (cursor.moveToNext())
        }
        cursor.close()
    }

    //VOLVER AL MAIN ACTIVITY #NO TOCAR#
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button3 -> {
                //Codigo Interno
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onRefresh() {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "Refreshed", Toast.LENGTH_LONG).show()
            swipeRefreshLayout.isRefreshing = false
        }, 300)
    }

    //DESCARGA DE DATOS###########################
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_download -> {
                // Mostrar un diálogo para elegir el formato de exportación
                mostrarDialogoSeleccionFormato()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarDialogoSeleccionFormato() {
        val formatos = arrayOf("CSV", "XLS", "XLSX")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona el formato de descarga")
            .setItems(formatos) { dialog, which ->
                when (which) {
                    0 -> exportarDatos("csv")
                    1 -> exportarDatos("xls")
                    2 -> exportarDatos("xlsx")
                }
            }
        builder.create().show()
    }

    private fun exportarDatosCSV(): String {
        val sb = StringBuilder()
        sb.append("Columna1,Columna2,Columna3\n")
        sb.append("Dato1,Dato2,Dato3\n")
        // Agrega más filas de datos aquí
        return sb.toString()
    }

    private fun exportarDatos(formato: String) {
        when (formato) {
            "csv" -> {
                val csvData = exportarDatosCSV()
                guardarArchivo(csvData, "datos.csv")
            }
            "xls" -> {
                val xlsData = exportarDatosXLS()
                guardarArchivo(xlsData, "datos.xls")
            }
            "xlsx" -> {
                val xlsxData = exportarDatosXLSX()
                guardarArchivo(xlsxData, "datos.xlsx")
            }
        }
    }

    private fun exportarDatosXLS(): ByteArray {
        val workbook = HSSFWorkbook()
        val sheet = workbook.createSheet("Datos")
        val row = sheet.createRow(0)
        row.createCell(0).setCellValue("Columna1")
        row.createCell(1).setCellValue("Columna2")
        row.createCell(2).setCellValue("Columna3")

        val dataRow = sheet.createRow(1)
        dataRow.createCell(0).setCellValue("Dato1")
        dataRow.createCell(1).setCellValue("Dato2")
        dataRow.createCell(2).setCellValue("Dato3")

        val bos = ByteArrayOutputStream()
        workbook.write(bos)
        workbook.close()
        return bos.toByteArray()
    }

    private fun exportarDatosXLSX(): ByteArray {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Datos")
        val row = sheet.createRow(0)
        row.createCell(0).setCellValue("Columna1")
        row.createCell(1).setCellValue("Columna2")
        row.createCell(2).setCellValue("Columna3")

        val dataRow = sheet.createRow(1)
        dataRow.createCell(0).setCellValue("Dato1")
        dataRow.createCell(1).setCellValue("Dato2")
        dataRow.createCell(2).setCellValue("Dato3")

        val bos = ByteArrayOutputStream()
        workbook.write(bos)
        workbook.close()
        return bos.toByteArray()
    }

    private fun guardarArchivo(data: String, fileName: String) {
        val file = File(getExternalFilesDir(null), fileName)
        file.writeText(data)
        Toast.makeText(this, "Archivo $fileName guardado en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    }

    private fun guardarArchivo(data: ByteArray, fileName: String) {
        val file = File(getExternalFilesDir(null), fileName)
        file.writeBytes(data)
        Toast.makeText(this, "Archivo $fileName guardado en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_WRITE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permiso denegado. No se podrán guardar archivos.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}