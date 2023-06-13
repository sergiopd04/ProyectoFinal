package Controllers;

import Models.Cliente;
import Models.Habitacion;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorHabitacion {

    static ArrayList<Habitacion> listadoHabitaciones = new ArrayList<>();

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

    public static void guardarCliente(String nombre, String apellidos, boolean rol, String email, String dni, LocalDate fecha, String codigoAcceso) throws IOException {

        FileWriter escribir = new FileWriter("src/data/clientes.dat", true);

        Habitacion nuevo = new Habitacion(nombre, apellidos, rol, email, dni, fecha, codigoAcceso);
        escribir.write(nombre + ";" + apellidos + ";" + rol + ";" + email + ";" + dni + ";" + fecha + ";" + codigoAcceso + ";" + "\n");
        escribir.flush();
        escribir.close();
        listadoHabitaciones.add(nuevo);
    }

    public void cargarDatos() throws IOException{

        FileReader lector = new FileReader("src/data/clientes.dat");

        int datos;
        datos = lector.read();
        String campos = "";

        while (datos != -1) {
            campos = campos + (char)datos;
            datos = lector.read();
        }
        lector.close();

        if (!campos.equals("")) {
            String[] separacionClientes = campos.split("\n");
            for (String clienteSeparado: separacionClientes) {
                String[] clienteArray = clienteSeparado.split(";");
                if (clienteArray.length >= 6) {
                    LocalDate fecha = LocalDate.parse(clienteArray[5]);
                    boolean rol = Boolean.parseBoolean(clienteArray[2]);
                    Cliente cliente = new Cliente(clienteArray[0],clienteArray[1],rol,clienteArray[3],clienteArray[4],fecha,clienteArray[6]);
                    listadoClientes.add(cliente);
                } else {
                    System.out.println("Eror");
                }

            }
        }
    }
}