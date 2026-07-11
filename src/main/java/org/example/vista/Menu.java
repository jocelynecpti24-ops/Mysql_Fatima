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
    static Alumno alumno = new Alumno();
    static Profesor profesor = new Profesor();
    static ProfesorDAO profesorDAO = new ProfesorDAO();

    private static void inscribir() throws IOException {
        System.out.println("nuemeo expediente");
        alumno.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.println("nombre");
        alumno.setNombre(leer.readLine());

        System.out.println("curp");
        alumno.setCurp(leer.readLine());

        System.out.println("grupo");
        alumno.setGrupo(leer.readLine());

        System.out.println("promedio");
        alumno.setPromedio(Double.parseDouble(leer.readLine()));

        alumnoDAO.inscribirAlumno(alumno);
    }

    private static void inscribirProfe() throws IOException {
        System.out.println("nuemeo expediente");
        profesor.setNumExpediente(Integer.parseInt(leer.readLine()));

        System.out.println("nombre");
        profesor.setNombre(leer.readLine());

        System.out.println("curp");
        profesor.setCurp(leer.readLine());

        System.out.println("puesto");
        profesor.setPuesto(leer.readLine());

        System.out.println("sueldo");
        profesor.setSueldo(Double.parseDouble(leer.readLine()));

        profesorDAO.registrarProfesor(profesor);
    }


    private static void actualizar() throws IOException {
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

    private static void darBaja(){}
    private static void buscar(){}

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
        int salir = 0;
        while (salir != 6){
            System.out.println("1. incribri alumno");
            System.out.println("2. mostra alumno");
            System.out.println("3. actuaizar un alumno");
            System.out.println("4. dar de baja un alumno");
            System.out.println("5. buscar un alumno");
            System.out.println("6. salir");
            System.out.println("7. inscribir profesor");
            System.out.println("8. mostrar profesores");
            System.out.println("elige una opcion");
            salir = Integer.parseInt(leer.readLine());
            switch (salir){
                case 1: inscribir(); break;
                case 2: mostrar(); break;
                case 3: actualizar(); break;
                case 4: darBaja(); break;
                case 5: buscar(); break;
                case 6:
                    System.out.println("has salido de la aplicacion");
                    break;
                case 7: inscribirProfe(); break;
                case 8: mostrarProfe(); break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        }
    }
}
