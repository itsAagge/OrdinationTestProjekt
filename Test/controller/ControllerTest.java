package controller;

import ordination.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = Controller.getController();

    @Test
    @DisplayName("Anders")
    void opretPNOrdination1() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, 10);

        assertEquals(LocalDate.of(2024,03,18), actual.getStartDen());
        assertEquals(LocalDate.of(2024,03,20), actual.getSlutDen());
        assertEquals(parcetamol, actual.getLaegemiddel());
        assertEquals(10, actual.getAntalEnheder());

        assertTrue(AndersHansen.getOrdinationer().contains(actual));


    }

    @Test
    @DisplayName("Antal")
    void opretPNOrdination2() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, 1);

        assertEquals(LocalDate.of(2024,03,18), actual.getStartDen());
        assertEquals(LocalDate.of(2024,03,20), actual.getSlutDen());
        assertEquals(parcetamol, actual.getLaegemiddel());
        assertEquals(1, actual.getAntalEnheder());

        assertTrue(AndersHansen.getOrdinationer().contains(actual));

    }

    @Test
    @DisplayName("samme dag")
    void opretPNOrdination3() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,22), LocalDate.of(2024, 03, 22), AndersHansen, parcetamol, 10);

        assertEquals(LocalDate.of(2024,03,22), actual.getStartDen());
        assertEquals(LocalDate.of(2024,03,22), actual.getSlutDen());
        assertEquals(1, actual.antalDage());
        assertEquals(parcetamol, actual.getLaegemiddel());
        assertEquals(10, actual.getAntalEnheder());

        assertTrue(AndersHansen.getOrdinationer().contains(actual));

    }

    @Test
    @DisplayName("startdato efter slutdato")
    void opretPNOrdination4() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,22), LocalDate.of(2024, 03, 22), AndersHansen, parcetamol, 10);

        Throwable excepction = assertThrows(IllegalArgumentException.class, () -> controller.opretPNOrdination(LocalDate.of(2024,03,20), LocalDate.of(2024, 03, 18), AndersHansen, parcetamol, 10));
        assertEquals("Fejl i dato", excepction.getMessage());

    }

    @Test
    @DisplayName("antal under 0")
    void opretPNOrdination5() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,22), LocalDate.of(2024, 03, 22), AndersHansen, parcetamol, 10);

        Throwable excepction = assertThrows(IllegalArgumentException.class, () -> controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, -1));
        assertEquals("Fejl i mængde" ,excepction.getMessage());

    }


    @Test
    @DisplayName("DagligFast Base")
    void opretDagligFastOrdination1() {
        Patient patient = controller.opretPatient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
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
        Patient patient = controller.opretPatient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
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
        Patient patient = controller.opretPatient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
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
        Patient patient = controller.opretPatient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.4, "Styk");
        LocalDate startDen = LocalDate.of(2024, 03, 20);
        LocalDate slutDen = LocalDate.of(2024, 03, 18);

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> controller.opretDagligFastOrdination(startDen, slutDen, patient, laegemiddel, 1, 1, 1, 1));

        assertEquals("Slutdato er efter startdato", exception.getMessage());
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
    void opretDagligSkaevOrdination01() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 70);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 2, 5, 10, "Pille");
        LocalTime[] klokkeslet = {LocalTime.of(10, 0)};
        double[] antalEnheder = {2};
        DagligSkaev actual = controller.opretDagligSkaevOrdination(
                LocalDate.of(2024,3, 22),
                LocalDate.of(2024,4, 9),
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
    void opretDagligSkaevOrdination02() {
        Patient patient = new Patient("123456-7890", "Anders Hansen", 70);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 2, 5, 10, "Pille");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0),LocalTime.of(12, 0),LocalTime.of(15, 0),LocalTime.of(18, 0)};
        double[] antalEnheder = {1, 2, 1, 2};
        DagligSkaev actual = controller.opretDagligSkaevOrdination(
                LocalDate.of(2024,3, 22),
                LocalDate.of(2024,3, 22),
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
    @DisplayName("Normal test")
    void ordinationPNAnvendt1() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, 10);

        controller.ordinationPNAnvendt(actual, LocalDate.of(2024,03,19));
        controller.ordinationPNAnvendt(actual, LocalDate.of(2024,03,20));
        assertEquals(2, actual.getAntalGangeGivet());

    }

    @Test
    @DisplayName("dato udenfor perioden")
    void ordinationPNAnvendt2() {

        Patient AndersHansen = new Patient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = new Laegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        PN actual = controller.opretPNOrdination(LocalDate.of(2024,03,18), LocalDate.of(2024, 03, 20), AndersHansen, parcetamol, 10);

        Throwable excepction = assertThrows(IllegalArgumentException.class, () -> controller.ordinationPNAnvendt(actual, LocalDate.of(2024,03,22)));

        assertEquals("Fejl: Datoen er ikke i perioden", excepction.getMessage());


    }

    @Test
    @DisplayName("20 kg")
    void anbefaletDosisPrDoegn1() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 20.00);



        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        assertEquals(2, controller.anbefaletDosisPrDoegn(AndersHansen, parcetamol));

    }

    @Test
    @DisplayName("25 kg")
    void anbefaletDosisPrDoegn2() {

        Patient BennChristensen = controller.opretPatient("123456-7890", "Benn Christensen", 25.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        assertEquals(5, controller.anbefaletDosisPrDoegn(BennChristensen, parcetamol));

    }

    @Test
    @DisplayName("80 kg")
    void anbefaletDosisPrDoegn3() {

        Patient PeterHansen = controller.opretPatient("123456-7890", "Peter Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        assertEquals(16, controller.anbefaletDosisPrDoegn(PeterHansen, parcetamol));

    }

    @Test
    @DisplayName("120 kg")
    void anbefaletDosisPrDoegn4() {

        Patient LarsJensen = controller.opretPatient("123456-7890", "Lars Jensen", 120.00);        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        assertEquals(24, controller.anbefaletDosisPrDoegn(LarsJensen, parcetamol));

    }

    @Test
    @DisplayName("140 kg")
    void anbefaletDosisPrDoegn5() {

        Patient MogensHansen = controller.opretPatient("123456-7890", "Mogens Hansen", 140.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        assertEquals(56, controller.anbefaletDosisPrDoegn(MogensHansen, parcetamol));

    }

    @Test
    @DisplayName("null test")
    void anbefaletDosisPrDoegn6() {

        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        Throwable exception = assertThrows(NullPointerException.class, () -> controller.anbefaletDosisPrDoegn(null, parcetamol));

        assertEquals("Patient eller lægemiddel må ikke være null", exception.getMessage());



    }


    @Test
    @DisplayName("Ordinationer pr vægt - 65-100 Wegovy")
    void antalOrdinationerPrVægtPrLægemiddel1() {
        Laegemiddel paracetamol = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");
        Laegemiddel wegovy = controller.opretLaegemiddel("Wegovy", 0.1, 0.2, 0.3, "Styk");
        Patient anders = controller.opretPatient("123456-7890", "Anders Hansen", 20);
        Patient peter = controller.opretPatient("123456-7890", "Peter Hansen", 80);
        Patient mogens = controller.opretPatient("123456-7890", "Mogens Hansen", 140);
        Patient nils = controller.opretPatient("123456-7890", "Nils Værker", 20);
        Ordination andersOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), anders, wegovy, 1, 1, 1, 1);
        Ordination peterOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), peter, wegovy, 1, 1, 1, 1);
        Ordination mogensOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), mogens, paracetamol, 1, 1, 1, 1);
        Ordination nilsOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), nils, paracetamol, 1, 1, 1, 1);

        int actual = controller.antalOrdinationerPrVægtPrLægemiddel(65, 100, wegovy);

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Ordinationer pr vægt - 1-200 Wegovy")
    void antalOrdinationerPrVægtPrLægemiddel2() {
        Laegemiddel paracetamol = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");
        Laegemiddel wegovy = controller.opretLaegemiddel("Wegovy", 0.1, 0.2, 0.3, "Styk");
        Patient anders = controller.opretPatient("123456-7890", "Anders Hansen", 20);
        Patient peter = controller.opretPatient("123456-7890", "Peter Hansen", 80);
        Patient mogens = controller.opretPatient("123456-7890", "Mogens Hansen", 140);
        Patient nils = controller.opretPatient("123456-7890", "Nils Værker", 20);
        Ordination andersOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), anders, wegovy, 1, 1, 1, 1);
        Ordination peterOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), peter, wegovy, 1, 1, 1, 1);
        Ordination mogensOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), mogens, paracetamol, 1, 1, 1, 1);
        Ordination nilsOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), nils, paracetamol, 1, 1, 1, 1);

        int actual = controller.antalOrdinationerPrVægtPrLægemiddel(1, 200, wegovy);

        assertEquals(2, actual);
    }

    @Test
    @DisplayName("Ordinationer pr vægt - 20-100 Paracetamol")
    void antalOrdinationerPrVægtPrLægemiddel3() {
        Laegemiddel paracetamol = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");
        Laegemiddel wegovy = controller.opretLaegemiddel("Wegovy", 0.1, 0.2, 0.3, "Styk");
        Patient anders = controller.opretPatient("123456-7890", "Anders Hansen", 20);
        Patient peter = controller.opretPatient("123456-7890", "Peter Hansen", 80);
        Patient mogens = controller.opretPatient("123456-7890", "Mogens Hansen", 140);
        Patient nils = controller.opretPatient("123456-7890", "Nils Værker", 20);
        Ordination andersOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), anders, wegovy, 1, 1, 1, 1);
        Ordination peterOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), peter, wegovy, 1, 1, 1, 1);
        Ordination mogensOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), mogens, paracetamol, 1, 1, 1, 1);
        Ordination nilsOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), nils, paracetamol, 1, 1, 1, 1);

        int actual = controller.antalOrdinationerPrVægtPrLægemiddel(20, 100, paracetamol);

        assertEquals(1, actual);
    }

    @Test
    @DisplayName("Ordinationer pr vægt - 1-200 Paracetamol")
    void antalOrdinationerPrVægtPrLægemiddel4() {
        Laegemiddel paracetamol = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");
        Laegemiddel wegovy = controller.opretLaegemiddel("Wegovy", 0.1, 0.2, 0.3, "Styk");
        Patient anders = controller.opretPatient("123456-7890", "Anders Hansen", 20);
        Patient peter = controller.opretPatient("123456-7890", "Peter Hansen", 80);
        Patient mogens = controller.opretPatient("123456-7890", "Mogens Hansen", 140);
        Patient nils = controller.opretPatient("123456-7890", "Nils Værker", 20);
        Ordination andersOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), anders, wegovy, 1, 1, 1, 1);
        Ordination peterOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), peter, wegovy, 1, 1, 1, 1);
        Ordination mogensOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), mogens, paracetamol, 1, 1, 1, 1);
        Ordination nilsOrdination = controller.opretDagligFastOrdination(LocalDate.now(), LocalDate.now(), nils, paracetamol, 1, 1, 1, 1);

        int actual = controller.antalOrdinationerPrVægtPrLægemiddel(1, 200, paracetamol);

        assertEquals(2, actual);
    }
}