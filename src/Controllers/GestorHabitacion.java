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


}