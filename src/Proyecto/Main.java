package Proyecto;

import Controllers.GestorClientes;
import Controllers.GestorHabitacion;
import Controllers.GestorPrincipal;
import Controllers.GestorReservas;
import Models.AtencionCliente;
import Models.Cliente;
import Models.Habitacion;
import Models.Pago;
import Utils.Validaciones;

import java.io.IOException;
import java.text.ParseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<Habitacion> listadoHabitaciones = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Validaciones validaciones = new Validaciones();
        Habitacion hab = new Habitacion();

        listadoHabitaciones.add(new Habitacion("A1","Pobre","adsa", 1, 1, false, false, 5));
        listadoHabitaciones.add(new Habitacion("A2","Pobre2","asd", 1, 1, false, true, 15));
        listadoHabitaciones.add(new Habitacion("B1","Normal","asd", 2, 2, true, true, 20));
        listadoHabitaciones.add(new Habitacion("B2","Esta bien","adsa", 2, 2, true, false, 40));
        listadoHabitaciones.add(new Habitacion("C1","Rico","asd", 3, 3, true, false, 60));

        AtencionCliente sistemafaqs = new AtencionCliente();

        GestorClientes gesCliente = new GestorClientes();
        gesCliente.cargarDatos();
        GestorHabitacion gestorHabitacion = new GestorHabitacion();
        gestorHabitacion.cargarDatos();
        GestorReservas gestorReservas = new GestorReservas();
        gestorReservas.cargarDatos();

        String opcion,hotelopcion,metpagoopcion;
        String nombre, apellidos, email, tlf, dni, fraseControl, emailLogin, codLogin, numeroTarjeta, CCV, fechaCad, fechaEntrada, fechaSalida,fecha,numPersonas;
        boolean tarjetaValida = false;
        boolean opcion2=false;

        do {
            System.out.print("*************\nTarifa Dreams\n*************\n");
            System.out.println("1. Registro Usuario");
            System.out.println("2. Login Usuario");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.print("\n************\n Registro \n************\n");
                    System.out.println("Introduce los siguientes datos \n--------------");
                    System.out.println("Nombre: ");
                    nombre=sc.nextLine();
                    validaciones.validarNombre(nombre);
                    System.out.println("Apellidos: ");
                    apellidos=sc.nextLine().toUpperCase();
                    validaciones.validarApellidos(apellidos);
                    System.out.println("Correo: ");
                    email=sc.nextLine();
                    validaciones.validarCorreo(email);
                    System.out.println("Teléfono: ");
                    tlf=sc.nextLine();
                    validaciones.validarTelefono(tlf);
                    do {
                        System.out.print("Ingrese un número de DNI: ");
                        dni = sc.nextLine();
                    } while (!Validaciones.validarDNI(dni));
                    do {
                        System.out.print("Ingresa tu fecha de nacimiento (dd/mm/yyyy): ");
                        fecha = sc.nextLine();
                    } while (!Validaciones.validarFecha(fecha));
                    LocalDate fechaNacimiento = Validaciones.convertirFecha(fecha);
                    if (Validaciones.calcularEdad(fechaNacimiento)){
                        System.out.print("Frase de Control (4 palabras separadas por espacios): ");
                        fraseControl=sc.nextLine();
                        validaciones.validarFrase(fraseControl);
                    }
                    gesCliente.guardarCliente(nombre,apellidos,false,email,dni,fechaNacimiento, Validaciones.getCodigoFinal());
                    break;
                case "2":
                    String correoInicio;
                    String codigoLogin;
                    Cliente cliente = null;
                    System.out.println("**Inicio de sesión**\n");
                    System.out.println("Introduce el correo: ");
                    correoInicio = sc.nextLine();
                    System.out.println("Introduce la contraseña: ");
                    codigoLogin = sc.nextLine();
                    try {
                        cliente = gesCliente.inicioSesion(correoInicio,codigoLogin);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    do {
                        boolean volverOpcion2=false;
                        System.out.println("1. Reserva de habitación");
                        System.out.println("2. Atención al cliente");
                        if (cliente.isRol()){
                            System.out.println("3. Menú Administrador");
                        }
                        System.out.println("4. Salir");
                        System.out.print("Elige una opcion: ");
                        hotelopcion= sc.nextLine();
                        switch (hotelopcion) {
                            case "1":
                                GestorReservas.mostrarHabitacionesDisponibles(listadoHabitaciones);
                                int opcionElegida = GestorReservas.obtenerOpcionElegida();
                                Pago.pagoATM();
                                GestorReservas.imprimirFactura(listadoHabitaciones, opcionElegida);
                                break;
                            case "2":
                                sistemafaqs.mostarfaqs();
                                break;
                            case "3":
                                if (cliente.isRol()){
                                    GestorPrincipal.menuGestorPrincipal();
                                    break;
                                }
                                break;
                            case "4":
                                System.out.println("Saliendo...");
                                break;

                            default:
                                System.out.println("Opcion incorrecta.");
                                break;
                        }
                    }while (!opcion2);
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        }while (!opcion.equals("0"));
    }
}