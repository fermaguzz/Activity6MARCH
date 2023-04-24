package com.example.activity6march



class FakerVideojuego {

    fun getVideogames (): ArrayList<Videojuego> {
        var videogames : ArrayList<Videojuego>
        videogames = arrayListOf<Videojuego>()

        var videojuego = Videojuego(1,"Mario bros",1000F,"PC", "E+", R.drawable.mario)


        videogames.add(videojuego)
        videogames.add(Videojuego(2, "Black ops",1600F,"Playstation", "T",R.drawable.bo2))
        videogames.add(Videojuego(3, "Dying Light",1600F,"Xbox","E+", R.drawable.dying))
        videogames.add(Videojuego(4, "Modern Warfare 2",1500F,"Playstation", "T", R.drawable.modern2))
        videogames.add(Videojuego(5, "Rainbow six",1500F,"Xbox", "E+", R.drawable.ranibow))
        videogames.add(Videojuego(6, "The last of us",1800F,"PC","R" , R.drawable.thelastofus))

        return videogames
    }

}