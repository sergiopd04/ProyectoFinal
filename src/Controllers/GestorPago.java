package Controllers;

import Models.Pago;

import java.util.ArrayList;

public class GestorPago {

    ArrayList<Pago> desgloseFactura = new ArrayList<>();

    public ArrayList<Pago> getDesgloseFactura() {
        return desgloseFactura;
    }

    public void setDesgloseFactura(ArrayList<Pago> desgloseFactura) {
        this.desgloseFactura = desgloseFactura;
    }

    public GestorPago(ArrayList desgloseFactura){
        this.desgloseFactura=desgloseFactura;
        generarDesglose();
    }


    public void generarDesglose(){
        Pago desglose1 = new Pago("Sergio","Patiño","27/04/2004","111","hola",125);
        Pago desglose2 = new Pago("Sergio","Pat","27/04/2004","222","paca",145);
        Pago desglose3 = new Pago("Pablo","Feo","27/04/2004","333","como",160);
        Pago desglose4 = new Pago("Juakin","La juaki","27/04/1004","444","estas",10);
    }

    public void mostrarPago(String nombre, String apellidos, String fechaFactura, String codigoFactura, String descripcion, double precio) {
        double iva = precio * 0.21;
        double subtotal = precio - iva;
        System.out.println("Factura para: " + nombre + " " + apellidos);
        System.out.println("Fecha: " + fechaFactura);
        System.out.println("Código de factura: " + codigoFactura);
        System.out.println("Descripción o concepto: " + descripcion);
        System.out.println("Precio total: €" + precio);
        System.out.println("Subtotal: €" + subtotal);
        System.out.println("IVA (21%): €" + iva);
    }
}
