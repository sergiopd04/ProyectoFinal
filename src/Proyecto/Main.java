package Proyecto;

import Models.AtencionCliente;
import Utils.Validaciones;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Validaciones validaciones = new Validaciones();
        AtencionCliente sistemafaqs = new AtencionCliente();

        String opcion,hotelopcion,metpagoopcion;
        String nombre, apellidos, email, tlf, fecha, fraseControl, emailLogin, codLogin, tarjeta, CCV, fechaCad, fechaEntrada, fechaSalida;

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
                    System.out.print("Fecha de Nacimiento (dd/mm/aaaa): ");
                    fecha=sc.nextLine();
                    validaciones.validarFecha(fecha);
                    System.out.print("Frase de Control (4 palabras separadas por espacios): ");
                    fraseControl=sc.nextLine();
                    validaciones.validarFrase(fraseControl);
                    break;
                case "2":
                    /*System.out.println("Email: ");
                    emailLogin=sc.nextLine();
                    validaciones.validarEmailLogin(emailLogin);
                    System.out.println("Código Numérico: ");
                    codLogin=sc.nextLine();
                    validaciones.validarCodigoLogin(codLogin);*/
                    System.out.println("1. Reserva de habitación");
                    System.out.println("2. Atención al cliente");
                    System.out.println("3. Pago on-line con factura");
                    System.out.println("4. Salir");
                    System.out.print("Elige una opcion: ");
                    hotelopcion= sc.nextLine();
                    switch (hotelopcion) {
                        case "1":
                            System.out.println("******* RESERVAR HABITACIÓN *******");
                            /*System.out.println("¿Para cuántas personas se hace la reserva?");*/
                            do {
                                System.out.print("Ingrese la fecha de entrada (dd/mm/yyyy): ");
                                fechaEntrada = sc.nextLine();

                                System.out.print("Ingrese la fecha de salida (dd/mm/yyyy): ");
                                fechaSalida = sc.nextLine();

                            } while (!Validaciones.validarFechas(fechaEntrada, fechaSalida));

                            System.out.println("Las fechas son válidas.");

                            break;
                        case "2":
                            sistemafaqs.mostarfaqs();
                            break;
                        case "3":
                            System.out.println("******* MÉTODOS DE PAGO *******");
                            System.out.println("1. Tarjeta de crédito.");
                            System.out.println("2. Bizum.");
                            metpagoopcion = sc.nextLine();
                            switch (metpagoopcion){
                                case "1":
                                    System.out.println("Número tarjeta:");
                                    tarjeta = sc.nextLine();
                                    validaciones.validarTarjeta(tarjeta);
                                    System.out.println("CCV:");
                                    CCV = sc.nextLine();
                                    validaciones.validarCCV(CCV);
                                    System.out.println("Fecha de caducidad:");
                                    fechaCad = sc.nextLine();
                                    validaciones.validarFechaCad(fechaCad);
                                    break;
                                case "2":
                                    System.out.println("Has elegido la opción de pago con bizum.");
                                    System.out.println("El número de teléfono disponible al que realizar el pago es el siguiente: +34 685 04 05 47");
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
