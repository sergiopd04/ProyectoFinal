package Controllers;

import Models.Cliente;
import Utils.Validaciones;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorClientes {


    static ArrayList<Cliente> listadoClientes = new ArrayList<>();

    public GestorClientes() {

    }

    ArrayList<Cliente> getListadoClientes() {
        return listadoClientes;
    }

    public void setListadoClientes(ArrayList<Cliente> listadoClientes) {
        this.listadoClientes = listadoClientes;
    }

    public void mostrarClientes(String nombre, String apellidos, String email, String dni) {
        System.out.println(nombre);
        System.out.println(apellidos);
        System.out.println(email);
        System.out.println(dni);
    }

    public static void guardarCliente(String nombre, String apellidos, boolean rol, String email, String dni, LocalDate fecha, String codigoAcceso) throws IOException {

        FileWriter escribir = new FileWriter("src/data/clientes.dat", true);

        Cliente nuevo = new Cliente(nombre, apellidos, rol, email, dni, fecha, codigoAcceso);
        escribir.write(nombre + ";" + apellidos + ";" + rol + ";" + email + ";" + dni + ";" + fecha + ";" + codigoAcceso + ";" + "\n");
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
                if (clienteArray.length >= 6) {
                    LocalDate fecha = LocalDate.parse(clienteArray[5]);
                    boolean rol = Boolean.parseBoolean(clienteArray[2]);
                    Cliente cliente = new Cliente(clienteArray[0],clienteArray[1],rol,clienteArray[3],clienteArray[4],fecha,clienteArray[6]);
                    listadoClientes.add(cliente);
                }
            }
        }
    }

    public Cliente inicioSesion(String email, String codigo) throws Exception {

        for (Cliente cliente : listadoClientes) {
            if (cliente.getCodigo_acceso().equals(codigo) && cliente.getEmail().equals(email)) {
                System.out.println("Bienvenido");
                return cliente;
            }
        }
        throw new Exception("Cliente no encontrado");
    }


    public void agregarCliente() {
        Scanner sc = new Scanner(System.in);

        String dni, fecha, fraseControl;

        boolean bnombre = false;
        boolean bapellido = false;
        boolean bcorreo = false;
        boolean btelefono = false;
        boolean bdni = false;
        boolean bfechanacimiento = false;
        boolean bfrasecontrol = false;

        String nombre = "";
        String apellidos = "";
        String email = "";
        String tlf = "";
        LocalDate fechaNacimiento = null;

        do {
            System.out.println("Nombre: ");
            nombre = sc.nextLine();
        } while (!Validaciones.validarNombre(nombre));
        bnombre = true;

        System.out.println("Apellidos: ");
        apellidos = sc.nextLine().toUpperCase();
        Validaciones.validarApellidos(apellidos);
        bapellido = true;

        System.out.println("Correo: ");
        email = sc.nextLine();
        Validaciones.validarCorreo(email);
        bcorreo = true;

        System.out.println("Teléfono: ");
        tlf = sc.nextLine();
        Validaciones.validarTelefono(tlf);
        btelefono = true;

        do {
            System.out.print("Ingrese un número de DNI: ");
            dni = sc.nextLine();
        } while (!Validaciones.validarDNI(dni));
        bdni = true;

        do {
            System.out.print("Ingresa tu fecha de nacimiento (dd/mm/yyyy): ");
            fecha = sc.nextLine();
        } while (!Validaciones.validarFecha(fecha));
        fechaNacimiento = Validaciones.convertirFecha(fecha);
        bfechanacimiento = true;

        if (Validaciones.calcularEdad(fechaNacimiento)) {
            do {
                System.out.print("Frase de Control (4 palabras separadas por espacios): ");
                fraseControl = sc.nextLine();
            } while (!Validaciones.validarFrase(fraseControl));
            bfrasecontrol = true;
        }

        if (bnombre && bapellido && bcorreo && bdni && btelefono && bfechanacimiento && bfrasecontrol) {
            try {
                guardarCliente(nombre, apellidos, false, email, dni, fechaNacimiento, Validaciones.getCodigoFinal());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Cliente agregado correctamente.\n");
        }
    }

    public void buscarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del cliente: ");
        String dni = scanner.nextLine();

        for (Cliente cliente : listadoClientes) {
            if (cliente.getDni().equals(dni)) {
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Apellido: " + cliente.getApellidos());
                System.out.println("Email: " + cliente.getEmail());
                System.out.println("DNI: " + cliente.getDni());
                System.out.println("Fecha de Nacimiento: " + cliente.getFecha_nacimiento());
                System.out.println("Código de acceso: " + cliente.getCodigo_acceso());
                return;
            }
        }

        System.out.println("No se encontró ningún cliente con el DNI proporcionado.");
    }

    public void eliminarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        String dni = scanner.nextLine();

        String archivo = "src/data/clientes.dat";

        try {
            RandomAccessFile raf = new RandomAccessFile(archivo, "rw");

            boolean encontrado = false;
            long posicion = 0;
            String linea;

            while ((linea = raf.readLine()) != null) {
                String[] datosCliente = linea.split(";");
                if (datosCliente[4].equals(dni)) {
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
                    System.out.println("Cliente eliminado exitosamente.");
                } else {
                    raf.setLength(posicion);
                    System.out.println("Cliente eliminado exitosamente.");
                }
            } else {
                System.out.println("No se encontró ningún cliente con el DNI proporcionado.");
            }

            raf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modificarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el DNI del cliente a modificar: ");
        String dni = scanner.nextLine();

        for (Cliente cliente : listadoClientes) {
            if (cliente.getDni().equals(dni)) {
                System.out.println("Cliente encontrado. ¿Qué atributo desea modificar?");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Email");
                System.out.println("4. Fecha de Nacimiento");
                System.out.println("5. Código de acceso");
                System.out.println("0. Cancelar");

                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Ingrese el nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        Validaciones.validarNombre(nuevoNombre);
                        cliente.setNombre(nuevoNombre);
                        break;
                    case "2":
                        System.out.print("Ingrese el nuevo apellido: ");
                        String nuevoApellido = scanner.nextLine();
                        Validaciones.validarApellidos(nuevoApellido);
                        cliente.setApellidos(nuevoApellido);
                        break;
                    case "3":
                        System.out.print("Ingrese el nuevo email: ");
                        String nuevoEmail = scanner.nextLine();
                        Validaciones.validarCorreo(nuevoEmail);
                        cliente.setEmail(nuevoEmail);
                        break;
                    case "4":
                        String fecha;
                        do {
                            System.out.print("Ingresa tu fecha de nacimiento (dd/mm/yyyy): ");
                            fecha = scanner.nextLine();
                        } while (!Validaciones.validarFecha(fecha));
                        LocalDate fechaNacimiento = Validaciones.convertirFecha(fecha);
                        cliente.setFecha_nacimiento(fechaNacimiento);
                        break;
                    case "5":
                        System.out.print("Ingrese el nuevo código de acceso: ");
                        String nuevoCodigoAcceso = scanner.nextLine();
                        cliente.setCodigo_acceso(nuevoCodigoAcceso);
                        break;
                    case "0":
                        System.out.println("Operación cancelada. No se realizaron modificaciones.");
                        return;
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
}