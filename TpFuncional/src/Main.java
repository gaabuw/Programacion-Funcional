import entidades.Alumno;
import entidades.Empleado;
import entidades.Libro;
import entidades.Producto;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Caso Practico 1
        // Creamos lista de Alumnos
        System.out.println("-------- Caso Practico 1 ----------");
        List<Alumno> alumnosLista = Arrays.asList(
                new Alumno("Jose", 7, "analisis"),
                new Alumno("Mario", 6, "algebra"),
                new Alumno("Mariana", 3, "analisis"),
                new Alumno("Roberto", 10, "analisis"),
                new Alumno("Sofia", 7, "algebra"),
                new Alumno("Cristian", 8, "algebra")
        );

        // 1)
        List<String> alumnosAprobados = alumnosLista.stream()
                .filter(alumno -> alumno.getNota() >= 7.0)
                .map(alumno -> alumno.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(alumnosAprobados);
        System.out.println("");

        // 2)
        double notatotal = alumnosLista.stream()
                .map(alumno -> (double) alumno.getNota())
                .reduce(0.0, Double::sum);

        double promedioGeneral = 0.0;
        promedioGeneral = notatotal / alumnosLista.size();
        System.out.println("Promedio general: " + promedioGeneral );
        System.out.println("");

        // 3)
        Map<String, List<Alumno>> porCurso = alumnosLista.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));
        System.out.println(porCurso);
        System.out.println("");

        // 4)
        List<Double> mejoresPromedios = alumnosLista.stream()
                .map(alumno -> (double) alumno.getNota())
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Los mejores 3 promedios: " + mejoresPromedios);


        //Caso Practico 2
        // Creamos Lista de productos
        System.out.println("--------- Caso Practico 2 ---------");
        List<Producto> listaProductos = Arrays.asList(
                new Producto("Harina", "Alimento", 120.0, 30),
                new Producto("Papel", "Higiene", 100.0, 30),
                new Producto("Cuaderno", "Libreria", 90.0, 45),
                new Producto("Aceite", "Alimento", 140.0, 20),
                new Producto("Lapicera","Libreria", 50.0, 200),
                new Producto("Jabon", "Higiene", 70.0, 70)
        );

        // 1)
        List<Producto> productoMayorA100 = listaProductos.stream()
                .filter(producto -> producto.getPrecio() > 100.0)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .collect(Collectors.toList());

        System.out.println("\nProductos con un precio mayor a 100: ");
        productoMayorA100.forEach(System.out::println);

        // 2)
        Map<String, Integer> productosPorCategoria = listaProductos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,
                        Collectors.summingInt(Producto::getStock)));

        System.out.println("\nStock total por categoria:");
        System.out.println(productosPorCategoria);

        // 3)
        String unionProductos = listaProductos.stream()

                .map(producto -> producto.getNombre() + " - " + producto.getPrecio())
                .collect(Collectors.joining(", "));

        System.out.println("\n[Producto - Precio]: ");
        System.out.println(unionProductos);

        // 4)
        Map<String, Double> precioPromedioPorCategoria = listaProductos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));

        System.out.println("\nPrecio promedio por cada Categoria");
        System.out.println(precioPromedioPorCategoria);


        //Caso Practico 3
        // Creamos lista de libros
        System.out.println("--------- Caso Practico 3 ---------");
        List<Libro> listaLibros = Arrays.asList(
                new Libro("El Aleph", "Borges", 250, 150.0),
                new Libro("Cien Años de Soledad", "Garcia Marquez", 450, 200.0),
                new Libro("Rayuela", "Cortazar", 500, 250.0),
                new Libro("Ficciones", "Borges", 200, 130.0)
        );

        // 1)
        List<String>  listaTitulos = listaLibros.stream()
                .filter(libro -> libro.getPaginas() > 300)
                .map(Libro::getTitulo)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\nLista de libros con más de 300 páginas: ");
        listaTitulos.forEach(System.out::println);

        // 2)
        double promedioPaginas = listaLibros.stream()
                .collect(Collectors.averagingInt(Libro::getPaginas));

        System.out.println("\nPromedio de páginas de todos los libros: " + promedioPaginas);

        // 3)
        Map<String, Long> autorXlibros = listaLibros.stream()
                .collect(Collectors.groupingBy(Libro::getAutor,
                        Collectors.counting()
                        ));

        System.out.println("\nCantidad de libros por Autor:");
        System.out.println(autorXlibros);

        // 4)
        Optional<Libro> libroMasCaro = listaLibros.stream()
                .max(Comparator.comparing(Libro::getPrecio));

        System.out.println("\nLibro más caro: "+ libroMasCaro.get());


        //Caso Practico 4
        // Creamos lista de empleados
        System.out.println("--------- Caso Practico 4 ---------");
        List<Empleado> listaEmpleados = Arrays.asList(
                new Empleado("Rogelio", "Las heras", 2500.0, 24),
                new Empleado("Mariano", "Godoy Cruz", 4500.0, 29),
                new Empleado("Enrique", "Godoy Cruz", 1600.0, 30),
                new Empleado("Eustaquio", "Guaymallen", 2000.0, 24)
        );

        // 1)
        List<Empleado> empleadosSalarioMayor = listaEmpleados.stream()
                .filter(e -> e.getSalario() > 2000.0)
                .sorted(Comparator.comparing(Empleado::getSalario).reversed())
                .collect(Collectors.toList());

        System.out.println("\nEmpleados con salario > 2000: ");
        empleadosSalarioMayor.forEach(System.out::println);

        // 2)
        double salarioPromedio = listaEmpleados.stream()
                .map(empleado -> empleado.getSalario())
                .reduce(0.0, Double::sum);

        double salarioPromedioGeneral = 0.0;
        salarioPromedioGeneral = salarioPromedio / listaEmpleados.size();
        System.out.println("\nEl salario promedio general es: $" + salarioPromedioGeneral);

        // 3)
        Map<String, Double> salarioXdepa = listaEmpleados.stream()
                .collect(Collectors.groupingBy(Empleado::getDepartamento,
                        Collectors.summingDouble(Empleado::getSalario)
                        ));

        System.out.println("\nSalario total por departamento:");
        System.out.println(salarioXdepa);

        // 4)
        List<String> masJovenes = listaEmpleados.stream()
                .sorted(Comparator.comparing(Empleado::getEdad))
                .limit(2)
                .map(Empleado::getNombre)
                .collect(Collectors.toList());

        System.out.println("\nLos 2 empleados más jovenes:");
        masJovenes.forEach(System.out::println);




    }
}