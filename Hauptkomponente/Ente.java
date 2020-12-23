package restaurantkassensystem.Hauptkomponente;

/**
 * Hauptkomponente Ente
 * @author dimitrova
 * @version 1.1
 */
public class Ente implements Hauptkomponente{
    private double preis;
    
    public Ente(double preis){
        this.preis = preis;
    }
    
    @Override
    public double getPreis() {
        return preis;
    }

    @Override
    public String getBeschreibung() {
        return "Ente";
    }

    @Override
    public void setPreis(double Preis) {
        this.preis = preis;}
    
}
