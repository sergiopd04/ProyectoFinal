package Utils;

import static org.junit.jupiter.api.Assertions.*;

class ValidacionesTest {

    //Atributos a comprobar
    String dniIncorrecto="205210901";
    String dniCorrecto="20521090F";
    String telefonoIncorrecto="68504054s";
    String telefonoCorrecto="685040547";


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    //Comprobar que sea necesario una letra al final.
    @org.junit.jupiter.api.Test
    void validarDNIIncorrecto() {
        assertFalse(Validaciones.validarDNI(dniIncorrecto));

    }

    //Comprobar que no muestre mensaje de error al introducir dni correcto.
    @org.junit.jupiter.api.Test
    void validarDNICorrecto() {
        assertTrue(Validaciones.validarDNI(dniCorrecto));
    }

    //Comprobar que no permita letras dentro del String.
    @org.junit.jupiter.api.Test
    void validarTelefonoIncorrecto() {
        assertFalse(Validaciones.validarTelefono(telefonoIncorrecto));

    }

    //Comprobar que no muestre mensaje de error al introducir dni correcto.
    @org.junit.jupiter.api.Test
    void validarTelefonoCorrecto() {
        assertTrue(Validaciones.validarTelefono(telefonoCorrecto));
    }
}