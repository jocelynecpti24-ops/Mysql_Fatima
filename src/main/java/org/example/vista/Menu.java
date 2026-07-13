package org.example.vista;

import org.example.dao.AlumnoDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.example.dao.ProfesorDAO;
import org.example.modelo.Alumno;
import org.example.modelo.Profesor;

public class Menu {
    static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    static AlumnoDAO alumnoDAO = new AlumnoDAO();
    static ProfesorDAO profesorDAO = new ProfesorDAO();

    private static void inscribir() throws IOException {

        Alumno alumno = new Alumno();

        System.out.println("Numero expediente:");
        alumno.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.println("nombre:");
        alumno.setNombre(leer.readLine());

        System.out.println("curp:");
        alumno.setCurp(leer.readLine());

        System.out.println("grupo:");
        alumno.setGrupo(leer.readLine());

        System.out.println("promedio:");
        alumno.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.inscribirAlumno(alumno);
    }

    private static void inscribirProfe() throws IOException {
        Profesor profesor = new Profesor();

        System.out.println("Numero expediente:");
        profesor.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.println("nombre:");
        profesor.setNombre(leer.readLine());

        System.out.println("curp:");
        profesor.setCurp(leer.readLine());

        System.out.println("puesto:");
        profesor.setPuesto(leer.readLine());

        System.out.println("sueldo:");
        profesor.setSueldo(Double.parseDouble(leer.readLine()));

        profesorDAO.registrarProfesor(profesor);
    }

    private static void actualizar() throws IOException {
        Alumno alumno = new Alumno();

        System.out.println("Numero de expediente del alumno a actualizar:");
        alumno.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.println("Nuevo nombre:");
        alumno.setNombre(leer.readLine());

        System.out.println("Nuevo curp:");
        alumno.setCurp(leer.readLine());

        System.out.println("Nuevo grupo:");
        alumno.setGrupo(leer.readLine());

        System.out.println("Nuevo promedio:");
        alumno.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.actualizar(alumno);
    }

    private static void modificarProfesor() throws IOException {
        Profesor profesor = new Profesor();

        System.out.println("Numero de expediente del profesor a modificar:");
        profesor.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.println("Nuevo nombre:");
        profesor.setNombre(leer.readLine());

        System.out.println("Nuevo curp:");
        profesor.setCurp(leer.readLine());

        System.out.println("Nuevo puesto:");
        profesor.setPuesto(leer.readLine());

        System.out.println("Nuevo sueldo:");
        profesor.setSueldo(Double.parseDouble(leer.readLine()));

        profesorDAO.modificarProfesor(profesor);
    }

    private static void darBaja() throws IOException {
        System.out.println("Ingresa el numero de expediente del alumno que deseas dar de baja:");
        int expediente = Integer.parseInt(leer.readLine());
        alumnoDAO.eliminarAlumno(expediente);
    }

    private static void darBajaProfesor() throws IOException {
        System.out.println("Ingresa el numero de expediente del profesor que deseas dar de baja:");
        int expediente = Integer.parseInt(leer.readLine());
        profesorDAO.eliminarProfesor(expediente);
    }

    private static void buscar() throws IOException {
        System.out.println("¿Qué deseas buscar?");
        System.out.println("1. Alumno");
        System.out.println("2. Profesor");
        System.out.print("Selecciona una opción: ");
        int tipo = Integer.parseInt(leer.readLine());

        if (tipo == 1) {
            System.out.println("Ingresa el numero de expediente del alumno a buscar:");
            int expediente = Integer.parseInt(leer.readLine());
            Alumno alEncontrado = alumnoDAO.buscarAlumnoPorExpediente(expediente);

            if (alEncontrado != null) {
                System.out.println("\n[ Alumno Encontrado ]");
                System.out.println("Expediente: " + alEncontrado.getNumExpediente());
                System.out.println("Nombre: " + alEncontrado.getNombre());
                System.out.println("CURP: " + alEncontrado.getCurp());
                System.out.println("Grupo: " + alEncontrado.getGrupo());
                System.out.println("Promedio: " + alEncontrado.getPromedio());
            }
        } else if (tipo == 2) {
            System.out.println("Ingresa el numero de expediente del profesor a buscar:");
            int expediente = Integer.parseInt(leer.readLine());
            Profesor profEncontrado = profesorDAO.buscarProfesorPorExpediente(expediente);

            if (profEncontrado != null) {
                System.out.println("\n[ Profesor Encontrado ]");
                System.out.println(profEncontrado.toString()); // Muestra los datos usando tu toString()
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private static void mostrar(){
        ArrayList<Alumno> alumnos = alumnoDAO.extraerAlumnos();
        System.out.println("--------lista de alumnos--------");
        for(Alumno alumno : alumnos){
            System.out.println(alumno);
        }
    }

    private static void mostrarProfe(){
        ArrayList<Profesor> profesores = profesorDAO.extraerProfesores();
        System.out.println("--------lista de profesores--------");
        for(Profesor profesor : profesores){
            System.out.println(profesor);
        }
    }

    public static void menu() throws IOException {
        int opcion = 0;


        while (opcion != 11){
            System.out.println("\n=== MENU DE LA UNIVERSIDAD ===");
            System.out.println("1. Inscribir alumno");
            System.out.println("2. Mostrar alumnos");
            System.out.println("3. Actualizar un alumno");
            System.out.println("4. Dar de baja un alumno");
            System.out.println("5. Buscar (Alumno / Profesor)");
            System.out.println("6. Inscribir profesor");
            System.out.println("7. Mostrar profesores");
            System.out.println("8. Modificar un profesor");
            System.out.println("9. Dar de baja un profesor");
            System.out.println("10. Mostrar Todos los Profesores");
            System.out.println("11. Salir");
            System.out.println("Elige una opcion:");

            opcion = Integer.parseInt(leer.readLine());
            switch (opcion){
                case 1: inscribir(); break;
                case 2: mostrar(); break;
                case 3: actualizar(); break;
                case 4: darBaja(); break;
                case 5: buscar(); break;
                case 6: inscribirProfe(); break;
                case 7: mostrarProfe(); break;
                case 8: modificarProfesor(); break;
                case 9: darBajaProfesor(); break;
                case 10: mostrarProfe(); break;
                case 11:
                    System.out.println("Has salido de la aplicacion.");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
    }
}