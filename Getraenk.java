package restaurantkassensystem;

/**
 * Klasse zum Erzeugen von Getränkeobjekten
 * @author tdimitrova
 * @version 1.1
 */
public class Getraenk {
    private String bezeichnung;
    private double volumen, preis;

    public Getraenk(String bezeichnung, double volumen, double preis) {
        this.bezeichnung = bezeichnung;
        this.volumen = volumen;
        this.preis = preis;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
    /**
     * Ändern der Bezeichnung des Getränkes
     * @param bezeichnung Neue Getränkename
     * @deprecated Methode in dieser Version nicht benötigt.
     */
    @Deprecated
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    public double getVolumen() {
        return volumen;
    }
    
    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return bezeichnung + ";" + volumen + ";" + preis + "\n";
    }
    
}
