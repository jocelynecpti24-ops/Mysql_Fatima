package org.example.dao;

import org.example.config.Coleccion;
import org.example.modelo.Alumno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlumnoDAO {

    public boolean inscribirAlumno(Alumno alumno) {
        boolean inscrito = false;
        String sql = "INSERT INTO alumnos VALUES(?,?,?,?,?)";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {
            stm.setInt(1, alumno.getNumExpediente());
            stm.setString(2, alumno.getNombre());
            stm.setString(3, alumno.getCurp());
            stm.setString(4, alumno.getGrupo());
            stm.setDouble(5, alumno.getPromedio());

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                inscrito = true;
                System.out.println("Alumno inscrito correctamente en la base de datos.");
            }
        } catch (SQLException err) {
            System.out.println("Error al inscribir el alumno: " + err.getMessage());
        }
        return inscrito;
    }

    public Alumno buscarAlumnoPorExpediente(int numExpediente) {
        Alumno alumno = null;
        String sql = "SELECT * FROM alumnos WHERE numExpediente = ?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, numExpediente);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                alumno = new Alumno();
                alumno.setNumExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setGrupo(rs.getString("grupo"));
                alumno.setPromedio(rs.getDouble("promedio"));
            }
        } catch (SQLException err) {
            System.out.println("Error al buscar el alumno: " + err.getMessage());
        }
        return alumno;
    }

    public ArrayList<Alumno> extraerAlumnos(){
        ArrayList<Alumno> alumnos= new ArrayList<>();
        String sql="SELECT * FROM alumnos";
        try(Connection conexion = Coleccion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Alumno alumno=new Alumno();
                alumno.setNumExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setGrupo(rs.getString("grupo"));
                alumno.setPromedio(rs.getDouble("promedio"));
                alumnos.add(alumno);
            }
        }catch (SQLException err){
            System.out.println("ERROR AL EXTRAER ALUMNOS: "+err.getMessage());
        }
        return alumnos;
    }

    public boolean actualizar(Alumno alumno){
        boolean actualizado = false;
        String sql = "UPDATE alumnos SET nombre=?, curp=?, grupo=?, promedio=? WHERE numExpediente=?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setString(1, alumno.getNombre());
            stm.setString(2, alumno.getCurp());
            stm.setString(3, alumno.getGrupo());
            stm.setDouble(4, alumno.getPromedio());
            stm.setInt(5, alumno.getNumExpediente());

            int registrosAfectados = stm.executeUpdate();
            if(registrosAfectados > 0){
                System.out.println("Alumno actualizado correctamente.");
                actualizado = true;
            } else {
                System.out.println("El número de expediente no se encontró.");
            }
        } catch (SQLException err) {
            System.out.println("Error al actualizar: " + err.getMessage());
        }
        return actualizado;
    }

    public boolean eliminarAlumno(int numExpediente) {
        boolean eliminado = false;
        String sql = "DELETE FROM alumnos WHERE numExpediente = ?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            stm.setInt(1, numExpediente);

            int filasAfectadas = stm.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
                System.out.println("Alumno eliminado correctamente de la base de datos.");
            } else {
                System.out.println("No se encontró ningún alumno con el expediente: " + numExpediente);
            }
        } catch (SQLException err) {
            System.out.println("Error al eliminar el alumno: " + err.getMessage());
        }
        return eliminado;
    }
}