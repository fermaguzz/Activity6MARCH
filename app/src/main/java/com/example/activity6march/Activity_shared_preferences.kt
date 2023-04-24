package com.example.activity6march

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Activity_shared_preferences : AppCompatActivity() {
    private lateinit var tvNombre: TextView
    private lateinit var tvEdad: TextView
    private lateinit var tvAltura: TextView
    private lateinit var tvMonedero: TextView

    private val NOMBRE_KEY = "nombre"
    private val EDAD_KEY = "edad"
    private val ALTURA_KEY = "altura"
    private val MONEDERO_KEY = "monedero"

    private var nombreShared: String = ""
    private var edad: Int = 0
    private var altura: Float = 0F
    private var monedero: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)

        val bnRegresar = findViewById<FloatingActionButton>(R.id.bnRegresar)

        getPreferences()

        bnRegresar.setOnClickListener {
            val i = Intent(this,Activity_lista_juegos::class.java)
            startActivity(i)
        }
    }

    private fun getPreferences() {
        tvNombre = findViewById(R.id.tvNombre)
        tvEdad = findViewById(R.id.tvEdad)
        tvAltura = findViewById(R.id.tvAltura)
        tvMonedero = findViewById(R.id.tvMonedero)


        if(TextUtils.isEmpty(nombreShared)) {
            Log.d("PRUEBA", "entro")
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombreShared = miSharedPreferences.getString(NOMBRE_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
            altura= miSharedPreferences.getFloat(ALTURA_KEY, 0F)
            monedero = miSharedPreferences.getFloat(MONEDERO_KEY, 0F)


            tvNombre.setText(nombreShared)
            tvEdad.setText(edad.toString())
            tvAltura.setText(altura.toString())
            tvMonedero.setText(monedero.toString())
        }

    }


}