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
    
    Reservas reservas = new Reservas();

    public GestorReservas() {
    }

    Validaciones validaciones = new Validaciones();

    public void mostrarHabitacionesDisponibles(ArrayList<Habitacion> listadoHabitaciones) throws ParseException {
        Scanner sc = new Scanner(System.in);


        System.out.println("*******   RESERVAR HABITACIÓN  ********");
        System.out.print("¿Para cuántas personas se hace la reserva? ");
        String num_personas = sc.nextLine();

        String  fechaEntrada;
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
            if (habitacion.getMax_personas() <= personas) {
                System.out.println("Opción " + opcion + ":");
                System.out.println(habitacion.getNombre() + " para " + habitacion.getMax_personas() + " personas");
                System.out.println("Precio final: " + habitacion.getPrecio() + " €");
                opcion++;
            }
        }
    }


    ArrayList<Cliente> listadoReservas=new ArrayList<>();

    public ArrayList<Cliente> getListadoReservas() {
        return listadoReservas;
    }

    public void setListadoReservas(ArrayList<Cliente> listadoReservas) {
        this.listadoReservas = listadoReservas;
    }




}