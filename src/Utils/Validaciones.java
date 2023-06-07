package Utils;

import java.sql.SQLOutput;
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

    public static boolean validarNombre(String nombre) {
        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        do {

            if (nombre.isEmpty()) {
                System.out.println("Nombre vacío. Vuelva a intentarlo.");
            }

            if (!nombre.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{3,}(\\s+[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*")) {
                System.out.println("El nombre debe tener al menos 3 caracteres y contener solo letras. Inténtalo de nuevo.");
                nombre = sc.nextLine();
            } else {
                salir=true;
            }

        } while (!salir);

        return true;
    }


    public static boolean validarApellidos(String apellidos) {
        Scanner sc = new Scanner(System.in);

        boolean salir = false;

        do {

            if (apellidos.isEmpty()) {
                System.out.println("Apellido vacío. Vuelva a intentarlo.");
            }

            if (!apellidos.matches("[a-zA-ZñÑáéíóúÁÉÍÓÚ]{3,}(\\s+[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*")) {
                System.out.println("El apellido debe tener al menos 3 caracteres y contener solo letras. Inténtalo de nuevo.");
                apellidos = sc.nextLine();
            } else {
                salir=true;
            }

        } while (!salir);

        return true;
    }

    public static boolean validarTelefono(String tlf) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        do {

            if (tlf.trim().length() == 0) { // verifica si el teléfono está vacío
                System.out.println("Debe ingresar un número de teléfono");
                System.out.print("Teléfono: ");
                tlf = sc.nextLine().replace(" ", "");
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

    public static boolean validarCorreo(String correo) {
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
    return true;
    }

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

    public static boolean calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNacimiento, fechaActual);
        int edad = periodo.getYears();

        if (edad >= 18) {
            System.out.println("Fecha correcta. Eres mayor de edad.");
            return true;
        } else {
            System.out.println("Fecha incorrecta. No puedes acceder siendo menor de edad.");
            return false;
        }
    }

    static String codigoFinal;

    public static String getCodigoFinal() {
        return codigoFinal;
    }

    public static boolean validarFrase(String frase) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean validarDig = false;
        boolean primeraLetra = false;
        boolean sacarValor = false;

        do {

            String[] palabras = frase.split(" ");
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
                        System.out.println("La frase no debe contener dígitos o caracteres especiales.");
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
                    System.out.println("El código es el siguiente: " + nuevaFrase.toUpperCase() + codigoNumerico);
                    codigoFinal = nuevaFrase.toUpperCase() + codigoNumerico;
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

    public static boolean Tarjeta(String NumeroTarjeta) {

        if (NumeroTarjeta.trim().isEmpty()) {
            return false;
        }


        if (!NumeroTarjeta.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        boolean numero = false;
        for (int i = NumeroTarjeta.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(NumeroTarjeta.substring(i, i + 1));
            if (numero) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            numero = !numero;
        }

        if (sum % 10 == 0) {
            if (NumeroTarjeta.matches("^4[0-9]{12}(?:[0-9]{3})?$")) {
                System.out.println("VISA");
            } else if (NumeroTarjeta.matches("^5[1-5][0-9]{14}$")) {
                System.out.println("MASTERCARD");
            } else if (NumeroTarjeta.matches("^3[47][0-9]{13}$")) {
                System.out.println("AMEX");
            } else if (NumeroTarjeta.matches("^3(?:0[0-5]|[68][0-9])[0-9]{11}$")) {
                System.out.println("DINERS CLUB");
            } else if (NumeroTarjeta.matches("^6(?:011|5[0-9]{2})[0-9]{12}$")) {
                System.out.println("DISCOVER");
            }
            return true;
        } else {
            return false;
        }
    }

    public static void validarBizum(){
        Scanner sc = new Scanner(System.in);

        String confirmar;

        System.out.println("Has elegido la opción de pago con Bizum.");
        System.out.println("El número de teléfono disponible al que realizar el pago es el siguiente: +34 685 04 05 47");

        do {
            System.out.println("Una vez hayas realizado, confirma que has realizado el pago correctamente mediante S/N");
            confirmar = sc.nextLine().toUpperCase();

            if (confirmar.equals("S")) {
                System.out.println("Pago realizado correctamente. Gracias por tu compra.");
                break;
            } else if (confirmar.equals("N")) {
                System.out.println("El pago no se ha realizado. Por favor, intenta nuevamente.");
                break;
            } else {
                System.out.println("Opción no válida. Por favor, introduce 'S' para confirmar o 'N' para cancelar el pago.");
            }
        } while (true);
    }

    public static boolean validarDNI(String dni) {
        if (dni.length() != 9) {
            System.out.println("El DNI debe tener 9 caracteres.");
            return false;
        }

        // Verificar que los primeros 8 caracteres son dígitos numéricos
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                System.out.println("Los primeros 8 caracteres del DNI deben ser dígitos numéricos.");
                return false;
            }
        }

        // Verificar que el último carácter es una letra
        char lastCharacter = dni.charAt(8);
        if (!Character.isLetter(lastCharacter)) {
            System.out.println("El último carácter del DNI debe ser una letra.");
            return false;
        }

        // Si todas las verificaciones pasan, el DNI es válido
        return true;
    }

}
