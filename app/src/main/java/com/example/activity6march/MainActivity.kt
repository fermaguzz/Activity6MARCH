package com.example.activity6march

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tvBienvenido: TextView
    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etAltura: EditText
    private lateinit var etMonedero: EditText
    private lateinit var bnGuardar: Button
    private lateinit var switchPreferencias: Switch

    private val NOMBRE_KEY = "nombre"
    private val EDAD_KEY = "edad"
    private val ALTURA_KEY = "altura"
    private val MONEDERO_KEY = "monedero"
    private val SWITCH_KEY = "switch_estado"
    private val NOMBRE_INSTANCIA = "nombre_instancia"

    private var nombre: String = ""
    private var edad: Int = 0
    private var altura: Float = 0F
    private var monedero: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("PREFERENCIAS", "onCreate")
        setContentView(R.layout.activity_main)

        var actionBar: ActionBar?
        actionBar = supportActionBar
        var colorDrawable: ColorDrawable
        colorDrawable = ColorDrawable(Color.parseColor("#FF018786"))
        actionBar!!.setBackgroundDrawable(colorDrawable)

        inicializarVistas()

        Log.d("PREFERENCIAS", savedInstanceState?.getString(NOMBRE_KEY).toString())
        Log.d("PREFERENCIAS", savedInstanceState?.getInt(EDAD_KEY).toString())
        Log.d("PREFERENCIAS", savedInstanceState?.getFloat(ALTURA_KEY).toString())
        Log.d("PREFERENCIAS", savedInstanceState?.getFloat(MONEDERO_KEY).toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("PREFERENCIAS", "onSaveInstanceState")
        outState.putString(NOMBRE_KEY, nombre )
        outState?.run {
            putString(NOMBRE_KEY, nombre)
            putInt(EDAD_KEY, edad)
            putFloat(ALTURA_KEY, altura)
            putFloat(MONEDERO_KEY, monedero)
        }
        super.onSaveInstanceState(outState)

    }

    override fun onResume() {
        Log.d("PREFERENCIAS", "onResume")
        if(TextUtils.isEmpty(nombre)){
            val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
            nombre = miSharedPreferences.getString(NOMBRE_KEY, "").toString()
            edad = miSharedPreferences.getInt(EDAD_KEY, 0)
            altura= miSharedPreferences.getFloat(ALTURA_KEY, 0F)
            monedero = miSharedPreferences.getFloat(MONEDERO_KEY, 0F)

        }

        tvBienvenido.text = "Bienvenido ${nombre}"
        etNombre.setText(nombre)
        etEdad.setText(edad.toString())
        etAltura.setText(altura.toString())
        etMonedero.setText(monedero.toString())

        super.onResume()
    }

    override fun onStart() {
        Log.d("PREFERENCIAS", "onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("PREFERENCIAS", "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("PREFERENCIAS", "onDestroy")
        super.onDestroy()
    }

    private fun cambiarTextoBienvenida(nombre: String) {
        if (!TextUtils.isEmpty(nombre)) {
            tvBienvenido.text = "Bienvenido " + nombre
        }
    }

    private fun inicializarVistas() {
        tvBienvenido = findViewById(R.id.tvBienvenido)
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etAltura = findViewById(R.id.etAltura)
        etMonedero= findViewById(R.id.etMonedero)
        bnGuardar = findViewById(R.id.bnGuardar)
        switchPreferencias = findViewById(R.id.switchPreferencias)

        bnGuardar.setOnClickListener {
            if (switchPreferencias.isChecked()) {
                nombre = etNombre.text.toString()
                edad = Integer.valueOf(etEdad.text.toString())
                altura = etAltura.text.toString().toFloat()
                monedero = etMonedero.text.toString().toFloat()
                cambiarTextoBienvenida(nombre)
                val miSharedPreferences = getSharedPreferences("PERSISTENCIA", MODE_PRIVATE)
                val editor = miSharedPreferences.edit()
                editor.putString(NOMBRE_KEY, nombre)
                editor.putInt(EDAD_KEY, edad)
                editor.putFloat(ALTURA_KEY, altura)
                editor.putFloat(MONEDERO_KEY, monedero)
                editor.apply()
            }
            val i = Intent(this,Activity_lista_juegos::class.java)
            startActivity(i)
        }



    }

}