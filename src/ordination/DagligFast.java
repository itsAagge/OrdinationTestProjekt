package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {

    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }

    public Dosis[] getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        // Gang antal dage fra start dato til slut dato
        // med doegnDosis()
        double antalDage = ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1;
        double samletDosis = antalDage * doegnDosis();
        return samletDosis;
    }

    @Override
    public double doegnDosis() {
        double samlet = 0;
        for(Dosis dose : doser) {
            samlet += dose.getAntal();
        }
        return samlet;
    }

    @Override
    public String getType() {
        return getLaegemiddel().getEnhed();
    }
    // TODO
}
