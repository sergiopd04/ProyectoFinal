package Controllers;

import Models.Cliente;
import Models.Reservas;

import java.util.ArrayList;

public class GestorReservas {

    ArrayList<Cliente> listadoReservas=new ArrayList<>();

    public ArrayList<Cliente> getListadoReservas() {
        return listadoReservas;
    }

    public void setListadoReservas(ArrayList<Cliente> listadoReservas) {
        this.listadoReservas = listadoReservas;
    }

    public void generarReservas (){
        Reservas reserva1 = new Reservas(2,"2","A1","27/06/2023","27/07/2023");
        Reservas reserva2 = new Reservas(3,"3","B1","27/07/2023","27/08/2023");
        Reservas reserva3 = new Reservas(4,"4","C1","27/08/2023","27/09/2023");

    }

}
