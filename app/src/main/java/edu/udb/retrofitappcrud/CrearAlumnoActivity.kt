package edu.udb.retrofitappcrud

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CrearAlumnoActivity : AppCompatActivity() {

    private lateinit var tituloEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var tipoEditText: EditText
    private lateinit var enlaceEditText: EditText
    private lateinit var imagenEditText: EditText
    private lateinit var crearButton: Button

    // Obtener las credenciales de autenticación
    var auth_username = ""
    var auth_password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alumno)

        // Obtención de datos que envia actividad anterior
        val datos: Bundle? = intent.getExtras()
        if (datos != null) {
            auth_username = datos.getString("auth_username").toString()
            auth_password = datos.getString("auth_password").toString()
        }

        tituloEditText = findViewById(R.id.editTextTitulo)
        descripcionEditText = findViewById(R.id.editTextDescripcion)
        tipoEditText = findViewById(R.id.editTextTipo)
        enlaceEditText = findViewById(R.id.editTextEnlace)
        imagenEditText = findViewById(R.id.editTextImagen)
        crearButton = findViewById(R.id.btnGuardar)

        crearButton.setOnClickListener {
            val titulo = tituloEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val tipo = tipoEditText.text.toString()
            val enlace = enlaceEditText.text.toString()
            val imagen = imagenEditText.text.toString()

            val alumno = Alumno(0,titulo, descripcion, tipo, enlace, imagen)
            Log.e("API", "auth_username: $auth_username")
            Log.e("API", "auth_password: $auth_password")

            // Crea una instancia de Retrofit con el cliente OkHttpClient
            val retrofit = Retrofit.Builder()
                .baseUrl("https://6646905851e227f23aaf4374.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Crea una instancia del servicio que utiliza la autenticación HTTP básica
            val api = retrofit.create(AlumnoApi::class.java)

            api.crearAlumno(alumno).enqueue(object : Callback<Alumno> {
                override fun onResponse(call: Call<Alumno>, response: Response<Alumno>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@CrearAlumnoActivity, "Recurso creado exitosamente", Toast.LENGTH_SHORT).show()
                        val i = Intent(getBaseContext(), MainActivity::class.java)
                        startActivity(i)
                    } else {
                        val error = response.errorBody()?.string()
                        Log.e("API", "Error crear alumno: $error")
                        Toast.makeText(this@CrearAlumnoActivity, "Error al crear el recurso", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<Alumno>, t: Throwable) {
                    Toast.makeText(this@CrearAlumnoActivity, "Error al crear el recurso", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
