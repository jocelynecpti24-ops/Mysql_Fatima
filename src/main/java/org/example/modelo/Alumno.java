package org.example.modelo;

import java.util.Locale;

public class Alumno extends PersonaUT {
    private String grupo;
    private double promedio;
    private int numExpediente;

    public Alumno(){}

    // Constructor ordenado exactamente como lo mandas llamar en tu Main
    public Alumno(int numExpediente, String nombre, String curp, String grupo, double promedio) {
        super(nombre, curp);
        setNumExpediente(numExpediente);
        setGrupo(grupo);
        setPromedio(promedio);
    }

    public double getPromedio() {
        String promedioFormato = String.format(Locale.US, "%.1f", this.promedio);
        return Double.parseDouble(promedioFormato);
    }

    public void setPromedio(double promedio) {
        if (promedio >= 0 && promedio <= 10) {
            this.promedio = promedio;
        } else {
            System.out.println("El promedio debe ser entre 0 y 10");
        }
    }

    public String getGrupo() {
        return grupo != null ? grupo.toUpperCase() : "";
    }

    public void setGrupo(String grupo) {
        // Se cambió .isBlank() por .trim().isEmpty() para solucionar el error de Java 8
        if (grupo == null || grupo.isEmpty() || grupo.trim().isEmpty()) {
            System.out.println("El grupo es requerido");
        } else {
            this.grupo = grupo;
        }
    }

    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        // Ajustado para aceptar matrículas del año 2025 (como 202516025)
        if (numExpediente > 20000000 && numExpediente < 2140000000) {
            this.numExpediente = numExpediente;
        } else {
            System.out.println("Número de expediente no válido");
        }
    }

    @Override
    public String toString() {
        return "Número de expediente: " + getNumExpediente() + "\n" +
                super.toString() + " | Grupo: " + getGrupo() + "\n" +
                "Promedio: " + getPromedio() + "\n" +
                "---------------------------------------------------------------------------------------";
    }
}