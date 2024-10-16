package com.example.demo_celsius_activities_api_py

import android.util.Log
import com.google.gson.JsonObject
//Retrofit es una biblioteca de cliente HTTP para Android y Java que simplifica el consumo de servicios web RESTful.
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Interfaz que utilizara la API para el metodo POST y GET. En el caso del POST es ligeramente diferente,
//ya que utiliza el Body en vez de params, ya que la API hecho con FAST API lo cual es la formas más comunes
// es a través del body de la solicitud HTTP, especialmente para operaciones como crear o modificar recursos
interface ApiService {
    @POST("api/conversiones/temperatura") //debe ser igual que el nombre de la extension del POST API, en mi caso se llama resultado
    fun insertConversionResult(@Body body: JsonObject): Call<Void>

    @GET("api/conversiones/temperatura") //debe ser igual que el nombre de la extension de la GET API, en mi caso se llama resultados
    fun getAllResults(): Call<List<Result>> //se utiliza como un modelo de datos para representar los resultados de las conversiones de temperatura en la aplicación de Android,
    // y se integra con Retrofit para manejar la comunicación con la Fast API
}

//Los data classes son una forma conveniente de declarar clases que se utilizan principalmente para almacenar datos.
data class Result(
    val id: Int,
    val resultado: Double,
    val tipo: String,
)

class DatabaseHelper {

    private val apiService: ApiService

    init {
        apiService = createApiService()
    }

    // Método privado para crear el ApiService
    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            //.baseUrl("http://192.168.0.8/") //Si se tiene la API con un IP Estatico o un servidor externo se cambia este IP
            //.baseUrl("http://10.0.2.2/") // Para ejecutar aplicaciones en local (localhost) en el emulador AVD (ANDROID)
            .baseUrl("http://10.0.3.2/") // Para ejecutar aplicaciones en local (localhost) en el emulador Genymotion
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    // Insertar datos en la API
    fun addConversionResult(result: Double, conversion: String) {
        val jsonObject = JsonObject().apply {
            addProperty("resultado", result)
            addProperty("tipo", conversion)
        }

        apiService.insertConversionResult(jsonObject).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("DatabaseHelper", "Datos insertados correctamente en la API")
                } else {
                    Log.e("DatabaseHelper", "Error al insertar los datos en la API: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("DatabaseHelper", "Error al comunicarse con la API: ${t.message}")
            }
        })
    }

    // Consultar datos a través de la API
    fun getAllResults(callback: (List<Result>?) -> Unit) {
        apiService.getAllResults().enqueue(object : retrofit2.Callback<List<Result>> {
            override fun onResponse(call: Call<List<Result>>, response: retrofit2.Response<List<Result>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    Log.e("DatabaseHelper", "Error al obtener los datos de la API: ${response.code()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<List<Result>>, t: Throwable) {
                Log.e("DatabaseHelper", "Error al comunicarse con la API: ${t.message}")
                callback(null)
            }
        })
    }
}


