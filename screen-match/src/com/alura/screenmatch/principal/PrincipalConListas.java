package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalConListas {
    public static void main(String[] args) {

        var miPelicula = new Pelicula("Upgrade", 2018);
        miPelicula.evalua(9);
        var otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(6);

        Serie Mentalist = new Serie("Mentalist", 2008);

        Pelicula p1 = miPelicula;

        List<Titulo> lista = new LinkedList<>();
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(Mentalist);


        lista.forEach(System.out::println);

        ArrayList<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penelope Cruz");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo Darin");

        Collections.sort(listaDeArtistas);
        System.out.println("Lista de Artistas ordernada" + listaDeArtistas);

        Collections.sort(lista);
        System.out.println("Lista ordenada por nombre: "+ lista);

        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista ordenada por fecha: " + lista);
    }
}
