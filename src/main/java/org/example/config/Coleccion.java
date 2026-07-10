package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Coleccion {
    private static final String url = "jdbc:mysql://localhost:3306/universidadUT";
    private static final String user = "root";
    private static final String password = "jocesa7.";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión realizada correctamente");
        } catch (SQLException err) {
            System.out.println("Error al conectar con MySQL: " + err.getMessage());
        }
        return conexion;
    }
}