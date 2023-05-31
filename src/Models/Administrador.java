package Models;

public class Administrador extends Usuario {
    private boolean activo;

    public Administrador(String nombreUsuario, String password, boolean rol, boolean activo) {
        super(nombreUsuario, password,rol);
        this.activo = activo;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}