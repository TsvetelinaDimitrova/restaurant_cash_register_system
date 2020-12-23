package restaurantkassensystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import restaurantkassensystem.Hauptkomponente.*;

/**
 * Klasse für die Dateioperationen (Lesen/Speichern)
 * @author tdimitrova
 * @version 1.1
 */
public class KassensystemIO {
    private static KassensystemIO kassensystemIO;
    private ArrayList<Getraenk> getraenkeliste = new ArrayList<>();
    private ArrayList<Hauptkomponente> hauptkomponentenliste = new ArrayList<>();
    private ArrayList<Mitarbeiter> mitarbeiterliste = new ArrayList<>();
    private HashMap<String, Double> beilagenliste = new HashMap<>();
    private File getraenkecsv = new File("getraenke.csv");
    private File hauptcsv = new File("Hauptkomponenten.csv");
    private File mitarbeitertxt = new File("mitarbeiter.txt");
    private File beilagencsv = new File("Beilagen.csv");
    private String[] getraenke;
    
    /**
     * Konstruktor
     * @version 1.1
     */
    private KassensystemIO(){}
    
    /**
     * Übermitteln der Getränkeliste
     * @return Getränkeliste als ArrayList
     * @version 1.1
     */
    public ArrayList<Getraenk> getGetraenkeliste(){
        return getraenkeliste;
    }
    
    /**
     * Übermitteln der Beilagenliste
     * @return Beilagenliste als HashMap (key = Beilagenname, value = preis)
     * @version 1.1
     */
    public HashMap<String,Double> getBeilagenliste(){
        return beilagenliste;
    }
    
    /**
     * Rückgabe eines String-Arrays mit den Getränken
     * @return Getränkearray
     * @version 1.2
     */
    public String[] getGetraenkeArray(){
        return getraenke;
    }
    
    
    
    /**
     * Übermitteln der Hauptkomponentenliste
     * @return Hauptkomponenten als ArrayList
     * @version 1.1
     */
    public ArrayList<Hauptkomponente> getHauptkomponente(){
        return hauptkomponentenliste;
    }
    
    /**
     * Übermitteln der Mitarbeiterliste
     * @return Mitarbeiterliste als ArrayList
     * @version 1.1
     */
    public ArrayList<Mitarbeiter> getMitarbeiterliste(){
        return mitarbeiterliste;
    }
    /**
     * Instanzprüfung kassensystemIO
     * @return Instand der KassensystemIO
     * @version 1.1
     */
    public static KassensystemIO getIOInstanz(){
        if(kassensystemIO == null){
            kassensystemIO = new KassensystemIO();
        }
        return kassensystemIO;
    }
    
    /**
     * Setzen der Getränkeliste
     * @version 1.1
     */
    public void setGetraenkeliste(){
        try{
            BufferedReader zeile = 
                    new BufferedReader(new FileReader(getraenkecsv));
            String str;
            while((str=zeile.readLine()) != null){
                String[] temp = str.split(";");
                getraenkeliste.add(new Getraenk(temp[0], Double.valueOf(temp[1]),
                        Double.valueOf(temp[2])));
            }
            zeile.close();
             int lang = getraenkeliste.size();
            getraenke = new String[lang];
            for(int i = 0; i < lang; i++){
                getraenke[i] = getraenkeliste.get(i).getBezeichnung()+ ", "+
                        String.valueOf(getraenkeliste.get(i).getVolumen());
            }
        }
        catch(IOException e){
            errorMessage("Fehler beim Auslesen der Getränke.");
        }
    }   
    
    /**
     * Setzen der Beilagenliste
     * @version 1.1
     */
    public void setBeilagenliste(){
        try{
            BufferedReader zeile = 
                    new BufferedReader(new FileReader(beilagencsv));
            String str;
            while((str=zeile.readLine()) != null){
                String[] temp = str.split(";");
                beilagenliste.put(temp[0], Double.valueOf(temp[1]));
            }
            zeile.close();
        }
        catch(IOException e){
            errorMessage("Fehler beim Auslesen der Beilagen.");
        }
    }        
    
    /**
     * Setzen der Mitarbeiterliste
     * @version 1.1
     */
    public void setMitarbeiterliste(){
        try{
            BufferedReader zeile = 
                    new BufferedReader(new FileReader(mitarbeitertxt));
            String str;
            int counter = 0;
            String ben = "";
            while((str=zeile.readLine()) != null){
                if(counter == 0){
                    ben = str;
                    counter++;
                }else
                {
                    mitarbeiterliste.add(new Mitarbeiter(ben,str));
                    counter = 0;
                }
            }
            zeile.close();
        }
        catch(IOException e){
            errorMessage("Fehler beim Auslesen der Mitarbeiter.");
        }
    }
    
    /**
     * Mitarbeiterliste updaten und anschließend speichern
     * @param mitarbeiterliste aktualisierte Mitarbeiterliste
     * @version 1.1
     */
    public void updateMitarbeiterliste(ArrayList<Mitarbeiter> mitarbeiterliste){
        this.mitarbeiterliste = mitarbeiterliste;
        schreibeMitarbeiterliste();
    }
    
