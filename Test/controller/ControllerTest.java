package controller;

import ordination.DagligFast;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = Controller.getController();

    @Test
    void opretPNOrdination() {



    }

    @Test
    @DisplayName("DagligFast Base")
    void opretDagligFastOrdination1() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
        LocalDate startDen = LocalDate.of(2024, 03, 18);
        LocalDate slutDen = LocalDate.of(2024, 03, 20);

        DagligFast actual = controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, 1, 1, 1, 1);

        assertTrue(patient.getOrdinationer().contains(actual));
        assertEquals(laegemiddel, actual.getLaegemiddel());
        assertEquals(4.0, actual.doegnDosis());
        assertEquals(12.0, actual.samletDosis());
    }

    @Test
    @DisplayName("DagligFast antalMiddag = 0")
    void opretDagligFastOrdination2() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
        LocalDate startDen = LocalDate.of(2024, 03, 18);
        LocalDate slutDen = LocalDate.of(2024, 03, 20);

        DagligFast actual = controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, 1, 0, 1, 1);

        assertTrue(patient.getOrdinationer().contains(actual));
        assertEquals(laegemiddel, actual.getLaegemiddel());
        assertEquals(3.0, actual.doegnDosis());
        assertEquals(9.0, actual.samletDosis());
    }

    @Test
    @DisplayName("DagligFast samme dag")
    void opretDagligFastOrdination3() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
        LocalDate startDen = LocalDate.of(2024, 03, 22);
        LocalDate slutDen = LocalDate.of(2024, 03, 22);

        DagligFast actual = controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, 1, 1, 1, 1);

        assertTrue(patient.getOrdinationer().contains(actual));
        assertEquals(laegemiddel, actual.getLaegemiddel());
        assertEquals(4.0, actual.doegnDosis());
        assertEquals(4.0, actual.samletDosis());
    }

    @Test
    @DisplayName("DagligFast fejl")
    void opretDagligFastOrdination4() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
        LocalDate startDen = LocalDate.of(2024, 03, 20);
        LocalDate slutDen = LocalDate.of(2024, 03, 18);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, 1, 1, 1, 1));

        assertEquals("Slutdato er efter startdato", exception.getMessage());
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