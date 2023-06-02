package Controllers;

import Models.Cliente;
import Models.Habitacion;
import Models.Reservas;
import Utils.Validaciones;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GestorReservas {

    GestorHabitacion gestorHabitaciones = new GestorHabitacion();

    private ArrayList<Habitacion> listadoHabitaciones = new ArrayList<Habitacion>();

    Reservas reservas = new Reservas();


    public GestorReservas() {
    }

    Validaciones validaciones = new Validaciones();

    public void mostrarHabitacionesDisponibles() throws ParseException {
        Scanner sc = new Scanner(System.in);


        System.out.println("*******   RESERVAR HABITACIÓN  ********");
        System.out.print("¿Para cuántas personas se hace la reserva? ");
        String num_personas = sc.nextLine();

        String fechaEntrada;
        String fechaSalida;
        do {
            System.out.print("Ingrese la fecha de entrada (dd/mm/yyyy): ");
            fechaEntrada = sc.nextLine();
            System.out.print("Ingrese la fecha de salida (dd/mm/yyyy): ");
            fechaSalida = sc.nextLine();
        } while (!Validaciones.validarFechas(fechaEntrada, fechaSalida));
        System.out.println("Las fechas son válidas.");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date mifecha = formato.parse(fechaEntrada);
        Date mifecha2 = formato.parse(fechaSalida);

        System.out.println("Tenemos disponibles para esa fecha:");

        int opcion = 1;
        for (Habitacion habitacion : listadoHabitaciones) {
            int personas = Integer.parseInt(num_personas);
            if (habitacion.getMax_personas() >= personas) {
                System.out.println("\t" + habitacion.getNombre() + " para " + habitacion.getMax_personas() + " personas");
                System.out.println("\tPrecio final: " + habitacion.getPrecio() + " €");
                opcion++;
            }
        }
    }
    public void setListadoHabitaciones(ArrayList<Habitacion> listadoHabitaciones) {
        this.listadoHabitaciones = listadoHabitaciones;
    }

    ArrayList<Cliente> listadoReservas=new ArrayList<>();

    public ArrayList<Cliente> getListadoReservas() {
        return listadoReservas;
    }

    public void setListadoReservas(ArrayList<Cliente> listadoReservas) {
        this.listadoReservas = listadoReservas;
    }

    public void generarReservas (){
        /*Reservas reserva1 = new Reservas(2,"2","A1","27/06/2023","27/07/2023");
        Reservas reserva2 = new Reservas(3,"3","B1","27/07/2023","27/08/2023");
        Reservas reserva3 = new Reservas(4,"4","C1","27/08/2023","27/09/2023");*/
    }
    public void generarHabitaciones(ArrayList<Habitacion> listadoHabitaciones){
        listadoHabitaciones.add(new Habitacion("A1","Pobre","adsa", 1, 1, false, false, 5));
        listadoHabitaciones.add(new Habitacion("A2","Pobre2","asd", 1, 1, false, false, 15));
        listadoHabitaciones.add(new Habitacion("B1","Normal","asd", 2, 2, true, true, 20));
        listadoHabitaciones.add(new Habitacion("B2","Con dinero","asd", 2, 2, true, false, 30));
        listadoHabitaciones.add(new Habitacion("C1","Jeque","asd", 3, 3, true, false, 40));
    }

}