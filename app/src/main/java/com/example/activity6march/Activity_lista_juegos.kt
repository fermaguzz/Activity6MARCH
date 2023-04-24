package com.example.activity6march

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Activity_lista_juegos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_juegos)

        val juegos = FakerVideojuego().getVideogames()
        val recycler = findViewById<RecyclerView>(R.id.recyclerJuegos)
        val fab = findViewById<FloatingActionButton>(R.id.fabJuegos)
        val bnOrientation = findViewById<Button>(R.id.bnOrientacion)
        val bnDosColumnas = findViewById<Button>(R.id.bnDosColumnas)
        val bnTresColumnas = findViewById<Button>(R.id.bnTresColumnas)

        var isPortrait = true

        // este se usa si nuestro layout fuera de grid
        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )


        // LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)

        //  usamos el administrador de layouts para utilizar nuestro layout de juego para el recycler view con Linear layout
        recycler.layoutManager = linearLayoutManager
        recycler.adapter = VideojuegoAdapter(juegos, this)



        bnOrientation.setOnClickListener {
            // if isPortrait true, change to Landscape
            requestedOrientation = if (isPortrait) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                // else change to Portrait
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            // opposite the value of isPortrait
            isPortrait = !isPortrait
        }

        bnDosColumnas.setOnClickListener {
            val gridLayoutManager = GridLayoutManager(this, 2)
            recycler.layoutManager = gridLayoutManager
        }

        bnTresColumnas.setOnClickListener {
            val gridLayoutManager = GridLayoutManager(this, 3)
            recycler.layoutManager = gridLayoutManager
        }

        fab.setOnClickListener {
            val i = Intent(this, Activity_shared_preferences::class.java)
            startActivity(i)
        }
    }

}