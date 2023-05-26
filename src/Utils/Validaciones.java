package Utils;

import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;


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

    public static boolean validarFecha(String fecha) {
        String[] partes = fecha.split("/");
        if (partes.length != 3) {
            System.out.println("Formato de fecha incorrecto. Debe ser dd/mm/yyyy.");
            return false;
        }

        int dia, mes, anio;
        try {
            dia = Integer.parseInt(partes[0]);
            mes = Integer.parseInt(partes[1]);
            anio = Integer.parseInt(partes[2]);
        } catch (NumberFormatException e) {
            System.out.println("Formato de fecha incorrecto. Debe ser dd/mm/yyyy.");
            return false;
        }

        if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
            System.out.println("Fecha inválida.");
            return false;
        }

        if (mes == 2) {
            // Validar febrero y años bisiestos
            boolean esBisiesto = (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
            if (esBisiesto && dia > 29) {
                System.out.println("Fecha inválida. Febrero en año bisiesto no puede tener más de 29 días.");
                return false;
            } else if (!esBisiesto && dia > 28) {
                System.out.println("Fecha inválida. Febrero no puede tener más de 28 días.");
                return false;
            }
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            // Validar meses con 30 días
            if (dia > 30) {
                System.out.println("Fecha inválida. El mes especificado no puede tener más de 30 días.");
                return false;
            }
        }

        // Realiza otras validaciones si es necesario, como verificar febrero y los años bisiestos

        return true;
    }

    public static LocalDate convertirFecha(String fecha) {
        String[] partes = fecha.split("/");
        if (partes.length != 3 || partes[0].isEmpty() || partes[1].isEmpty() || partes[2].isEmpty()) {
            throw new IllegalArgumentException("Formato de fecha incorrecto. Debe ser dd/mm/yyyy.");
        }

        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int anio = Integer.parseInt(partes[2]);
        return LocalDate.of(anio, mes, dia);
    }

    public static String calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();

        if (edad >= 18) {
            return "Fecha correcta. Eres mayor de edad.";
        } else {
            return "Fecha incorrecta. No puedes acceder siendo menor de edad.";
        }
    }

    public boolean validarFrase(String frase) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean validarDig = false;
        boolean primeraLetra = false;
        boolean sacarValor = false;

        do {

            String[] palabras = frase.split("\\s+");
            if (palabras.length == 4) {
                validarDig=true;
            } else {
                System.out.println("Frase incorrecta");
                System.out.println("Introduce nueva frase:");
                frase = sc.nextLine();
            }

            if (validarDig){
                for (int i = 0; i < frase.length(); i++) {
                    char c = frase.charAt(i);
                    if (!Character.isLetter(c) && c != ' ') {
                        System.out.println("La frase no debe contener dÃ­gitos o caracteres especiales.");
                        System.out.println("Frase: ");
                        frase = sc.nextLine();
                    } else {
                        primeraLetra=true;
                    }
                }
            }

            if (primeraLetra) {
                String[] subcadenas = frase.split(" ");

                String nuevaFrase = "";
                for (String subcadena : subcadenas) {
                    char primLetra = subcadena.charAt(0);
                    nuevaFrase += primLetra;
                }

                sacarValor=true;
                if (sacarValor) {
                    String[] valor = frase.split(" ");
                    int[] primValor = new int[Math.min(valor.length, 4)];
                    for (int i = 0; i < primValor.length; i++) {
                        byte[] bytes = valor[i].getBytes();
                        primValor[i] = bytes[bytes.length - 1];
                    }

                    // Sumar todos los valores en primValor
                    int suma = 0;
                    for (int i = 0; i < primValor.length; i++) {
                        suma += primValor[i];
                    }

                    int cociente = suma / 4;
                    int resto = suma % 4;
                    int codigoNumerico = cociente - resto;
                    System.out.println("El codigo es el siguiente: " + nuevaFrase.toUpperCase() + codigoNumerico);
                    salir=true;
                }
            }
        }while (!salir);

        return true;}

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

    public static boolean validarFechas(String fechaEntrada, String fechaSalida) {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);

        try {
            Date fechaActual = new Date();
            Date fechaIni = formatoFecha.parse(fechaEntrada);
            Date fechaFin = formatoFecha.parse(fechaSalida);

            if (fechaIni.getYear() != 123 || fechaFin.getYear() != 123) {
                System.out.println("Las fechas deben ser del año 2023.");
                return false;
            }

            if (fechaIni.before(fechaActual) || fechaFin.before(fechaActual)) {
                System.out.println("Las fechas no pueden ser anteriores a la fecha actual.");
                return false;
            }

            if (fechaFin.before(fechaIni)) {
                System.out.println("La fecha de salida no puede ser anterior a la fecha de entrada.");
                return false;
            }

            return true;

        } catch (ParseException e) {
            System.out.println("Las fechas ingresadas no tienen el formato correcto (dd/mm/yyyy).");
            return false;
        }
    }
    public static String validarTarjetaCredito(String numeroTarjeta) {
        numeroTarjeta = numeroTarjeta.replaceAll(" ", ""); // Eliminar espacios en blanco

        if (!numeroTarjeta.matches("\\d+")) {
            return null; // Contiene caracteres no numéricos
        }

        int suma = 0;
        boolean doble = false;

        for (int i = numeroTarjeta.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroTarjeta.charAt(i));

            if (doble) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }

            suma += digito;
            doble = !doble;
        }

        if (suma % 10 != 0) {
            return null; // Tarjeta inválida según el algoritmo de Luhn
        }

        // Identificar la entidad bancaria por el primer número de la tarjeta
        char primerDigito = numeroTarjeta.charAt(0);

        if (primerDigito == '4') {
            return "Visa";
        } else if (primerDigito == '5') {
            return "Mastercard";
        } else if (primerDigito == '3' && (numeroTarjeta.charAt(1) == '4' || numeroTarjeta.charAt(1) == '7')) {
            return "American Express";
        } else if (primerDigito == '6') {
            return "Discover";
        } else if (primerDigito == '2') {
            return "Mastercard";
        } else {
            return "Desconocida";
        }
    }
}
