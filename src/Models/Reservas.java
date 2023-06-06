package Models;


import java.util.Date;

public class Reservas {


    int cod;
    String id_cliente;
    String id_habitacion;
    Date fecha_entrada;
    Date fecha_salida;




    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(String id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Reservas(int cod, String id_cliente, String id_habitacion, Date fecha_entrada, Date fecha_salida){
        this.cod=cod;
        this.id_cliente=id_cliente;
        this.id_habitacion=id_habitacion;
        this.fecha_entrada=fecha_entrada;
        this.fecha_salida=fecha_salida;
    }

    public Reservas() {
    }
}


