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
}