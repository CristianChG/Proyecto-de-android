package com.example.myapplication.data.network

import com.example.myapplication.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Proporciona las instancias necesarias para realizar las solicitudes de red.
 */
object NetworkModuleDI {

    /**
     * Fábrica de convertidores Gson para Retrofit.
     */
    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()

    /**
     * Configura y proporciona un cliente [OkHttpClient] con un interceptor para añadir cabeceras a las solicitudes.
     *
     * @return [OkHttpClient] configurado con el interceptor.
     */
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("X-Parse-Application-Id", Constants.APPLICATION_ID)
                    .header("X-Parse-REST-API-Key", Constants.API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    /**
     * Instancia de [Retrofit] configurada con la URL base y el cliente HTTP.
     */
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(getOkHttpClient())
        .addConverterFactory(gsonFactory)
        .build()

    /**
     * Servicio API para interactuar con los endpoints de eventos históricos.
     */
    val apiService: HistoricalAPIService = retrofit.create(HistoricalAPIService::class.java)
}
