package ordination;

import org.junit.jupiter.api.Test;
import controller.Controller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class DagligFastTest {
    Controller controller = Controller.getController();
    @Test
    void samletDosis() {
        Patient pas = new Patient("123456-7890", "Anders Handsen", 85);
        Laegemiddel middel = new Laegemiddel("Paracetamol",2, 2, 2, "Pille");
        DagligFast ordination = controller.opretDagligFastOrdination(LocalDate.of(2024,7,13),LocalDate.of(2024,7,19), pas, middel,1,1,1,1);
        assertEquals(28, ordination.samletDosis());


    }

    @Test
    void doegnDosis() {

    }
}