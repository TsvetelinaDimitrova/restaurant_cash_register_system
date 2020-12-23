package restaurantkassensystem.Beilagen;

import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Beilagenklasse rote Currysauce
 * @author dimitrova
 * @version 1.1
 */
public class RoteCurrysauce extends Beilagen {
    private double preis;
    
    public RoteCurrysauce(Hauptkomponente gericht, double preis){
        super(gericht);
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return gericht.getPreis() + preis;
    }

    @Override
    public String getBeschreibung() {
        return gericht.getBeschreibung() + ", rote Currysauce";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
