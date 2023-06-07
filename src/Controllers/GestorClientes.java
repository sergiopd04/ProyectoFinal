package Controllers;

import Models.Cliente;
import Models.Reservas;
import Utils.Validaciones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorClientes {


    static ArrayList<Cliente> listadoClientes=new ArrayList<>();

    public GestorClientes(){

    }

    ArrayList<Cliente> getListadoClientes() {
        return listadoClientes;
    }

    public void setListadoClientes(ArrayList<Cliente> listadoClientes) {
        this.listadoClientes = listadoClientes;
    }

    public void mostrarClientes (String nombre, String apellidos, String email, String dni) {
        System.out.println(nombre);
        System.out.println(apellidos);
        System.out.println(email);
        System.out.println(dni);
    }

    public static void guardarCliente(String nombre, String apellidos,boolean rol, String email, String dni, LocalDate fechaNacimiento, String codigoAcceso) throws IOException {

        FileWriter escribir = new FileWriter("src/data/clientes.dat",true);

        Cliente nuevo = new Cliente(nombre,apellidos,rol,email,dni,fechaNacimiento,codigoAcceso);
        escribir.write(nombre + ";" + apellidos + ";" + rol + ";" + email + ";" + dni + ";" + fechaNacimiento + ";" + codigoAcceso + ";" + "\n");
        escribir.flush();
        escribir.close();
        listadoClientes.add(nuevo);
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
                LocalDate fecha = LocalDate.parse(clienteArray[5]);
                boolean rol = Boolean.parseBoolean(clienteArray[2]);
                Cliente cliente = new Cliente(clienteArray[0],clienteArray[1],rol,clienteArray[3],clienteArray[4],fecha,clienteArray[6]);
                listadoClientes.add(cliente);
            }
        }

    }

    public Cliente inicioSesion(String email, String codigo) throws Exception {

        for (Cliente cliente: listadoClientes) {
            if (cliente.getCodigo_acceso().equals(codigo) && cliente.getEmail().equals(email)) {
                System.out.println("Bienvenido");
                return cliente;
            }
        }
        throw new Exception("Cliente no encontrado");
    }


    public void agregarCliente() {
        Scanner sc = new Scanner(System.in);

        String dni,fecha,fraseControl;

        System.out.println("Nombre: ");
        String nombre=sc.nextLine();
        Validaciones.validarNombre(nombre);
        System.out.println("Apellidos: ");
        String apellidos=sc.nextLine().toUpperCase();
        Validaciones.validarApellidos(apellidos);
        System.out.println("Correo: ");
        String email=sc.nextLine();
        Validaciones.validarCorreo(email);
        System.out.println("Teléfono: ");
        String tlf=sc.nextLine();
        Validaciones.validarTelefono(tlf);
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
            Validaciones.validarFrase(fraseControl);
        }

        try {
            guardarCliente(nombre, apellidos, false, email, dni, fechaNacimiento, Validaciones.getCodigoFinal());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Cliente agregado correctamente. \n");
    }

    public boolean buscarCliente(Cliente cliente) {
        for (Cliente i : listadoClientes) {
            if (i.getDni().equals(cliente.getDni())) {
                return true;
            }
        }
        return false;
    }

    /*public void generarClientesBase (ArrayList<Cliente> listadoClientes){
        Cliente cliente1 = new Cliente("Sergio","Patiño","sergio@gmail.com","20520190F","27/06/2004","2052");
        Cliente cliente2 = new Cliente("Pablo","Feo","pablofeo@gmail.com","20521090A","01/01/1999","1090");
        Cliente cliente3 = new Cliente("Paca","Laca","pacalaca@gmail.com","20521090G","10/10/2003","9010");
        Cliente cliente4 = new Cliente("Antonio","Cañas","antoniotiracas@gmail.com","20521090Y","26/04/1987","8040");
        Cliente cliente5 = new Cliente("Juakin","Thefast","thefaster@gmail.com","20521090L","18/02/1900","4060");
        Cliente cliente6 = new Cliente("Xusky","Xusky","xusky@gmail.com","205201090K","20/04/1994","4030");
        Cliente cliente7 = new Cliente("William","Willy","willy@gmail.com","20521090I","27/06/1996","2000");
    }
     */
}