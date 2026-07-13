package org.example.modelo;

    public class PersonaUT {
        private String nombre;
        private String curp;

        public PersonaUT(){}

        public PersonaUT(String nombre, String curp) {
            setNombre(nombre);
            setCurp(curp);
        }

        public String getNombre() {
            return nombre != null ? nombre.toUpperCase() : "";
        }

        public void setNombre(String nombre) {
            if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()){
                System.out.println("El nombre es requerido");
            } else {
                this.nombre = nombre;
            }
        }

        public String getCurp() {
            return curp;
        }

        public void setCurp(String curp) {

            if (curp == null || curp.trim().isEmpty() || curp.isEmpty()){
                System.out.println("El CURP es requerido");
            } else {
                this.curp = curp;
            }
        }

        @Override
        public String toString() {
            return "Nombre: " + getNombre() + " | CURP: " + getCurp();
        }
    }