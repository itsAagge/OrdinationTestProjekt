package controller;

import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = Controller.getController();

    @Test
    void opretPNOrdination() {



    }

    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkaevOrdination() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 70);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 2, 5, 10, "Pille");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0),LocalTime.of(12, 0),LocalTime.of(15, 0),LocalTime.of(18, 0)};
        double[] antalEnheder = {1, 2, 1, 2};
        DagligSkaev actual = controller.opretDagligSkaevOrdination(
            LocalDate.of(2024,3, 18),
            LocalDate.of(2024,3, 20),
            patient,
            laegemiddel,
            klokkeslet,
            antalEnheder
        );
            assertTrue(antalEnheder.length == klokkeslet.length);
            assertTrue(actual.getStartDen().isBefore(actual.getSlutDen()));
            // Input er oprettet
            assertNotNull(patient);
            assertEquals(laegemiddel, actual.getLaegemiddel());
            assertTrue(patient.getOrdinationer().contains(actual));
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