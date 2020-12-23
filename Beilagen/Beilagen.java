package restaurantkassensystem.Beilagen;

import restaurantkassensystem.Hauptkomponente.*;

/**
 * Klasse, um im Decorator dynamisch mehrere Beilagen
 * einem Gericht zuzuweisen
 * @author dimitrova
 * @version 1.1
 */
public abstract class Beilagen implements Hauptkomponente {
    protected Hauptkomponente gericht;
    
    /**
     * Konstruktor für Beilage
     * @param gericht aktuelles Gericht, dem die Beilage hinzugefügt wird
     */
    public Beilagen(Hauptkomponente gericht){
        this.gericht = gericht;
    }
}
