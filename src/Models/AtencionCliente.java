package Models;


import java.util.Scanner;

public class AtencionCliente {
        String[] preguntas = {"¿Cómo puedo llamar fuera del hotel con el teléfono de la habitación?",
                "¿Es gratis la comida de la nevera de la habitación?"};
        String[] respuestas = {"Por paloma mensajera.",
                "Es gratis si la pagas jeje"};

        public void mostarfaqs(){
                Scanner sc =new Scanner(System.in);
                boolean bolAC = false;
                do {
                        System.out.println("***** PREGUNTAS FRECUENTES *****");
                        for (int i = 0; i < preguntas.length; i++) {
                                System.out.println((i + 1) + ". " + preguntas[i]);
                        }
                        System.out.println("0. Salir");
                        System.out.print("Seleccione una opción: ");
                        String opcion = sc.nextLine().trim();
                        if (opcion.isEmpty()) {
                                System.out.println("Debe ingresar una opción válida");
                                continue;
                        }
                        while (!opcion.matches("\\d+")) {
                                System.out.println("Debe ingresar un número válido");
                                System.out.print("Seleccione una opción: ");
                                opcion = sc.nextLine().trim();
                        }
                        int preguntaopcion = Integer.parseInt(opcion);
                        if (preguntaopcion == 0) {
                                break;
                        } else if (preguntaopcion >= 1 && preguntaopcion <= preguntas.length) {
                                System.out.println(respuestas[preguntaopcion - 1]);
                                bolAC=true;
                        } else {
                                System.out.println("Opción inválida");
                        }
                }while (!bolAC);
        }

}


