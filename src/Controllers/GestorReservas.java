package Controllers;

import Models.Cliente;
import Models.Habitacion;
import Models.Reservas;
import Proyecto.Main;
import Utils.Validaciones;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import Models.Cliente;
import Utils.Validaciones;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static Controllers.GestorClientes.listadoClientes;

public class GestorReservas {

    GestorHabitacion gestorHabitaciones = new GestorHabitacion();

    public static ArrayList<Reservas> getListadoReservas() {
        return listadoReservas;
    }

    public static void setListadoReservas(ArrayList<Reservas> listadoReservas) {
        GestorReservas.listadoReservas = listadoReservas;
    }

    static ArrayList<Reservas> listadoReservas=new ArrayList<>();


    public GestorReservas() {
    }

    Validaciones validaciones = new Validaciones();

    public static void mostrarHabitacionesDisponibles(ArrayList<Habitacion> listadoHabitaciones) throws ParseException {

        Habitacion habitaciones = new Habitacion();

        Scanner sc = new Scanner(System.in);

        System.out.println("*******   RESERVAR HABITACIÓN  ********");
        System.out.print("¿Para cuántas personas se hace la reserva? ");
        String num_personas = sc.nextLine();

        String fecha_entrada;
        String fecha_salida;

        do {
            System.out.print("Ingrese la fecha de entrada (dd/mm/yyyy): ");
            fecha_entrada = sc.nextLine();
            System.out.print("Ingrese la fecha de salida (dd/mm/yyyy): ");
            fecha_salida = sc.nextLine();
        } while (!Validaciones.validarFechas(fecha_entrada, fecha_salida));
        System.out.println("Las fechas son válidas.");


        LocalDate fechaEntrada = Validaciones.convertirFecha(fecha_entrada);
        LocalDate fechaSalida = Validaciones.convertirFecha(fecha_salida);

        System.out.println("Tenemos disponibles: ");

        int opcion = 1;
        for (Habitacion habitacion : listadoHabitaciones) {
            int personas = Integer.parseInt(num_personas);
            if (habitacion.getMax_personas() <= personas) {
                System.out.println("Opción " + opcion + ":");
                System.out.println("Código Habitacion: " + habitacion.getId());
                System.out.println(habitacion.getNombre() + " para " + habitacion.getMax_personas() + " personas");
                System.out.println("Precio final: " + habitacion.getPrecio() + " €");
                opcion++;
            }
        }

        int opcionElegida = -1;

        while (opcionElegida < 1) {
            System.out.print("Ingrese el número de opción deseada: ");
            opcionElegida = Integer.parseInt(sc.nextLine());
        }
        Habitacion habitacionElegida = listadoHabitaciones.get(opcionElegida - 1);

        String id = habitacionElegida.getId();


        boolean coincide=false;
        boolean nocoinciden=false;

        for (Reservas reserva : listadoReservas) {
            if (reserva.getId_habitacion().equals(habitacionElegida.getId()) &&
                    reserva.getFecha_entrada().equals(fechaEntrada) &&
                    reserva.getFecha_salida().equals(fechaSalida)) {
                coincide=true;
            }else {
                nocoinciden=true;
            }
        }

        if (coincide) System.out.println("La reserva ya existe");

        if (nocoinciden){
            System.out.println("*********   FACTURA   *********");
            System.out.println("Habitación seleccionada:");
            System.out.println("Código: " + habitacionElegida.getId());
            System.out.println("Nombre: " + habitacionElegida.getNombre());
            System.out.println("Capacidad: " + habitacionElegida.getMax_personas() + " personas");
            System.out.println("Precio: " + habitacionElegida.getPrecio() + " €");
            System.out.println("*******************************");

            String metpagoopcion, numeroTarjeta;
            do {
                System.out.println("******* MÉTODOS DE PAGO *******");
                System.out.println("1. Tarjeta");
                System.out.println("2. Bizum.");
                metpagoopcion = sc.nextLine();
            }while (metpagoopcion.isEmpty());
            switch (metpagoopcion) {
                case "1":
                    do {
                        System.out.print("Ingrese un número de tarjeta: ");
                        numeroTarjeta = sc.nextLine();
                    } while (!Validaciones.Tarjeta(numeroTarjeta) || numeroTarjeta.isEmpty());
                    break;
                case "2":
                    Validaciones.validarBizum();
                    break;
            }
        }

        try {
            guardarReservas(id, fechaEntrada,fechaSalida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void guardarReservas(String id_habitacion, LocalDate fecha_entrada, LocalDate fecha_salida) throws IOException {

        FileWriter escribir = new FileWriter("src/data/reservas.dat",true);

        Reservas nuevo = new Reservas(id_habitacion, fecha_entrada, fecha_salida);
        escribir.write(id_habitacion + ";" + fecha_entrada + ";" + fecha_salida + ";" + "\n");
        escribir.flush();
        escribir.close();
        listadoReservas.add(nuevo);
    }

    public void cargarDatos() throws IOException {

        FileReader lector = new FileReader("src/data/reservas.dat");

        int datos;
        datos = lector.read();
        String campos = "";

        while (datos != -1) {
            campos = campos + (char) datos;
            datos = lector.read();
        }
        lector.close();

        if (!campos.equals("")) {
            String[] separacionReservas = campos.split("\n");
            for (String reservaSeparado : separacionReservas) {
                String[] reservasArray = reservaSeparado.split(";");
                LocalDate fecha_entrada = LocalDate.parse(reservasArray[1]);
                LocalDate fecha_salida = LocalDate.parse(reservasArray[2]);
                Reservas reservas = new Reservas(reservasArray[0],fecha_entrada, fecha_salida);
                listadoReservas.add(reservas);
            }
        }
    }

    public void agregarReservas(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Id Habitacion: ");
        String id_habitacion=sc.nextLine();
        System.out.println("Fecha entrada");
        String fecha_entrada=sc.nextLine();
        System.out.println("Fecha salida");
        String fecha_salida=sc.nextLine();

        LocalDate fechaEntrada = Validaciones.convertirFecha(fecha_entrada);
        LocalDate fechaSalida = Validaciones.convertirFecha(fecha_salida);

        try {
            guardarReservas(id_habitacion, fechaEntrada,fechaSalida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Reserva agregada correctamente. \n");
    }

    public void listarReservas(){
        for (Reservas reservas : listadoReservas) {
            System.out.println("**************************");
            System.out.println("Habitacion ID: "+ reservas.getId_habitacion());
            System.out.println("Fecha entrada: "+ reservas.getFecha_entrada());
            System.out.println("Fecha salida: "+ reservas.getFecha_salida());
        }
    }

    public void cancelarReservas() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de la habitación a eliminar: ");
        String habitacion = scanner.nextLine();

        String archivo = "src/data/reservas.dat";

        try {
            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");

            boolean encontrado = false;
            long posicion = 0;
            String linea;

            while ((linea = raf.readLine()) != null) {
                String[] datosReserva = linea.split(";");
                if (datosReserva[0].equals(habitacion)) {
                    encontrado = true;
                    break;
                }
                posicion = raf.getFilePointer();
            }

            if (encontrado) {
                raf.seek(posicion);
                String siguienteLinea;
                long inicioLineaSiguiente;

                if ((siguienteLinea = raf.readLine()) != null) {
                    inicioLineaSiguiente = raf.getFilePointer();
                    raf.seek(posicion);
                    raf.writeBytes(siguienteLinea);
                    raf.setLength(raf.length() - (inicioLineaSiguiente - posicion));
                    System.out.println("Reserva eliminada exitosamente.");
                } else {
                    raf.setLength(posicion);
                    System.out.println("Reserva eliminada exitosamente.");
                }
            } else {
                System.out.println("No se encontró ninguna reserva con el código proporcionado.");
            }

            raf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

