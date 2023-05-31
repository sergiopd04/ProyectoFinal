package Controllers;

import Models.Cliente;
import Models.Reservas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestorClientes {

    ArrayList<Cliente> listadoClientes=new ArrayList<>();

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

    public void guardarCliente(String nombre, String apellidos, String email, String dni, LocalDate fechaNacimiento, String codigoAcceso) throws IOException {

        FileWriter escribir = new FileWriter("src/data/clientes.dat");

        Cliente nuevo = new Cliente(nombre,apellidos,email,dni,fechaNacimiento,codigoAcceso);
        escribir.write(nombre + ";" + apellidos + ";" + email + ";" + dni + ";" + fechaNacimiento + ";" + codigoAcceso + ";" + "\n");
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
                LocalDate fecha = LocalDate.parse(clienteArray[4]);
                Cliente cliente = new Cliente(clienteArray[0],clienteArray[1],clienteArray[2],clienteArray[3],fecha,clienteArray[5]);
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


    public void agregarCliente(Cliente cliente) {
        listadoClientes.add(cliente);
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