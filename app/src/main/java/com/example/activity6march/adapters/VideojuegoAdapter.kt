package com.example.activity6march

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


class VideojuegoAdapter(videojuegos: ArrayList<Videojuego>, contexto : Context) :
 RecyclerView.Adapter<VideojuegoAdapter.ContenedorDeVista> (){
    var innerVideojuegos: ArrayList<Videojuego> = videojuegos
    var innerContext: Context = contexto

    inner class ContenedorDeVista(view: View):
            RecyclerView.ViewHolder(view), View.OnClickListener {
                val tvNombre : TextView
                val tvPrecio : TextView
                val tvConsola : TextView
                val tvClasificacion : TextView
                val ivFoto : ImageView
                val bnComprar : Button

                init{
                    tvNombre = view.findViewById(R.id.tvNombre)
                    tvPrecio = view.findViewById(R.id.tvPrecio)
                    tvConsola = view.findViewById(R.id.tvConsola)
                    tvClasificacion = view.findViewById(R.id.tvClasificacion)
                    ivFoto = view.findViewById(R.id.ivFoto)
                    bnComprar = view.findViewById(R.id.bnComprar)

                    bnComprar.setOnClickListener(this)
                }

                override fun onClick(p0: View?) {
                    val miSharedPreferences = innerContext.getSharedPreferences("PERSISTENCIA", AppCompatActivity.MODE_PRIVATE)
                    val edad = miSharedPreferences.getInt("edad", 4)

                    if(absoluteAdapterPosition >= 0) {
                        val videojuego: Videojuego = innerVideojuegos.get(absoluteAdapterPosition)
                        if (edad <= 5 && (videojuego.clasificacion=="R" || videojuego.clasificacion == "T")) {
                            val toast = Toast.makeText(innerContext, "No puedes comprar", Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val toast = Toast.makeText(innerContext, "Comprado", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_game, parent, false)

        return ContenedorDeVista(view)
    }


    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val videojuego: Videojuego = innerVideojuegos.get(position)

        holder.tvNombre.text = videojuego.nombre
        holder.tvConsola.text = videojuego.consola
        holder.tvClasificacion.text = videojuego.clasificacion
        holder.tvPrecio.text = videojuego.precio.toString()
        holder.ivFoto.setImageResource(videojuego.imagen)

    }

    override fun getItemCount(): Int {
        return innerVideojuegos.size
    }
}