package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    Controller controller = Controller.getController();

    @Test
    void opretDosis() {

    }

    @Test
    void samletDosis() {

        Patient AndersHansen = controller.opretPatient("123456-7890", "Anders Hansen", 80.00);
        Laegemiddel parcetamol = controller.opretLaegemiddel("Paracetamol", 0.1,0.2,0.4, "styk");

        //DagligSkaev actual = controller.opretDagligSkaevOrdination(LocalDate.of(2024,3, 18), LocalDate.of(2024,3, 20), AndersHansen, parcetamol, klokkeslet, antalEnheder);

    }

    @Test
    void doegnDosis() {
    }
}