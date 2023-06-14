package Controllers;

import Models.Cliente;
import Models.Habitacion;
import Utils.Validaciones;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorHabitacion {

    static ArrayList<Habitacion> listadoHabitaciones = new ArrayList<>();

    public GestorHabitacion() {

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


    public static void guardarHabitacion(String id, String nombre, String descripcion, int num_camas, int max_personas, boolean banera, boolean ocupada, double precio) throws IOException {

        FileWriter escribir = new FileWriter("src/data/habitaciones.dat", true);

        Habitacion nuevo = new Habitacion();
        escribir.write(id + ";" + nombre + ";" + descripcion + ";" + num_camas + ";" + max_personas + ";" + banera + ";" + ocupada + ";" + precio + "\n");
        escribir.flush();
        escribir.close();
        listadoHabitaciones.add(nuevo);
    }

    public void cargarDatos() throws IOException {

        FileReader lector = new FileReader("src/data/habitaciones.dat");

        int datos;
        datos = lector.read();
        String campos = "";

        while (datos != -1) {
            campos = campos + (char) datos;
            datos = lector.read();
        }
        lector.close();

        if (!campos.equals("")) {
            String[] separacionHabitaciones = campos.split("\n");
            for (String habitacionSeparado : separacionHabitaciones) {
                String[] habitacionArray = habitacionSeparado.split(";");
                if (habitacionArray.length >= 9) {
                    int num_camas = Integer.parseInt(habitacionArray[4]);
                    int max_personas = Integer.parseInt(habitacionArray[5]);
                    boolean banera = Boolean.parseBoolean(habitacionArray[6]);
                    boolean ocupada = Boolean.parseBoolean(habitacionArray[7]);
                    double precio = Double.parseDouble(habitacionArray[8]);
                    Habitacion habitacion = new Habitacion(habitacionArray[0], habitacionArray[1], habitacionArray[2], num_camas, max_personas, banera, ocupada, precio);
                    listadoHabitaciones.add(habitacion);
                } else {

                }

            }
        }
    }

    public void agregarHabitacion() {
        Scanner sc = new Scanner(System.in);

        String id, nombre, descripcion, compbanera, compocupada, numCamas, maxPersonas, precio;
        boolean banera = false;
        boolean ocupada = false;

        System.out.println("ID: ");
        id = sc.nextLine();
        System.out.println("Nombre: ");
        nombre = sc.nextLine();
        System.out.println("Descripción: ");
        descripcion = sc.nextLine();
        System.out.println("Camas: ");
        numCamas = sc.nextLine();
        int num_camas = Integer.parseInt(numCamas);
        System.out.println("Maximo personas: ");
        maxPersonas = sc.nextLine();
        int max_personas = Integer.parseInt(maxPersonas);

        System.out.println("Bañera: ");
        compbanera = sc.nextLine();
        if (compbanera.equals("S")) {
            banera = true;
        } else banera = false;

        System.out.println("Ocupada: ");
        compocupada = sc.nextLine();
        if (compocupada.equals("S")) {
            ocupada = true;
        } else ocupada = false;

        System.out.println("Precio: ");
        precio = sc.nextLine();

        try {
            guardarHabitacion(id, nombre, descripcion, num_camas, max_personas, banera, ocupada, Double.parseDouble(precio));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Habitacion agregada correctamente. \n");
    }

    public void buscarHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la habitación: ");
        String id = scanner.nextLine();

        for (Habitacion habitacion : listadoHabitaciones) {
            if (habitacion.getId().equals(id)) {
                System.out.println("ID: " + habitacion.getId());
                System.out.println("Nombre: " + habitacion.getNombre());
                System.out.println("Descripción: " + habitacion.getDescripcion());
                System.out.println("Camas: " + habitacion.getNum_camas());
                System.out.println("Máximo personas: " + habitacion.getMax_personas());
                System.out.println("Precio: " + habitacion.getPrecio());
                return;
            }
        }

        System.out.println("No se encontró ningún cliente con el DNI proporcionado.");
    }
}
