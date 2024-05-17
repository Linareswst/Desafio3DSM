package edu.udb.retrofitappcrud

import retrofit2.Call
import retrofit2.http.*

interface AlumnoApi {

    @GET("desafio3/recursos/recursos")
    fun obtenerAlumnos(): Call<List<Alumno>>

    @GET("desafio3/recursos/recursos/{id}")
    fun obtenerAlumnoPorId(@Path("id") id: Int): Call<Alumno>

    @POST("desafio3/recursos/recursos")
    fun crearAlumno(@Body alumno: Alumno): Call<Alumno>

    @PUT("desafio3/recursos/recursos/{id}")
    fun actualizarAlumno(@Path("id") id: Int, @Body alumno: Alumno): Call<Alumno>

    @DELETE("desafio3/recursos/recursos/{id}")
    fun eliminarAlumno(@Path("id") id: Int): Call<Void>
}
