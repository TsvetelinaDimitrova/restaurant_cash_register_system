package restaurantkassensystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import javax.swing.*;
import restaurantkassensystem.Beilagen.*;
import restaurantkassensystem.Hauptkomponente.*;

/**
 * Klasse für das Erstellen von Tischansichten der einzelnen 
 * Tische
 * 
 * @author tdimitrova
 * @version 1.2
 */
public class Tischansicht extends JDialog implements ActionListener {
    private boolean bestellung_aktiv = false;
    private String tischnummer;
    private JDialog tischansicht;
    private String[] getraenk;
    

    // JSplitPane-ELemente
    private JSplitPane jsp_tischansicht;
    private JPanel jp_menu, jp_bestellung, jp_rechnung;
    private JButton bt_bestellen, bt_rechnung, bt_beenden;
    // Bestellung-Bedienelemente
    private JButton bt_getraenk, bt_speise;
    // Bestellung Getraenke
    private JList jl_getraenk;
    private JButton bt_g_speichern, bt_g_break;
    // Bestellung Speisen
    private ButtonGroup bg_speisen;
    private JRadioButton rb_ente, rb_lachs, rb_tofu, rb_huenchen;
    private JCheckBox jcb_gNudeln, jcb_rCurry, jcb_eierreis, 
            jcb_erdnusssauce, jcb_salat;
    private JButton bt_s_speichern, bt_s_break;
    private JLabel bzh_haupt, bzh_beilage;
    // Rechnungsbereich
    private JButton drucken;

    /**
     * Rückgabe des Tischstatus Bestellung
     *
     * @return true für Bestellung aktiv
     * @version 1.0
     */
    public boolean getBestellung_aktiv() {
        return bestellung_aktiv;
    }

    /**
     * Ändern des Tischstatus Bestellung
     *
     * @param bestellung_aktiv true für Bestellung
     * @version 1.0
     */
    public void setBestellung_aktiv(boolean bestellung_aktiv) {
        this.bestellung_aktiv = bestellung_aktiv;
    }

    /**
     * Rückgabe der Tischnummer
     *
     * @return Tischnummer
     * @version 1.0
     */
    public String getTischnummer() {
        return tischnummer;
    }

    /**
     * Setzen der Tischnummer
     *
     * @param tischnummer Tischnummer
     * @version 1.0
     */
    public void setTischnummer(String tischnummer) {
        this.tischnummer = tischnummer;
    }
    
    /**
     * Erstellen des Tischansichtsobjektes
     *
     * @param tischnummer des aktuellen Tisches
     * @version 1.0
     */
    public Tischansicht(String tischnummer){
        this.tischnummer = tischnummer;
        tischansicht = new JDialog();
        tischansicht.setTitle(tischnummer);
        tischansicht.setIconImage(Tischauswahl.ICON.getImage());
        tischansicht.setSize(Tischauswahl.screenwidth,
                Tischauswahl.screenheight);
        tischansicht.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Hinweis WindowConstants enthält die 4 Konstanten, die
        // für die CloseOperation vorgesehen sind. Allerdings
        // unterstützen nicht alle Fensterklasse alle 4 Konstanten.
        tischansicht.add(setTischansicht());
        tischansicht.setAlwaysOnTop(true);
    }
    
    /**
     * Sichtbarmachen der aktuellen Tischansicht
     * 
     * @version 1.0
     */
    public void getTischansicht(){
        tischansicht.setModal(true);
        tischansicht.setVisible(true);
    }

    /**
     * Grundgerüst des geteilten Fensters
     * 
     * @return JSplitPane mit linkem JPanel
     * @version 1.0
     */
    private JSplitPane setTischansicht(){
        jsp_tischansicht = 
                new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jsp_tischansicht.setLeftComponent(getMenuCont());
        setDivider();
        jsp_tischansicht.setDividerSize(0);
        return jsp_tischansicht;
    }
    
