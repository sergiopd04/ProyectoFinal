package Models;

public class Usuario {
    String nombreUsuario;
    String password;
    String email;
    boolean rol;

    public Usuario(String nombreUsuario, String password, boolean rol) {
        this.nombreUsuario=nombreUsuario;
        this.password=password;
        this.rol=rol;
    }

    public Usuario(boolean rol) {
    }

    public Usuario() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }
}
