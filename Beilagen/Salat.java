package restaurantkassensystem.Beilagen;

import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Beilagenklasse Salat
 * @author dimitrova
 * @version 1.1
 */
public class Salat extends Beilagen {
    private double preis;
    
    public Salat(Hauptkomponente gericht, double preis){
        super(gericht);
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return gericht.getPreis() + preis;
    }

    @Override
    public String getBeschreibung() {
        return gericht.getBeschreibung() + ", Salat";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
