package restaurantkassensystem.Beilagen;

import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Beilagenklasse gebratene Nudeln
 * @author dimitrova
 * @version 1.1
 */
public class GebrateneNudeln extends Beilagen {
    private double preis;
    
    public GebrateneNudeln(Hauptkomponente gericht, double preis){
        super(gericht);
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return gericht.getPreis() + preis;
    }

    @Override
    public String getBeschreibung() {
        return gericht.getBeschreibung() + ", gebratene Nudeln";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
