package Controllers;

import Models.Cliente;
import Models.Habitacion;
import Models.Reservas;
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
    public static int obtenerOpcionElegida() {
        Scanner sc = new Scanner(System.in);
        int opcionElegida = -1;

        while (opcionElegida < 1) {
            System.out.print("Ingrese el número de opción deseada: ");
            opcionElegida = Integer.parseInt(sc.nextLine());
        }

        return opcionElegida;
    }

    public static void imprimirFactura(ArrayList<Habitacion> listadoHabitaciones, int opcionElegida) {
        System.out.println("*********   FACTURA   *********");
        System.out.println("Habitación seleccionada:");
        Habitacion habitacionElegida = listadoHabitaciones.get(opcionElegida - 1);
        System.out.println("Nombre: " + habitacionElegida.getNombre());
        System.out.println("Capacidad: " + habitacionElegida.getMax_personas() + " personas");
        System.out.println("Precio: " + habitacionElegida.getPrecio() + " €");
        System.out.println("*******************************");
        // Puedes agregar más detalles a la factura si es necesario
    }

    public static void guardarReservas(int cod,String id_cliente, String id_habitacion, LocalDate fecha_entrada, LocalDate fecha_salida) throws IOException {

        FileWriter escribir = new FileWriter("src/data/reservas.dat",true);

        Reservas nuevo = new Reservas(cod, id_cliente, id_habitacion, fecha_entrada, fecha_salida);
        escribir.write(cod + ";" + id_cliente + ";" + id_habitacion + ";" + fecha_entrada + ";" + fecha_salida + ";" + "\n");
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
                LocalDate fecha_entrada = LocalDate.parse(reservasArray[3]);
                LocalDate fecha_salida = LocalDate.parse(reservasArray[4]);
                int cod = Integer.parseInt(reservasArray[0]);
                Reservas reservas = new Reservas(cod, reservasArray[1], reservasArray[2], fecha_entrada, fecha_salida);
                listadoReservas.add(reservas);
            }
        }
    }

    public void agregarReservas(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Codigo: ");
        String codigo=sc.nextLine();
        int cod = Integer.parseInt(codigo);
        System.out.println("Id Cliente: ");
        String id_cliente=sc.nextLine();
        System.out.println("Id Habitacion: ");
        String id_habitacion=sc.nextLine();
        System.out.println("Fecha entrada");
        String fecha_entrada=sc.nextLine();
        System.out.println("Fecha salida");
        String fecha_salida=sc.nextLine();

        LocalDate fechaEntrada = Validaciones.convertirFecha(fecha_entrada);
        LocalDate fechaSalida = Validaciones.convertirFecha(fecha_salida);

        try {
            guardarReservas(cod,id_cliente,id_habitacion, fechaEntrada,fechaSalida);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Reserva agregada correctamente. \n");
    }

    public void listarReservas(){
        for (Reservas reservas : listadoReservas) {
            System.out.println("**************************");
            System.out.println("Codigo: "+ reservas.getCod());
            System.out.println("Cliente ID: "+ reservas.getId_cliente());
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

