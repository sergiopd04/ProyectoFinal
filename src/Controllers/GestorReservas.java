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

    private ArrayList<Habitacion> listadoHabitaciones;

    public GestorReservas(ArrayList<Habitacion> listadoHabitaciones) {
        this.listadoHabitaciones = listadoHabitaciones;
    }

    public GestorReservas() {
    }

    Validaciones validaciones = new Validaciones();

    public void reservaAnadida() throws ParseException {

        Scanner sc = new Scanner(System.in);

        List<Habitacion> listadoHabitaciones = new ArrayList<>();
        List<Reservas> listadoReservas = new ArrayList<>();

        System.out.println("*******   RESERVAR HABITACIÓN  ********");
        System.out.print("¿Para cuántas personas se hace la reserva? ");
        int numeroclientes = Integer.parseInt(sc.nextLine());

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

        List<String> habitaciondisponible = new ArrayList<>();

        for (Habitacion habitacion1 : listadoHabitaciones) {
            if (numeroclientes <= habitacion1.getMax_personas()) {
                habitaciondisponible.add(habitacion1.getId());
            }
        }

        for (Reservas reserva : listadoReservas) {
            if (reserva.getFecha_entrada().equals(mifecha)
                    || (mifecha.before(reserva.getFecha_salida()) && mifecha.after(reserva.getFecha_entrada())
                    && mifecha.getMonth() == reserva.getFecha_entrada().getMonth())) {
                habitaciondisponible.remove(reserva.getId_habitacion());
            }
        }

        System.out.println("\nTenemos disponibles para esa fecha:");
        int opcion = 1;
        for (String hd : habitaciondisponible) {
            for (Habitacion hab : listadoHabitaciones) {
                if (hab.getId().equals(hd)) {
                    System.out.println("\t* Opción " + opcion++);
                    System.out.println("\t" + hab.getNombre() + " para " + hab.getMax_personas() + " personas");
                    System.out.println("\tPrecio final: " + hab.getPrecio() + " €");
                }
            }
        }

        System.out.print("\n¿Qué opción desea? ");
        String opciones = sc.nextLine();
    }


    ArrayList<String> habitaciondisponible =new ArrayList<String>();
    

    public ArrayList<Habitacion> getListadoHabitaciones() {
        return listadoHabitaciones;
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
}