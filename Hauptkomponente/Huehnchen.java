package restaurantkassensystem.Hauptkomponente;

/**
 * Hauptkomponente Ente
 * @author dimitrova
 * @version 1.1
 */
public class Huehnchen implements Hauptkomponente{
    private double preis;
    
    public Huehnchen(double preis){
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return preis;
    }

    @Override
    public String getBeschreibung() {
        return "Hühnchen";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
