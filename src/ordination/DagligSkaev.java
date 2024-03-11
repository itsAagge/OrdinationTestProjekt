package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
        super(startDen, slutDen, laegemiddel);
    }


    public void opretDosis(LocalTime tid, double antal) {
        // TODO
    }

    public ArrayList<Dosis> getDoser() {
        return doser;
    }

    @Override
    public double samletDosis() {
        // TODO
        return 0;
    }

    @Override
    public double doegnDosis() {
        // TODO
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }


}
