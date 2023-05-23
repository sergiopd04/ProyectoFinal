package Utils;

import java.util.Scanner;

public class Validaciones {

    public Validaciones(){

    }

    public boolean validarNombre(String nombre) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        do {
            while(nombre.length() < 3) {
                System.out.println("El nombre debe tener al menos 3 caracteres. Inténtalo de nuevo.");
                nombre = sc.nextLine();
            }

            while(nombre.length() < 3 || !nombre.matches("[a-zA-Zªº]+")) {
                System.out.println("El nombre debe tener al menos 3 caracteres y contener solo letras. Inténtalo de nuevo.");
                nombre = sc.nextLine();
            }

            salir = true;
        } while (!salir);

        return true;
    }


    public boolean validarApellidos(String apellidos) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        do {
            while(apellidos.length() < 3) {
                System.out.println("El apellido debe tener al menos 3 caracteres.");
                apellidos = sc.nextLine();
            }

            while(apellidos.length() < 3 || !apellidos.matches("[a-zA-Zªº]+")) {
                System.out.println("El apellido debe tener al menos 3 caracteres y contener solo letras.");
                apellidos = sc.nextLine();
            }

            salir = true;
        } while (!salir);

        return true;
    }

    public boolean validarTelefono(String tlf) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {

            if (tlf.trim().length() == 0) { // verifica si el teléfono está vacío
                System.out.println("Debe ingresar un número de teléfono");
                System.out.print("Teléfono: ");
                tlf = sc.nextLine().replace(" ", "");
                continue;
            }

            for (int i = 0; i < tlf.length(); i++) {
                if (tlf.length() == 9) {
                    if (tlf.matches("\\d+")) {
                        if ((tlf.charAt(0) == '6') || (tlf.charAt(0) == '7')) {
                            salir = true;
                        } else if ((tlf.charAt(0) == '8') || (tlf.charAt(0) == '9')) {
                            salir = true;
                        }
                    } else {
                        System.out.println("Has introducido mal el teléfono");
                        System.out.print("Teléfono: ");
                        tlf = sc.nextLine().replace(" ", "");

                    }
                } else {
                    System.out.println("Has introducido mal el teléfono");
                    System.out.print("Teléfono: ");
                    tlf = sc.nextLine().replace(" ", "");
                }
            }
        } while (!salir);
        return true;
    }

    public boolean validarCorreo(String correo) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        do {
            String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if(correo.matches(patron) && correo.indexOf('@') >= 3 && correo.indexOf('.') >= correo.indexOf('@') + 3 && correo.length() - correo.indexOf('.') >= 3) {
                salir=true;
            } else {
                System.out.println("Correo incorrecto");
                System.out.println("Escribe un nuevo correo");
                correo = sc.nextLine();
            }
        }while (!salir);
    return true;}

    public boolean validarFecha(String fecha) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {
            boolean validarDig = false;
            for (int i = 0; i < 1; i++) {
                if (!validarDig) {
                    if (fecha.length()==10) {
                        if (fecha.charAt(0) >= '0' || fecha.charAt(0) <= '9') {
                            if (((fecha.charAt(2) == '/') && (fecha.charAt(5) == '/')) || ((fecha.charAt(2) == '-') && (fecha.charAt(5) == '-'))) {
                                validarDig=true;
                            }else {
                                validarDig = false;
                                System.out.println("Fecha incorrecta");
                                System.out.println("Fecha: ");
                                fecha = sc.nextLine();
                                validarFecha(fecha);
                            }
                        }else {
                            validarDig = false;
                            System.out.println("Fecha incorrecta");
                            System.out.println("Fecha: ");
                            fecha = sc.nextLine();
                            validarFecha(fecha);
                        }
                    }else {
                        validarDig = false;
                        System.out.println("Fecha incorrecta");
                        System.out.println("Fecha: ");
                        fecha = sc.nextLine();
                        validarFecha(fecha);
                    }
                }else  {
                    validarDig = false;
                    System.out.println("Fecha incorrecta");
                    System.out.println("Fecha: ");
                    fecha = sc.nextLine();
                    validarFecha(fecha);
                }
            }

            if (validarDig){
                String subDia = fecha.substring(0,2);
                String subMes = fecha.substring(3,5);
                String subAnio = fecha.substring(6);

                int dia = Integer.parseInt(subDia);
                int mes = Integer.parseInt(subMes);
                int anio = Integer.parseInt(subAnio);

                if (dia >= 1 && dia <= 31) {
                    if (mes >= 1 && mes <= 12) {
                        salir = true;
                        if (anio % 400 == 0 || (anio % 4 == 0 && anio % 100 != 0)) {
                            salir = true;
                        } else {
                            System.out.println("Fecha incorrecta");
                            System.out.println("Introduce fecha: ");
                            fecha = sc.nextLine();
                            validarFecha(fecha);
                        }
                    } else {
                        System.out.println("Fecha incorrecta");
                        System.out.println("Introduce fecha: ");
                        fecha = sc.nextLine();
                        validarFecha(fecha);

                    }
                } else {
                    System.out.println("Fecha incorrecta");
                    System.out.println("Introduce fecha: ");
                    fecha = sc.nextLine();
                    validarFecha(fecha);
                }
            }
        } while (!salir);
        return true;
    }

    public boolean validarFrase(String frase) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        do {
            String[] palabras = frase.split("\\s+");
            if (palabras.length == 4) {
                salir=true;
            } else {
                System.out.println("Frase incorrecta");
                System.out.println("Introduce nueva frase:");
                frase = sc.nextLine();
            }
        }while (!salir);
        return true;
    }

    public boolean validarEmailLogin(String emailLogin) {
        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        String correoPrueba = "prueba@gmail.com";
        do {
            if (emailLogin.equals(correoPrueba)) {
                salir = true;
            }else {
                System.out.println("Correo incorrecto");
                System.out.println("Introduce un nuevo correo: ");
                emailLogin= sc.nextLine();
            }
        } while (!salir);
        return true;
    }

    public boolean validarCodigoLogin(String codLogin) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        String codigoPrueba  = "123";

        do {
            if (codLogin.equals(codigoPrueba)) {
                salir = true;
            }else {
                System.out.println("Código incorrecto");
                System.out.println("Introduce un nuevo código: ");
                codLogin= sc.nextLine();
            }
        } while (!salir);

        return true;
    }

    public boolean validarTarjeta(String tarjeta) {
        Scanner sc = new Scanner(System.in);
        boolean salir=false;



        do {
            boolean validarDigito=false;
            boolean validarBIN=false;


            tarjeta = tarjeta.replaceAll(" ", "");

            if (tarjeta.length() >=13 && tarjeta.length() <=16){
                if (tarjeta.matches("\\d+")){
                    validarDigito=true;
                }else {
                    System.out.println("Tarjeta inválida");
                    System.out.println("Tarjeta:");
                    tarjeta= sc.nextLine();
                    validarTarjeta(tarjeta);
                }
            }else {
                System.out.println("Tarjeta inválida");
                System.out.println("Tarjeta:");
                tarjeta= sc.nextLine();
                validarTarjeta(tarjeta);
            }
            if (validarDigito){
                int suma = 0;
                boolean doble = false;

                for (int i = tarjeta.length() - 1; i >= 0; i--) {
                    int digito = tarjeta.charAt(i) - '0';
                    if (doble) {
                        digito *= 2;
                        if (digito > 9) {
                            digito -= 9;
                        }
                    }
                    suma += digito;
                    doble = !doble;
                }

                if (suma % 10 == 0){
                    salir=true;
                    validarBIN=true;
                }else {
                    System.out.println("Tarjeta inválida");
                    System.out.println("Tarjeta:");
                    tarjeta= sc.nextLine();
                    validarTarjeta(tarjeta);
                }
                if(validarBIN){
                    String subBIN = tarjeta.substring(0,1);
                    switch (subBIN){
                        case "4":
                            System.out.println("VISA");
                            break;
                        case "5":
                            System.out.println("Mastercard");
                            break;
                        case "3":
                            System.out.println("American Express");
                            break;

                    }
                }
            }
        }while (!salir);

    return true;}


    public boolean validarCCV (String CCV){
        Scanner sc = new Scanner(System.in);
        boolean salir=false;

        do {
            for (int i = 0; i < CCV.length(); i++) {
                if (CCV.length() >=3 && CCV.length() <=4) {
                    if (CCV.matches("\\d+")) {
                        salir=true;
                    }else {
                        System.out.println("CCV incorrecto");
                        System.out.println("CCV: ");
                        CCV = sc.nextLine();
                    }
                }else{
                    System.out.println("CCV incorrecto");
                    System.out.println("CCV: ");
                    CCV = sc.nextLine();
                }
            }
        }while(!salir);
    return true;}

    public boolean validarFechaCad (String fechaCad){
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {
            boolean validarDig = false;
            for (int i = 0; i < 1; i++) {
                if (!validarDig) {
                    if (fechaCad.length()==5) {
                        if (fechaCad.charAt(0) >= '0' || fechaCad.charAt(0) <= '9') {
                            if (fechaCad.charAt(2) == '/') {
                                validarDig=true;
                            }else {
                                validarDig = false;
                                System.out.println("Fecha incorrecta");
                                System.out.println("Fecha: ");
                                fechaCad = sc.nextLine();
                            }
                        }else {
                            validarDig = false;
                            System.out.println("Fecha incorrecta");
                            System.out.println("Fecha: ");
                            fechaCad = sc.nextLine();
                        }
                    }else {
                        validarDig = false;
                        System.out.println("Fecha incorrecta");
                        System.out.println("Fecha: ");
                        fechaCad = sc.nextLine();
                    }
                }else  {
                    validarDig = false;
                    System.out.println("Fecha incorrecta");
                    System.out.println("Fecha: ");
                    fechaCad = sc.nextLine();
                }
            }

            if (validarDig) {
                String subUno = fechaCad.substring(0, 2);
                String subDos = fechaCad.substring(3);

                int uno = Integer.parseInt(subUno);
                int dos = Integer.parseInt(subDos);

                if (uno >0 && uno <12){
                    if (dos >=23 && dos <35){
                        salir=true;
                    }else {
                        System.out.println("Fecha incorrecta");
                        System.out.println("Fecha: ");
                        fechaCad = sc.nextLine();
                    }
                }else {
                    System.out.println("Fecha incorrecta");
                    System.out.println("Fecha: ");
                    fechaCad = sc.nextLine();
                }
            }

        } while (!salir);
    return true;}
}
