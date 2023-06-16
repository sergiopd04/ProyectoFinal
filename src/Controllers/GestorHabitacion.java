package Controllers;

import Models.Cliente;
import Models.Habitacion;
import Models.Reservas;
import Utils.Validaciones;

import java.io.*;
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
                if (habitacionArray.length >= 8) {
                    int num_camas = Integer.parseInt(habitacionArray[3]);
                    int max_personas = Integer.parseInt(habitacionArray[4]);
                    boolean banera = Boolean.parseBoolean(habitacionArray[5]);
                    boolean ocupada = Boolean.parseBoolean(habitacionArray[6]);
                    double precio = Double.parseDouble(habitacionArray[7]);
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
        compbanera = sc.nextLine().toUpperCase();
        if (compbanera.equals("S")) {
            banera = true;
        } else banera = false;

        System.out.println("Ocupada: ");
        compocupada = sc.nextLine().toUpperCase();
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

    public void listarHabitacion() {
        System.out.println("LISTANDO HABITACIÓN");
        for (Habitacion habitacion : listadoHabitaciones) {
            System.out.println("**************************");
            System.out.println("ID: "+ habitacion.getId());
            System.out.println("Nombre: "+ habitacion.getNombre());
            System.out.println("Descripción: "+ habitacion.getDescripcion());
            System.out.println("Num Camas: "+ habitacion.getNum_camas());
            System.out.println("Max Personas: "+ habitacion.getMax_personas());
            System.out.println("Bañera: "+ habitacion.isBanera());
            System.out.println("Ocupada: "+ habitacion.isOcupada());
            System.out.println("Precio: "+ habitacion.getPrecio());
        }
    }

    public void modificarHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la habitación a modificar: ");
        String id = scanner.nextLine();

        for (Habitacion habitacion : listadoHabitaciones) {
            if (habitacion.getId().equals(id)) {
                System.out.println("Habitación encontrado. ¿Qué atributo desea modificar?");
                System.out.println("**************************");
                System.out.println("1. ID");
                System.out.println("2. Nombre");
                System.out.println("3. Descripción");
                System.out.println("4. Num Camas");
                System.out.println("5. Max Personas");
                System.out.println("6. Bañera");
                System.out.println("7. Ocupada");
                System.out.println("8. Precio");

                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Ingrese el nuevo ID: ");
                        String nuevoId = scanner.nextLine();
                        habitacion.setId(nuevoId);
                        break;
                    case "2":
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        habitacion.setNombre(nuevoNombre);
                        break;
                    case "3":
                        System.out.print("Ingrese la nueva descripción: ");
                        String nuevaDescripcion = scanner.nextLine();
                        habitacion.setDescripcion(nuevaDescripcion);
                        break;
                    case "4":
                        System.out.print("Ingrese el nuevo ID: ");
                        String nuevonumcamas = scanner.nextLine();
                        int numCamas = Integer.parseInt(nuevonumcamas);
                        habitacion.setNum_camas(numCamas);
                        break;
                    case "5":
                        System.out.print("Ingrese el nuevo ID: ");
                        String nuevomaxpersonas = scanner.nextLine();
                        int maxPersonas = Integer.parseInt(nuevomaxpersonas);
                        habitacion.setMax_personas(maxPersonas);
                        break;
                    case "6":
                        String compbanera;
                        boolean banera=false;
                        System.out.println("Bañera: ");
                        compbanera = scanner.nextLine().toUpperCase();
                        if (compbanera.equals("S")) {
                            banera = true;
                        } else banera = false;
                        habitacion.setBanera(banera);
                        break;
                    case "7":
                        String compbocupado;
                        boolean ocupado=false;
                        System.out.println("Bañera: ");
                        compbocupado = scanner.nextLine().toUpperCase();
                        if (compbocupado.equals("S")) {
                            ocupado = true;
                        } else ocupado = false;
                        habitacion.setBanera(ocupado);
                        break;
                    case "8":
                        System.out.print("Ingrese el nuevo ID: ");
                        String strprecio = scanner.nextLine();
                        double precio = Double.parseDouble(strprecio);
                        habitacion.setPrecio(precio);
                        break;
                    default:
                        System.out.println("Opción inválida. No se realizaron modificaciones.");
                        return;
                }
                System.out.println("Cliente modificado exitosamente.");
                return;
            }
        }
        System.out.println("No se encontró ningún cliente con el DNI proporcionado.");
    }

    public void eliminarHabitacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código de la habitación a eliminar: ");
        String habitacion = scanner.nextLine();

        String archivo = "src/data/habitaciones.dat";

        try {
            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");

            boolean encontrado = false;
            long posicion = 0;
            String linea;

            while ((linea = raf.readLine()) != null) {
                String[] datosHabiracion = linea.split(";");
                if (datosHabiracion[0].equals(habitacion)) {
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
                    System.out.println("Habitación eliminada exitosamente.");
                } else {
                    raf.setLength(posicion);
                    System.out.println("Habitación eliminada exitosamente.");
                }
            } else {
                System.out.println("No se encontró ninguna habitación con el código proporcionado.");
            }

            raf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
