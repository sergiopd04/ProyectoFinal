package Controllers;

import java.util.Scanner;

public class GestorPrincipal {

    public static void menuGestorPrincipal(){

        Scanner sc = new Scanner(System.in);
        boolean salir=false;
        String opcion;

        do {
            System.out.println("1. Gestionar clientes");
            System.out.println("2. Gestionar habitaciones");
            System.out.println("3. Gestionar reservas");
            System.out.println("4. Volver al menú anterior");
            System.out.println("Elige opción");
            opcion= sc.nextLine();
            switch (opcion){
                case "1":
                    menuClientes();
                    break;
                case "2":
                    menuHabitacion();
                case "3":
                    menuReservas();
                    break;
                default:
                    System.out.println("Opción incorrecta...");
            }

        }while (!opcion.equals("4"));
    }

    public static void menuClientes() {

        Scanner sc = new Scanner(System.in);
        String opcion;
        GestorClientes gestorClientes = new GestorClientes();

        do {
            System.out.println("------ MENÚ DE GESTIÓN DE CLIENTES ------");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Regresar al menú anterior");
            System.out.print("Elija una opción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    gestorClientes.agregarCliente();
                    break;
                case "2":
                    gestorClientes.buscarCliente();
                    break;
                case "3":
                    gestorClientes.modificarCliente();
                    break;
                case "4":
                    gestorClientes.eliminarCliente();
                    break;
                case "5":
                    System.out.println("Regresando al menú anterior...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (opcion != "5");
    }

    public static void menuHabitacion(){
        Scanner sc = new Scanner(System.in);
        String opcion;
        GestorHabitacion gestorHabitacion = new GestorHabitacion();

        do {
            System.out.println("------ MENÚ DE GESTIÓN DE CLIENTES ------");
            System.out.println("1. Agregar Habitación");
            System.out.println("2. Consultar Habitación");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    gestorHabitacion.agregarHabitacion();
                    break;
                case "2":
                    gestorHabitacion.buscarHabitacion();
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (opcion != "3");
    }
    public static void menuReservas(){
        Scanner sc = new Scanner(System.in);
        String opcion;
        GestorReservas gestorReservas = new GestorReservas();

        do {
            System.out.println("------ MENÚ DE GESTIÓN DE CLIENTES ------");
            System.out.println("1. Agregar reserva");
            System.out.println("2. Listar reserva");
            System.out.println("3. Cancelar reserva");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    gestorReservas.agregarReservas();
                    break;
                case "2":
                    gestorReservas.listarReservas();
                    break;
                case "3":
                    gestorReservas.cancelarReservas();
                    break;
                case "4":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (opcion != "4");
    }
}
