package Controllers;

import Models.Habitacion;

import java.util.ArrayList;

public class GestorHabitacion {

    ArrayList<Habitacion> listadoHabitaciones = new ArrayList<>();

    public GestorHabitacion(){

    }

    public GestorHabitacion(ArrayList<Habitacion> listadoHabitaciones) {
        this.listadoHabitaciones = listadoHabitaciones;
    }

    public ArrayList<Habitacion> getListadoHabitaciones() {
        return listadoHabitaciones;
    }

    public void setListadoHabitaciones(ArrayList<Habitacion> listadoHabitaciones) {
        this.listadoHabitaciones = listadoHabitaciones;
    }

    public void generarHabitaciones(){
        Habitacion habitacion1 = new Habitacion("A1","Pobre","", 0, 1, false, false, 8);
        Habitacion habitacion2 = new Habitacion("A2","Humilde","", 1, 1, true, false, 15);
        Habitacion habitacion3 = new Habitacion("B1","Normal","", 4, 2, true, true, 24);
        Habitacion habitacion4 = new Habitacion("B2","Billetes","", 4, 3, true, true, 50);
        Habitacion habitacion5 = new Habitacion("C1","Farlopero","", 4, 3, true, false, 90);
    }

}
