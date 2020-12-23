package restaurantkassensystem.Beilagen;

import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Beilagenklasse Eierreis
 * @author dimitrova
 * @version 1.1
 */
public class Eierreis extends Beilagen {
    private double preis;
    
    public Eierreis(Hauptkomponente gericht, double preis){
        super(gericht);
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return gericht.getPreis() + preis;
    }

    @Override
    public String getBeschreibung() {
        return gericht.getBeschreibung() + ", Eierreis";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;
    }
}