    /**
     * JPanel für die Bestellung
     * 
     * @return JPanel Bestellung
     * @version 1.0
     */
    private JPanel getBestellung(){
        jp_bestellung = new JPanel();
        jp_bestellung.setLayout(null);
        bt_getraenk = new JButton("Getränke");
        bt_getraenk.setBounds(50, 50, 150, 25);
        bt_getraenk.addActionListener(this);
        bt_speise = new JButton("Speise");
        bt_speise.setBounds(50, 100, 150, 25);
        bt_speise.addActionListener(this); 
        // Bereich für die Getränke
        getraenk = KassensystemIO.getIOInstanz().getGetraenkeArray();
        jl_getraenk = new JList(getraenk);
        jl_getraenk.setBounds(250,50,250,500);
        bt_g_break = new JButton("Abbrechen");
        bt_g_break.setBounds(250,575,120,25);
        bt_g_break.addActionListener(this);
        bt_g_speichern = new JButton("Speichern");
        bt_g_speichern.setBounds(380,575,120,25);
        bt_g_speichern.addActionListener(this);
        // Bereich für die Hauptkomponeneten
        bg_speisen = new ButtonGroup();
        bzh_haupt = new JLabel("Hauptkomponenten");
        bzh_haupt.setBounds(250,50,250,25);
        bzh_beilage = new JLabel("Beilage");
        bzh_beilage.setBounds(250, 150, 100, 25);
        bt_s_break = new JButton("Abbrechen");
        bt_s_break.setBounds(250,400,120,25);
        bt_s_break.addActionListener(this);
        bt_s_speichern = new JButton("Speichern");
        bt_s_speichern.setBounds(380,400,120,25);
        bt_s_speichern.addActionListener(this);
        rb_ente = new JRadioButton("Ente");
        rb_ente.setBounds(300,100,100,25);
        rb_ente.addActionListener(this);
        rb_lachs = new JRadioButton("Lachs");
        rb_lachs.setBounds(450,100,100,25);
        rb_lachs.addActionListener(this);
        rb_tofu = new JRadioButton("Tofu");
        rb_tofu.setBounds(600,100,100,25);
        rb_tofu.addActionListener(this);
        rb_huenchen = new JRadioButton("Hünchen");
        rb_huenchen.setBounds(750,100,100,25);
        rb_huenchen.addActionListener(this);
        bg_speisen.add(rb_ente);
        bg_speisen.add(rb_lachs);
        bg_speisen.add(rb_tofu);
        bg_speisen.add(rb_huenchen);
        // Bereich für die Beilagen
        jcb_gNudeln = new JCheckBox("gebratene Nudeln");
        jcb_gNudeln.setBounds(300,200,300,25);
        jcb_gNudeln.addActionListener(this);
        jcb_rCurry = new JCheckBox("Rote Currysauce");
        jcb_rCurry.setBounds(300,225,300,25);
        jcb_rCurry.addActionListener(this);
        jcb_eierreis = new JCheckBox("Eierreis");
        jcb_eierreis.setBounds(300,250,300,25);
        jcb_eierreis.addActionListener(this);
        jcb_erdnusssauce = new JCheckBox("Erdnusssauce");
        jcb_erdnusssauce.setBounds(300,275,300,25);
        jcb_erdnusssauce.addActionListener(this);
        jcb_salat = new JCheckBox("Salat");
        jcb_salat.setBounds(300,300,300,25);
        jcb_salat.addActionListener(this);
        // Elemente dem JP hinzufügen
        jp_bestellung.add(bt_g_break);
        jp_bestellung.add(bt_g_speichern);
        jp_bestellung.add(jl_getraenk);
        jp_bestellung.add(bt_getraenk);
        jp_bestellung.add(bt_speise);
        jp_bestellung.setVisible(true);
        elementGetraenk(false);
        jp_bestellung.add(bzh_haupt);
        jp_bestellung.add(bzh_beilage);
        jp_bestellung.add(bt_s_break);
        jp_bestellung.add(bt_s_speichern);
        jp_bestellung.add(rb_ente);
        jp_bestellung.add(rb_lachs);
        jp_bestellung.add(rb_tofu);
        jp_bestellung.add(rb_huenchen);
        jp_bestellung.add(jcb_gNudeln);
        jp_bestellung.add(jcb_rCurry);
        jp_bestellung.add(jcb_eierreis);
        jp_bestellung.add(jcb_erdnusssauce);
        jp_bestellung.add(jcb_salat);
        elementSpeise(false);
        return jp_bestellung;
    }
    
