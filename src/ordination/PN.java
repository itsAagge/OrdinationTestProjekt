package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    ArrayList<LocalDate> tidspunkter = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDen, slutDen, laegemiddel);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        return tidspunkter.add(givesDen);
    }

    public double doegnDosis() {
        LocalDate førsteGivning = getSlutDen();
        LocalDate sidsteGivning = getStartDen();
        for (LocalDate dato : tidspunkter) {
            if (førsteGivning.isBefore(dato)) {
                førsteGivning = dato;
            }
            if (sidsteGivning.isAfter(dato)) {
                sidsteGivning = dato;
            }
        }
        return samletDosis() / (ChronoUnit.DAYS.between(førsteGivning, sidsteGivning) + 1);
    }

    @Override
    public String getType() {
        return getLaegemiddel().getEnhed();
    }


    public double samletDosis() {
        return antalEnheder * tidspunkter.size();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return tidspunkter.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
