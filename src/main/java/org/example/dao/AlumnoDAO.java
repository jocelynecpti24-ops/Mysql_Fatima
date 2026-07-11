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
            System.out.println("alumno inscrito correctamente ");
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
    public ArrayList<Alumno> extraerAlumnos(){
        ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
        String sql="SELECT * FROM alumnos";
        try(Connection conexion = Coleccion.conectar();
            PreparedStatement stm = conexion.prepareStatement(sql)){
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Alumno alumno=new Alumno();
                alumno.setNumExpediente(rs.getInt("numExpediente"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setCurp(rs.getString("curp"));
                alumno.setGrupo(rs.getString("Grupo"));
                alumno.setPromedio(rs.getDouble("promedio"));
                alumnos.add(alumno);
            }
        }catch (SQLException err){
            System.out.println("ERROR AL EXTRAER ALUMNOS"+err.getMessage());
        }

        return alumnos;
    }

    public boolean actualizar(Alumno alumno){
        boolean actualizado = false;
        // Se corrigieron las incógnitas del SQL: un '?' por columna a modificar
        String sql = "UPDATE alumnos SET nombre=?, curp=?, grupo=?, promedio=? WHERE numExpediente=?";

        try (Connection conexion = Coleccion.conectar();
             PreparedStatement stm = conexion.prepareStatement(sql)) {

            // Mapeo ordenado de los parámetros para el UPDATE
            stm.setString(1, alumno.getNombre());
            stm.setString(2, alumno.getCurp());
            stm.setString(3, alumno.getGrupo());
            stm.setDouble(4, alumno.getPromedio());
            stm.setInt(5, alumno.getNumExpediente());
            stm.executeUpdate();
            int registrosAfectados=stm.executeUpdate();
            if(registrosAfectados>0){
                System.out.println("alumno actualizado  correctamente");
                actualizado =true;
            }
            else{
                System.out.println("el numero de expediente no se encontro");
            }
        }
        catch (SQLException err) {
            System.out.println("eror al actualizar"+err);
        }
        return actualizado;
    }
}