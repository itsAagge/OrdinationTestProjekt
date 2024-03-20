package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    PN ordination;

    Controller controller = Controller.getController();

    @BeforeEach
    void setUp() {
        Patient patient = controller.opretPatient("123456-7890", "Anders Hansen", 80);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");
        ordination = controller.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(20), patient, laegemiddel, 2);
        controller.ordinationPNAnvendt(ordination, LocalDate.now().plusDays(3));
        controller.ordinationPNAnvendt(ordination, LocalDate.now().plusDays(7));
    }

    @Test
    void doegnDosis() {
        double expected = 0.8;
        double actual = ordination.doegnDosis();

        assertEquals(expected, actual);
    }

    @Test
    void samletDosis() {
        double expected = 4.0;
        double actual = ordination.samletDosis();

        assertEquals(expected, actual);
    }
}