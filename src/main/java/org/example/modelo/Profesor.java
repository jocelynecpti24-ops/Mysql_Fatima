package org.example.modelo;

public class Profesor {
    private int numExpediente;
    private String nombre;
    private String curp;
    private String puesto;
    private double sueldo;

    // Constructor vacío obligatorio
    public Profesor() {
    }

    // Constructor con parámetros
    public Profesor(int numExpediente, String nombre, String curp, String puesto, double sueldo) {
        this.numExpediente = numExpediente;
        this.nombre = nombre;
        this.curp = curp;
        this.puesto = puesto;
        this.sueldo = sueldo;
    }

    // --- GETTERS Y SETTERS (Esenciales para que ProfesorDAO los encuentre) ---
    public int getNumExpediente() {
        return numExpediente;
    }

    public void setNumExpediente(int numExpediente) {
        this.numExpediente = numExpediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Profesor [Expediente=" + numExpediente + ", Nombre=" + nombre +
                ", CURP=" + curp + ", Puesto=" + puesto + ", Sueldo=$" + sueldo + "]";
    }
}