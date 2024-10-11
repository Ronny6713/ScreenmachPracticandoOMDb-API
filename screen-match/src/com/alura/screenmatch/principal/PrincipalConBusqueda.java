package com.alura.screenmatch.principal;

import com.alura.screenmatch.excepcion.FalloConvertirDuracionException;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner teclado = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true){

            System.out.println("Escriba el nombre de una pelicula: ");
            var busqueda = teclado.nextLine();

            if (busqueda.equalsIgnoreCase("salir")){
                break;
            }

            String nombrePelicula = URLEncoder.encode(busqueda, "UTF-8");
            String direccion = "http://www.omdbapi.com/?t=" + nombrePelicula + "&apikey=feb3d4a8";

            try {

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();

                HttpResponse<String> response = client
                        .send(request, BodyHandlers.ofString());

                System.out.println(response.body());

                String json = response.body();

                TituloOmdb miTituloOmd = gson.fromJson(json, TituloOmdb.class);
                System.out.println(miTituloOmd);

                Titulo miTitulo = new Titulo(miTituloOmd);
                System.out.println("Datos de mi pelicula: " + miTitulo);


                titulos.add(miTitulo);
            }catch (NumberFormatException e){
                System.out.println("Ocurrio un error : ");
                System.out.println(e.getMessage());
            }catch (FalloConvertirDuracionException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("mis titulos son: " + titulos);

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(titulos));
        escritura.close();
        System.out.println("El programa finalizo");
    }
}
