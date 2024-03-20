package controller;

import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = Controller.getController();

    @Test
    void opretPNOrdination1() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, 10);

        assertEquals(LocalDate.of(2024,03,18), actual.getStartDen());
        assertEquals(LocalDate.of(2024,03,20), actual.getSlutDen());
        assertEquals(parcetamol, actual.getLaegemiddel());
        assertEquals(10, actual.getAntalEnheder());

        assertTrue(AndersHansen.getOrdinationer().contains(actual));


    }

    @Test
    void opretPNOrdination2() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, 1);

        assertEquals(LocalDate.of(2024,03,18), actual.getStartDen());
        assertEquals(LocalDate.of(2024,03,20), actual.getSlutDen());
        assertEquals(parcetamol, actual.getLaegemiddel());
        assertEquals(1, actual.getAntalEnheder());

        assertTrue(AndersHansen.getOrdinationer().contains(actual));

    }

    @Test
    void opretPNOrdination3() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,22), LocalDate.of(2024, 03, 22), AndersHansen, parcetamol, 10);

        assertEquals(LocalDate.of(2024,03,22), actual.getStartDen());
        assertEquals(LocalDate.of(2024,03,22), actual.getSlutDen());
        assertEquals(1, actual.antalDage());
        assertEquals(parcetamol, actual.getLaegemiddel());
        assertEquals(10, actual.getAntalEnheder());

        assertTrue(AndersHansen.getOrdinationer().contains(actual));

    }

    @Test
    void opretPNOrdination4() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,22), LocalDate.of(2024, 03, 22), AndersHansen, parcetamol, 10);

        Throwable excepction = assertThrows(IllegalArgumentException.class, () -> controller.opretPNOrdination(LocalDate.of(2024,03,20), LocalDate.of(2024, 03, 18), AndersHansen, parcetamol, 10));

    }

    @Test
    void opretPNOrdination5() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,22), LocalDate.of(2024, 03, 22), AndersHansen, parcetamol, 10);

        Throwable excepction = assertThrows(IllegalArgumentException.class, () -> controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, -1));

    }


    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkaevOrdination() {
    }

    @Test
    void ordinationPNAnvendt() {
    }

    @Test
    void anbefaletDosisPrDoegn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }
}