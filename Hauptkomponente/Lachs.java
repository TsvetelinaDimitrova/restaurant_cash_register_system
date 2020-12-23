package restaurantkassensystem.Hauptkomponente;

/**
 * Hauptkomponente Ente
 * @author dimitrova
 * @version 1.1
 */
public class Lachs implements Hauptkomponente{
    private double preis;
    
    public Lachs(double preis){
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return preis;
    }

    @Override
    public String getBeschreibung() {
        return "Lachs";
    }
        @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
}
