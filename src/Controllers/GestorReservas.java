package Controllers;

import Models.Cliente;
import Models.Habitacion;
import Models.Reservas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorReservas {

    ArrayList<Cliente> listadoReservas=new ArrayList<>();

    public ArrayList<Cliente> getListadoReservas() {
        return listadoReservas;
    }

    public ArrayList<Habitacion> listadoHabitaciones;

    public void setListadoReservas(ArrayList<Cliente> listadoReservas) {
        this.listadoReservas = listadoReservas;
    }

    public void guardarReserva(int codigo, String id_cliente, String id_habitacion, String fecha_entrada, String fecha_salida) throws IOException {

        FileWriter escribir = new FileWriter("src/data/reservas.dat");

        Reservas nueva = new Reservas(codigo,id_cliente,id_habitacion,fecha_entrada,fecha_salida);
        escribir.write(codigo + ";" + id_cliente + ";" + id_habitacion + ";" + fecha_entrada + ";" + fecha_salida + ";" + "\n");

    }


    public void generarReservas (){
        Reservas reserva1 = new Reservas(2,"2","A1","27/06/2023","27/07/2023");
        Reservas reserva2 = new Reservas(3,"3","B1","27/07/2023","27/08/2023");
        Reservas reserva3 = new Reservas(4,"4","C1","27/08/2023","27/09/2023");
    }
}
