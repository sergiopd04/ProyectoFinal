package Controllers;

import java.util.Scanner;

public class GestorPrincipal {
    Scanner sc = new Scanner(System.in);
    void MenuGestorPrincipal(){
        boolean salir=false;
        String opcion;
        do {
            System.out.println("1. Gestionar clientes");
            System.out.println("2. Gestionar habitaciones");
            System.out.println("3. Gestionar reservas");
            System.out.println("4. Volver al menú anterior");
            System.out.println("0. Salir de la aplicación");
            System.out.println("Elige opción");
            opcion= sc.nextLine();
            switch (opcion){
                case "1":
                    menuClientes();
                    break;
                case "3":


            }

        }while (salir);
    }

    private void menuClientes() {
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
                    /*gestorClientes.guardarCliente();*/
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":
                    System.out.println("Regresando al menú anterior...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo");
            }
        } while (opcion != "5");
    }
    void menuReservas(){

    }
}