    /**
     * Getränkeliste updaten und anschließend speichern
     * @param getraenkeliste aktualisierte Getränkeliste
     * @version 1.1
     */
    public void updateGetraenkeliste(ArrayList<Getraenk> getraenkeliste){
        this.getraenkeliste = getraenkeliste;
        schreibeGetraenkeliste();
    }

    /**
     * Beilagenliste updaten und anschließend speichern
     * @param beilage String-Array mit den Key-Werten der HashMap
     * @param beilagenliste aktualisierte Beilagenliste
     * @version 1.1
     */
    public void updateBeilagenliste(String[] beilage, HashMap<String, Double> beilagenliste){
        this.beilagenliste = beilagenliste;
        schreibeBeilagenliste(beilage);
    }

    /**
     * Aktualisieren der Hauptkomponentenliste und anschließend speichern
     * @param hauptkomponentenliste aktualisierte Hauptkomponentenliste
     * @version 1.1
     */
    public void updateHauptkomponentenliste(ArrayList<Hauptkomponente> hauptkomponentenliste){
        this.hauptkomponentenliste = hauptkomponentenliste;
        schreibeHauptkomponentenliste();
    }
    
    /**
     * Speichern der Beilagenliste
     * @param beilage String-Array mit den Keys der HashMap
     * @version 1.1
     */
    private void schreibeBeilagenliste(String[] beilage){
try {
            FileWriter fw = new FileWriter(beilagencsv);
            // Hinzufügen von Inhalten
            for (int i = 0; i < beilagenliste.size();i++){
                fw.append(beilage[i]+";"+String.valueOf(beilagenliste.get(beilage[i]))+"\n");
            }
            fw.close();
        } catch (IOException ex) {
            errorMessage("Fehler beim Speichern der Beilagen.");
        }        
    }
    
    /**
     * Speichern der Hauptkomponentenliste
     * @version 1.1
     */
    private void schreibeHauptkomponentenliste(){
        try {
            FileWriter fw = new FileWriter(hauptcsv);
            // Hinzufügen von Inhalten
            for (Hauptkomponente hk : hauptkomponentenliste){
                fw.append(hk.getBeschreibung()+";"+hk.getPreis()+"\n");
            }
            fw.close();
        } catch (IOException ex) {
            errorMessage("Fehler beim Speichern der Hauptkomponenten.");
        }        
    }

    /**
     * Speichern der Getränkeliste
     * @version 1.1
     */    
   private void schreibeGetraenkeliste(){
        try {
            FileWriter fw = new FileWriter(getraenkecsv);
             int zaehler = 0;
            // Hinzufügen von Inhalten
            for (Getraenk g : getraenkeliste){
                fw.append(g.toString());
                getraenke[zaehler] = g.getBezeichnung() + ", " +
                        String.valueOf(g.getVolumen());
                zaehler++;
                
            }
            fw.close();
        } catch (IOException ex) {
            errorMessage("Fehler beim Speichern der Getraenke.");
        }
    }        
    
    /**
     * Speichern der Mitarbeiterliste
     * @version 1.1
     */
    private void schreibeMitarbeiterliste(){
        try {
            FileWriter fw = new FileWriter(mitarbeitertxt);
            // Hinzufügen von Inhalten
            for (Mitarbeiter ma : mitarbeiterliste){
                fw.append(ma.getBenutzer()+"\n"+ma.getPasswort()+"\n");
            }
            fw.close();
        } catch (IOException ex) {
            errorMessage("Fehler beim Speichern der Mitarbeiter.");
        }
    }    
    
    /**
     * Setzen der Hauptkomponentenliste
     * @version 1.1
     */
    public void setHauptkomponente(){
        try{
            BufferedReader zeile = 
                    new BufferedReader(new FileReader(hauptcsv));
            String str;
            while((str=zeile.readLine()) != null){
                String[] temp = str.split(";");
                switch(temp[0]){
                    case "Ente":
                        hauptkomponentenliste.add(new Ente(Double.valueOf(temp[1])));
                        break;
                    case "Hühnchen":
                        hauptkomponentenliste.add(new Huehnchen(Double.valueOf(temp[1])));
                        break;                        
                    case "Lachs":
                        hauptkomponentenliste.add(new Lachs(Double.valueOf(temp[1])));
                        break;                        
                    case "Tofu":
                        hauptkomponentenliste.add(new Tofu(Double.valueOf(temp[1])));
                        break;  
                    default: break;
                }
            }
            zeile.close();
        }
        catch(IOException e){
            errorMessage("Fehler beim Auslesen der Hauptkomponenten.");
        }
    }
    
    /**
     * Dialogfenster zur Ausgabe der Fehlermeldungen
     * @param fehlermeldung Text der Fehlermeldung
     * @version 1.1
     */
    private void errorMessage(String fehlermeldung){
        JOptionPane.showMessageDialog(null, fehlermeldung, "Datenproblem", 
                JOptionPane.ERROR_MESSAGE);
    }
}