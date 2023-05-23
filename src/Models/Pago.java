package Models;

public class Pago {
    String nombre;
    String apellidos;
    String fechaFactura;
    String codigoFactura;
    String descripcion;
    double precio;

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

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getDescipcion() {
        return descripcion;
    }

    public void setDescipcion(String descipcion) {
        this.descripcion = descipcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Pago(String nombre, String apellidos, String fechaFactura, String codigoFactura, String descripcion, double precio){
        this.nombre= nombre;
        this.apellidos=apellidos;
        this.fechaFactura=fechaFactura;
        this.codigoFactura=codigoFactura;
        this.descripcion=descripcion;
        this.precio=precio;
    }
}
