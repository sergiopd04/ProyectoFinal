package Models;

import java.time.LocalDate;

public class Cliente {
    String nombre;
    String apellidos;
    String email;
    String dni;
    LocalDate fecha_nacimiento;
    String codigo_acceso;

    public Cliente(String nombre, String apellidos, String email, String dni, LocalDate fecha_nacimiento, String codigo_acceso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.codigo_acceso = codigo_acceso;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCodigo_acceso() {
        return codigo_acceso;
    }

    public void setCodigo_acceso(String codigo_acceso) {
        this.codigo_acceso = codigo_acceso;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", codigo_acceso='" + codigo_acceso + '\'' +
                '}';
    }
}
