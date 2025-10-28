package entidades;

public class Alumno {
    private String nombre;
    private int nota;
    private String curso;

    public Alumno(String nombre, int nota, String curso) {
        this.nombre = nombre;
        this.nota = nota;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

    public String getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
