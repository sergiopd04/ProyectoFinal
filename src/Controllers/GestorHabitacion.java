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

    public void generarHabitaciones(ArrayList<Habitacion> listadoHabitaciones){
        listadoHabitaciones.add(new Habitacion("A1","Pobre","adsa", 1, 1, false, false, 5));
        listadoHabitaciones.add(new Habitacion("A2","Pobrillo","asd", 1, 1, false, false, 15));
        listadoHabitaciones.add(new Habitacion("B1","Normal","asd", 2, 2, true, true, 20));
        listadoHabitaciones.add(new Habitacion("B2","Con dinero","asd", 2, 2, true, false, 30));
        listadoHabitaciones.add(new Habitacion("C1","Jeque","asd", 3, 3, true, false, 40));
    }
}