package com.example.estados.api

import com.example.estados.model.Cidade
import com.example.estados.model.Estado
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EstadoService {

    @GET("estados")
    fun getAll(): Call<List<Estado>>

    @GET("estados/{UF}/municipios")
    fun getMunicipios(@Path("UF") uf: String): Call<List<Cidade>>
}