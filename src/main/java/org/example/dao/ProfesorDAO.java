package org.example.dao;

import org.example.config.Coleccion;
import org.example.modelo.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfesorDAO {

    public boolean registrarProfesor(Profesor profesor) {
        boolean registrado = false;
        String sql = "INSERT INTO maestro (numExpediente, nombre, curp, puesto, sueldo) VALUES(?,?,?,?,?)";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, profesor.getNumExpediente());
            stm.setString(2, profesor.getNombre());
            stm.setString(3, profesor.getCurp());
            stm.setString(4, profesor.getPuesto());
            stm.setDouble(5, profesor.getSueldo());

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                registrado = true;
                System.out.println("Profesor registrado correctamente en la base de datos.");
            }
        } catch (SQLException err) {
            System.out.println("Error al registrar al profesor: " + err.getMessage());
        }

        return registrado;
    }


    public Profesor buscarProfesorPorExpediente(int numExpediente) {
        Profesor profesor = null;
        String sql = "SELECT * FROM maestro WHERE numExpediente = ?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, numExpediente);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                profesor = new Profesor();
                profesor.setNumExpediente(rs.getInt("numExpediente"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setCurp(rs.getString("curp"));
                profesor.setPuesto(rs.getString("puesto"));
                profesor.setSueldo(rs.getDouble("sueldo"));
            } else {
                System.out.println("No se encontró ningún profesor con el expediente: " + numExpediente);
            }
        } catch (SQLException err) {
            System.out.println("Error al buscar al profesor: " + err.getMessage());
        }

        return profesor;
    }

    public ArrayList<Profesor> extraerProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<Profesor>();
        String sql = "SELECT * FROM maestro";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Profesor profesor = new Profesor();
                profesor.setNumExpediente(rs.getInt("numExpediente"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setCurp(rs.getString("curp"));
                profesor.setPuesto(rs.getString("puesto"));
                profesor.setSueldo(rs.getDouble("sueldo"));
                profesores.add(profesor);
            }
        } catch (SQLException err) {
            System.out.println("Error al extraer profesores: " + err.getMessage());
        }

        return profesores;
    }

    public boolean modificarProfesor(Profesor profesor) {
        boolean modificado = false;
        String sql = "UPDATE maestro SET nombre = ?, curp = ?, puesto = ?, sueldo = ? WHERE numExpediente = ?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setString(1, profesor.getNombre());
            stm.setString(2, profesor.getCurp());
            stm.setString(3, profesor.getPuesto());
            stm.setDouble(4, profesor.getSueldo());
            stm.setInt(5, profesor.getNumExpediente());

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                modificado = true;
                System.out.println("Profesor modificado correctamente en la base de datos.");
            } else {
                System.out.println("No se encontró ningún profesor con el número de expediente: " + profesor.getNumExpediente());
            }

        } catch (SQLException err) {
            System.out.println("Error al modificar al profesor: " + err.getMessage());
        }

        return modificado;
    }

    public boolean eliminarProfesor(int numExpediente) {
        boolean eliminado = false;
        String sql = "DELETE FROM maestro WHERE numExpediente = ?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, numExpediente);

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
                System.out.println("Profesor eliminado correctamente de la base de datos.");
            } else {
                System.out.println("No se encontró ningún profesor con el expediente: " + numExpediente);
            }
        } catch (SQLException err) {
            System.out.println("Error al eliminar al profesor: " + err.getMessage());
        }

        return eliminado;
    }
}
//GETERS SETER CLASES APSTRACTAS HERENCIA P