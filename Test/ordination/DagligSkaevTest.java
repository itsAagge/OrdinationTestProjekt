package ordination;

import controller.Controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    Controller controller = Controller.getController();

    @Test
    void opretDosis() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0),LocalTime.of(12, 0),LocalTime.of(15, 0),LocalTime.of(18, 0)};
        double[] antalEnheder = {1, 1, 1, 1};

        DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,7, 13), LocalDate.of(2024,7, 19), AndersHansen, parcetamol, klokkeslet, antalEnheder);

        actual.opretDosis(LocalTime.of(23,00), 2);
        assertEquals(5, actual.getDoser().size());


    }

    @Test
    @DisplayName("forskellige datoer")
    void samletDosis1() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0),LocalTime.of(12, 0),LocalTime.of(15, 0),LocalTime.of(18, 0)};
        double[] antalEnheder = {1, 1, 1, 1};

        DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,7, 13), LocalDate.of(2024,7, 19), AndersHansen, parcetamol, klokkeslet, antalEnheder);

        assertEquals(28, actual.samletDosis());

    }

    @Test
    @DisplayName("samme dato")
    void samletDosis2() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0),LocalTime.of(12, 0),LocalTime.of(15, 0),LocalTime.of(18, 0)};
        double[] antalEnheder = {1, 1, 1, 1};

        DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,7, 13), LocalDate.of(2024,7, 13), AndersHansen, parcetamol, klokkeslet, antalEnheder);

        assertEquals(4, actual.samletDosis());

    }

    @Test
    @DisplayName("samme dato")
    void samletDosis3() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        LocalTime[] klokkeslet = {LocalTime.of(10, 0)};
        double[] antalEnheder = {1};

        DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,7, 13), LocalDate.of(2024,7, 19), AndersHansen, parcetamol, klokkeslet, antalEnheder);

        assertEquals(7, actual.samletDosis());

    }



    @Test
    @DisplayName("flere")
    void doegnDosis1() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0),LocalTime.of(12, 0),LocalTime.of(15, 0),LocalTime.of(18, 0)};
        double[] antalEnheder = {1, 1, 1, 1};

        DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,7, 13), LocalDate.of(2024,7, 19), AndersHansen, parcetamol, klokkeslet, antalEnheder);

        assertEquals(4, actual.doegnDosis());

    }

    @Test
    @DisplayName("samme dato")
    void doegnDosis2() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");
        LocalTime[] klokkeslet = {LocalTime.of(9, 0)};
        double[] antalEnheder = {1};

        DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,7, 13), LocalDate.of(2024,7, 19), AndersHansen, parcetamol, klokkeslet, antalEnheder);

        assertEquals(1, actual.doegnDosis());

    }




}