    /**
     * JPanel für die Rechnung
     * 
     * @return JPanel Rechnung
     * @version 1.0
     */
    private JPanel getRechnung(){
        jp_rechnung = new JPanel();
        jp_rechnung.setLayout(null);
        JLabel bezeichnung = new JLabel("Bezeichnung");
        bezeichnung.setBounds(50,50,500,25);
        JLabel preis = new JLabel("Preis");
        preis.setBounds(600,50,100,25);
        int i= 0;
//        int y = 50;
        double summe = 0.0;
        JLabel schoen = new JLabel ("------------------------------------"
                + "-----------------------------------------------------------"
                + "-------------------------------------------------------");
        schoen.setBounds(40,60,1500,25);
        // Getränke-Array auslesen (y-erhöhen nicht vergessen)
        ArrayList<Getraenk> tempGetraenk = new ArrayList<>();
        tempGetraenk = Tischauswahl.tischgetraenke.get(tischnummer);
        JLabel[] label1 = new JLabel[tempGetraenk.size()*2];
        for(Getraenk g : tempGetraenk){
            label1[i] = new JLabel(g.getBezeichnung()+" "+ g.getVolumen());
            label1[i].setBounds(50,i*25+75,300,25);
            jp_rechnung.add(label1[i]);
            label1[i] = new JLabel(String.valueOf(g.getPreis()));
            label1[i].setBounds(600,i*25+75,100,25);
            jp_rechnung.add(label1[i]);
            summe+=g.getPreis();
            i++;
        }

//        // Badzura
//        ArrayList<Getraenk> tempGetraenk = 
//                Tischauswahl.tischgetraenke.get(tischnummer);
//        int anzahlGetraenk = tempGetraenk.size();
//        JLabel[] gLabel = new JLabel[anzahlGetraenk * 2];
//        int index = 0;
//        for(int i = 0; i < anzahlGetraenk; i++){
//            y = y + 50;
//            gLabel[index] = new JLabel(tempGetraenk.get(i).getBezeichnung()+", "+ 
//                    tempGetraenk.get(i).getVolumen());
//            gLabel[index].setBounds(50,y,200,25);
//            jp_rechnung.add(gLabel[index]);
//            index++;
//            gLabel[index] = new JLabel(String.valueOf(tempGetraenk.get(i).getPreis()));
//            gLabel[i].setBounds(600,y,100,25);
//            jp_rechnung.add(gLabel[index]);
//            summe = summe + tempGetraenk.get(i).getPreis();
//            index++;
//        }

        // Speise-Array auslesen (y-erhöhen nicht vergessen)
        ArrayList<Hauptkomponente> tempSpeise = new ArrayList<>();
        tempSpeise = Tischauswahl.tischspeise.get(tischnummer);
        JLabel[] label2 = new JLabel[tempSpeise.size()*2];
        for(Hauptkomponente s : tempSpeise){
            label2[i] = new JLabel(s.getBeschreibung());
            label2[i].setBounds(50,i*25+75,300,25);
            jp_rechnung.add(label2[i]);
            label2[i] = new JLabel(String.valueOf(s.getPreis()));
            label2[i].setBounds(600,i*25+75,100,25);
            jp_rechnung.add(label2[i]);
            summe+=s.getPreis();
            i++;
        }

//        // Badzura
//        ArrayList<Hauptkomponente> tempSpeise = 
//                Tischauswahl.tischspeise.get(tischnummer);
//        int anzahlSpeise = tempSpeise.size();
//        JLabel[] sLabel = new JLabel[anzahlSpeise * 2];
//        index = 0;
//        for(int i = 0; i < anzahlSpeise; i++){
//            y = y + 30;
//            sLabel[index] = new JLabel(tempSpeise.get(i).getBeschreibung());
//            sLabel[index].setBounds(50,y,100,25);
//            jp_rechnung.add(sLabel[index]);
//            index++;
//            sLabel[index] = new JLabel(String.valueOf(tempSpeise.get(i).getPreis()));
//            sLabel[i].setBounds(600,y,100,25);
//            jp_rechnung.add(sLabel[index]);
//            summe = summe + tempSpeise.get(i).getPreis();
//            index++;
//        }
        
        int y = i*30;
        JLabel ust = new JLabel ("-------------");
        ust.setBounds(580,(y+80),100,25);
        JLabel gesamt = new JLabel("Gesamtsumme");
        gesamt.setBounds(50,(y+110), 500, 25);
        JLabel lb_summe = new JLabel(String.valueOf(summe));
        lb_summe.setBounds(600,(y+110),100,25);
        drucken = new JButton("Rechnung drucken");
        drucken.setBounds(50,(y+160),200,25);
        drucken.addActionListener(this);
        jp_rechnung.add(drucken);
        jp_rechnung.add(lb_summe);
        jp_rechnung.add(gesamt);
        jp_rechnung.add(schoen);
        jp_rechnung.add(ust);
        jp_rechnung.add(preis);
        jp_rechnung.add(bezeichnung);
        jp_rechnung.setVisible(true);
        return jp_rechnung;        
    }
    
