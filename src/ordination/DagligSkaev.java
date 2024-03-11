package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }


    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }

    public ArrayList<Dosis> getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        double dage = ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
        return doegnDosis() * (dage + 1);
    }

    @Override
    public double doegnDosis() {
        double samlet = 0;
        for (Dosis dosis : doser) {
            samlet += dosis.getAntal();
        }
        return samlet;
    }

    @Override
    public String getType() {
        return getLaegemiddel().getEnhed();
    }

}
