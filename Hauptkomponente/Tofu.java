package restaurantkassensystem.Hauptkomponente;

/**
 * Hauptkomponente Ente
 * @author dimitrova
 * @version 1.1
 */
public class Tofu implements Hauptkomponente{
    private double preis;
    
    public Tofu(double preis){
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return preis;
    }

    @Override
    public String getBeschreibung() {
        return "Tofu";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