    /**
     * JPanel mit den Bedienbuttons
     * 
     * @return JPanel
     * @version 1.0
     */
    private JPanel getMenuCont(){
        jp_menu = new JPanel();
        jp_menu.setLayout(null);
        int button_height = (int)(Tischauswahl.screenheight * 0.1);
        int button_width = (int)(Tischauswahl.screenwidth * 0.2 * 0.9);
        int x = (int)(Tischauswahl.screenwidth * 0.05 * 0.2);
        bt_bestellen = new JButton("Bestellung");
        bt_bestellen.setBounds(x,(int)(Tischauswahl.screenheight * 0.05),
                button_width, button_height);
        bt_bestellen.addActionListener(this);
        bt_rechnung = new JButton("Rechnung");
        bt_rechnung.setBounds(x,(int)(Tischauswahl.screenheight * 0.25),
                button_width, button_height);
        bt_rechnung.addActionListener(this);
        bt_beenden = new JButton("Beenden");
        bt_beenden.setBounds(x,(int)(Tischauswahl.screenheight * 0.45),
                button_width, button_height);
        bt_beenden.addActionListener(this);
        jp_menu.add(bt_bestellen);
        jp_menu.add(bt_rechnung);
        jp_menu.add(bt_beenden);
        return jp_menu;
    }
    
    /**
     * Setzen des Trenners für das JSplitPane
     * 
     * @version 1.0
     */
    private void setDivider(){
        jsp_tischansicht.setDividerLocation((int)(Tischauswahl.screenwidth * 0.2));
    }
    
    /**
     * Ein- und Ausblenden der Bedienelement Bestellung
     * @param b true = Elemente einblenden
     * @version 1.2
     */
    private void elementGetraenk(boolean b){
        jl_getraenk.setVisible(b);
        bt_g_break.setVisible(b);
        bt_g_speichern.setVisible(b);
    }
    
    /**
     * Ein-und Ausblenden der Bedienelemente Speise
     * @param b true = Elemente ausblenden
     * @version 1.2
     */
    private void elementSpeise(boolean b){
        rb_ente.setVisible(b);
        rb_lachs.setVisible(b);
        rb_tofu.setVisible(b);
        rb_huenchen.setVisible(b);
        jcb_gNudeln.setVisible(b);
        jcb_rCurry.setVisible(b);
        jcb_eierreis.setVisible(b);
        jcb_erdnusssauce.setVisible(b);
        jcb_salat.setVisible(b);
        bzh_beilage.setVisible(b);
        bzh_haupt.setVisible(b);
        bt_s_break.setVisible(b);
        bt_s_speichern.setVisible(b);
    }

    /**
     * Eventhandling ActionListener
     *
     * @param e Auslösendes Objekt
     * @version 1.2
     */
    
    @Override
    
