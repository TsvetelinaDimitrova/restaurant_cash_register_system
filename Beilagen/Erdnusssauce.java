package restaurantkassensystem.Beilagen;

import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Beilagenklasse Erdnusssauce
 * @author dimitrova
 * @version 1.1
 */
public class Erdnusssauce extends Beilagen {
    private double preis;
    
    public Erdnusssauce(Hauptkomponente gericht, double preis){
        super(gericht);
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return gericht.getPreis() + preis;
    }

    @Override
    public String getBeschreibung() {
        return gericht.getBeschreibung() + ", Erdnusssauce";
    }
    
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
