package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.DatosApi;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraELMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ******* Elige una opción *******
                    1 - Obtener Datos API
                    2 - Ver Top 10 Libros
                    3 - Ver Estadísticas
                    4 - Buscar Libros por Título
                    5 - Buscar Libros por X atributo
                    6 - Mostrar Libros Buscados
                                    
                    0 - Salir
                    ********************************
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    getDatosApi();
                    break;
                case 2:
                    topLibros();
                    break;
                case 3:
                    topLibros();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosApi getDatosApi() {
        var json = consumoApi.obtenerDatos(URL_BASE);
        System.out.println(json);
        DatosApi datosApi = conversor.convertirDatos(json, DatosApi.class);
        System.out.println(datosApi);
        return datosApi;
    }

    private void topLibros() {
        System.out.println("Top 10 libros más descargados");
        var json = consumoApi.obtenerDatos(URL_BASE);
        DatosApi datosApi = conversor.convertirDatos(json, DatosApi.class);
        datosApi.resultados().stream()
                .sorted(Comparator.comparing(DatosLibro::descargas).reversed())
                .limit(10)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);
    }

    private void getDatosLibro() {
        DatosApi datosApi = getDatosApi();
        List<DatosLibro> libros = new ArrayList<>();

        for (int i = 1; i <= datosApi.resultados().size(); i++) {
            var json = consumoApi.obtenerDatos(URL_BASE);
            DatosLibro datosLibro = conversor.convertirDatos(json, DatosLibro.class);
            libros.add(datosLibro);
        }
        libros.forEach(System.out::println);
    }
}




