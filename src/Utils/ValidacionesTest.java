package Utils;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionesTest {

    //Atributos a comprobar
    String dniIncorrecto="205210901";
    String dniCorrecto="20521090F";


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    //Comprobar que sea necesario una letra al final.
    @org.junit.jupiter.api.Test
    void validarCorreoIncorrecto() {
        assertFalse(Validaciones.validarDNI(dniIncorrecto));

    }

    //Comprobar que no muestre mensaje de error al introducir dni correcto.
    @org.junit.jupiter.api.Test
    void validarFechaCorrecto() {
        assertTrue(Validaciones.validarDNI(dniCorrecto));

    }
}