    public void actionPerformed(ActionEvent e) {
        // Buttons der Hauptnavigation
        if(e.getSource() == bt_beenden){
            tischansicht.setVisible(false);
            jsp_tischansicht.setRightComponent(null);
            setDivider();
        }
        
        if(e.getSource() == drucken){
            ArrayList<Getraenk> tempGetraenk = new ArrayList<>();
            tempGetraenk = Tischauswahl.tischgetraenke.get(tischnummer);
            tempGetraenk.clear();
            Tischauswahl.tischgetraenke.put(tischnummer, tempGetraenk);
            ArrayList<Hauptkomponente> tempSpeise = new ArrayList<>();
            tempSpeise = Tischauswahl.tischspeise.get(tischnummer);
            tempSpeise.clear();
            Tischauswahl.tischspeise.put(tischnummer, tempSpeise);
            jsp_tischansicht.setRightComponent(getBestellung());
            setDivider();
        }
        
        if(e.getSource() == bt_bestellen){
            jsp_tischansicht.setRightComponent(getBestellung());
            setDivider();
            bestellung_aktiv = true;
        }
        
        if(e.getSource() == bt_rechnung){
            jsp_tischansicht.setRightComponent(getRechnung());
            setDivider();
            bestellung_aktiv = false;
        }
        
        // ActionEvents bei Bestellung
        if (e.getSource() == bt_getraenk){
            elementGetraenk(true);
            elementSpeise(false);
        }
        
        if(e.getSource() == bt_g_break){
            elementGetraenk(false);
            jsp_tischansicht.setRightComponent(getBestellung());
            setDivider();
            bestellung_aktiv = true;
        }
        
//        if(e.getSource() == bt_g_speichern){
//            String lstr = jl_getraenk.getSelectedValue().toString();
//            System.out.println(lstr);
//        }
         
        if(e.getSource() == bt_g_speichern){
            ArrayList<Getraenk> getraenkeAktuell = new ArrayList<>();
            getraenkeAktuell = Tischauswahl.tischgetraenke.get(tischnummer);
            //Logik, für die Getränkeobjektspeicherung
            int[] getauswahl = jl_getraenk.getSelectedIndices();
            ArrayList<Getraenk> getraenkeliste =
                    KassensystemIO.getIOInstanz().getGetraenkeliste();
            for(int i = 0; i < getauswahl.length; i++){
                getraenkeAktuell.add(getraenkeliste.get(getauswahl[i]));
            }
            // Hash-Map mit aktueller ArrayList wieder aktualisieren
            Tischauswahl.tischgetraenke.put(tischnummer, getraenkeAktuell);
            jsp_tischansicht.setRightComponent(getBestellung());
            setDivider();
//                  // Nur zum Test!!!!
//            getraenkeAktuell = Tischauswahl.tischgetraenke.get(tischnummer);
//            double summe = 0.0;
//            for (Getraenk g : getraenkeAktuell){
//                System.out.println(g.getBezeichnung()+", "+
//                        g.getVolumen()+" : "+g.getPreis());
//                summe = summe + g.getPreis();
//            }
//            System.out.println("Gesamtpreis: "+summe);
        }
        
        if (e.getSource() == bt_speise){
            elementGetraenk(false);
            elementSpeise(true);
        }
        
        if(e.getSource() == bt_s_speichern){
            // ermiteln, welche Hauptkomponente ausgewählt wurde
            Hauptkomponente gericht = null;
            
            String hptKomponente = "";
            if(rb_ente.isSelected()){
                hptKomponente = "Ente";
            }
            if(rb_huenchen.isSelected()){
                hptKomponente = "Hünchen";
            }
            if(rb_lachs.isSelected()){
                hptKomponente = "Lachs";
            }
            if(rb_tofu.isSelected()){
                hptKomponente = "Tofu";
            }
            // Objekt aus Hauptgerichtliste ermitteln
            ArrayList<Hauptkomponente> hk = new ArrayList<>();
            hk = KassensystemIO.getIOInstanz().getHauptkomponente();
            for(int i = 0; i < hk.size(); i++){
                if(hk.get(i).getBeschreibung().equals(hptKomponente)){
                    gericht = hk.get(i);
                }
            }
            
            // ermiteln, welche Beilage(n) ausgewählt wurden
            double preis = 0.0;
            HashMap<String, Double> bll = 
                    KassensystemIO.getIOInstanz().getBeilagenliste();
            if(jcb_eierreis.isSelected()){
                preis = bll.get("Eierreis");
                gericht = new Eierreis(gericht,preis);
            }
            if(jcb_erdnusssauce.isSelected()){
                preis = bll.get("Erdnusssauce");
                gericht = new Erdnusssauce(gericht,preis);
            }
            if(jcb_gNudeln.isSelected()){
                preis = bll.get("gebratene Nudeln");
                gericht = new GebrateneNudeln(gericht,preis);
            }
            if(jcb_rCurry.isSelected()){
                preis = bll.get("Rote Currysauce");
                gericht = new RoteCurrysauce(gericht,preis);
            }
            if(jcb_salat.isSelected()){
                preis = bll.get("Salat");
                gericht = new Salat(gericht,preis);
            }
//            System.out.println(gericht.getBeschreibung());
            ArrayList<Hauptkomponente> speiseAktuell = new ArrayList<>();
            speiseAktuell = Tischauswahl.tischspeise.get(tischnummer);
            speiseAktuell.add(gericht);
            Tischauswahl.tischspeise.put(tischnummer, speiseAktuell);
            // Gericht "Zusammensetzen"
            // Zusammengesetztes Gericht in der ArrayList der Hashmap speichern
            
        }
        
        if(e.getSource() == bt_s_break){
            elementSpeise(false);
            jsp_tischansicht.setRightComponent(getBestellung());
            setDivider();
            bestellung_aktiv = true;
        }
    }
}
