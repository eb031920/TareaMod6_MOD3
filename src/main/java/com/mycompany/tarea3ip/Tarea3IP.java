
package com.mycompany.tarea3ip;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tarea3IP {

    static class Libro {
        private String titulo;
        private String autor;
        private int anioPublicacion;
        private String genero;
        private boolean disponible;

        public Libro(String titulo, String autor, int anioPublicacion, String genero) {
            this.titulo = titulo;
            this.autor = autor;
            this.anioPublicacion = anioPublicacion;
            this.genero = genero;
            this.disponible = true; 
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public boolean isDisponible() {
            return disponible;
        }

        public void prestar() {
            this.disponible = false;
        }

        public void devolver() {
            this.disponible = true;
        }

        
        public String toString() {
            return "Título: " + titulo + ", Autor: " + autor + ", Año: " + anioPublicacion + 
                   ", Género: " + genero + ", Disponible: " + (disponible ? "Sí" : "No");
        }
    }

    static class Biblioteca {
        private List<Libro> libros;

        public Biblioteca() {
            libros = new ArrayList<>();
        }

        public void agregarLibro(Libro libro) {
            libros.add(libro);
        }

        public List<Libro> buscarPorTitulo(String titulo) {
            List<Libro> resultados = new ArrayList<>();
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                    resultados.add(libro);
                }
            }
            return resultados;
        }

        public List<Libro> buscarPorAutor(String autor) {
            List<Libro> resultados = new ArrayList<>();
            for (Libro libro : libros) {
                if (libro.getAutor().equalsIgnoreCase(autor)) {
                    resultados.add(libro);
                }
            }
            return resultados;
        }

        public boolean prestarLibro(String titulo) {
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(titulo) && libro.isDisponible()) {
                    libro.prestar();
                    return true; 
                }
            }
            return false; 
        }

        public boolean devolverLibro(String titulo) {
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(titulo) && !libro.isDisponible()) {
                    libro.devolver();
                    return true; 
                }
            }
            return false; 
        }

        public void mostrarLibrosDisponibles() {
            for (Libro libro : libros) {
                if (libro.isDisponible()) {
                    System.out.println(libro);
                }
            }
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        
        biblioteca.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, "Ficción"));
        biblioteca.agregarLibro(new Libro("El Quijote", "Miguel de Cervantes", 1605, "Ficción"));
        biblioteca.agregarLibro(new Libro("El arte de la guerra", "Sun Tzu", -500, "Filosofía"));

        int opcion;
        do {
            System.out.println(" 1. Buscar libro por título");
            System.out.println(" 2. Buscar libro por autor");
            System.out.println(" 3. Prestar libro");
            System.out.println(" 4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título: ");
                    String tituloBusqueda = scanner.nextLine();
                    List<Libro> librosPorTitulo = biblioteca.buscarPorTitulo(tituloBusqueda);
                    librosPorTitulo.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Ingrese el autor: ");
                    String autorBusqueda = scanner.nextLine();
                    List<Libro> librosPorAutor = biblioteca.buscarPorAutor(autorBusqueda);
                    librosPorAutor.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Ingrese el título del libro a prestar: ");
                    String tituloPrestar = scanner.nextLine();
                    if (biblioteca.prestarLibro(tituloPrestar)) {
                        System.out.println("Libro prestado con éxito.");
                    } else {
                        System.out.println("Libro no disponible o no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el título del libro a devolver: ");
                    String tituloDevolver = scanner.nextLine();
                    if (biblioteca.devolverLibro(tituloDevolver)) {
                        System.out.println("Libro devuelto con éxito.");
                    } else {
                        System.out.println("Libro no encontrado o ya está disponible.");
                    }
                    break;
                case 5:
                    System.out.println("Libros disponibles:");
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
