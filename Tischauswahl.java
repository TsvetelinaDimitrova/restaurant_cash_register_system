package restaurantkassensystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.LineBorder;
import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Start beziehungsweise Hauptfenster der Anwendung
 * 
 * @author tdimitrova
 * @version 1.2
 */
public class Tischauswahl extends JFrame 
        implements ActionListener, WindowListener {
    
    private static Tischauswahl t;
    public static int screenheight = 
            java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    public static int screenwidth = 
            java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    // Icon für Fenster
    private static URL URLICON = Tischansicht.class.getResource("icon.gif");
    public static final ImageIcon ICON = new ImageIcon(URLICON);
    // HashMaps für Getränke und Speisen
    public static HashMap<String, ArrayList<Getraenk>> tischgetraenke = 
            new HashMap<>();
    public static HashMap<String, ArrayList<Hauptkomponente>> tischspeise = 
            new HashMap<>();
    // Elemente Hauptfenster
    private JFrame tischauswahl;
    private JPanel jp;
    ArrayList<JButton> tische = new ArrayList<>();
    private HashMap<String, Tischansicht> tischanzeige = new HashMap<>();
    // Elemente Menü
    private JMenuBar jmb;
    private JMenu kassensystem, verwaltung, jm_hilfe;
    private JMenuItem beenden, speiseverwaltung, getraenkeverwaltung,
            neuerMitarbeiter, jmi_hilfe;
    private JSeparator jsep = new JSeparator();

    /**
     * Konstruktor zum Erstellen einer Instanz von Tischauswahl
     * 
     * @version 1.1
     */
    private Tischauswahl(){
        tischauswahl = new JFrame("Restaurant - Kassensystem");
        // In JDK8 setResizable(true), JDK 14 muss hier setResizable(false)
        tischauswahl.setResizable(true);
        tischauswahl.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // tischauswahl.setSize(screenwidht, screenheight);
        tischauswahl.addWindowListener(this);
        tischauswahl.setIconImage(ICON.getImage());
        tischauswahl.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        tischauswahl.setJMenuBar(getMenubar());
        tischauswahl.add(getTischauswahl());
        tischauswahl.setVisible(true);
        KassensystemIO.getIOInstanz().setGetraenkeliste();
        KassensystemIO.getIOInstanz().setHauptkomponente();
        KassensystemIO.getIOInstanz().setMitarbeiterliste();
        KassensystemIO.getIOInstanz().setBeilagenliste();
//        System.out.println(KassensystemIO.getIOInstanz().getBeilagenliste().size());
    }
    
    /**
     * Zusammensetzen der JMenuBar
     * 
     * @return zusammengesetzte JMenuBar
     * @version 1.1
     */
    private JMenuBar getMenubar(){
        jmb = new JMenuBar();
        jmb.setBorder(new LineBorder(Color.BLACK));
        // JMenu kassensystem
        kassensystem = new JMenu("Kassensystem");
        // Festlegen des Buchstabens für die Menüaktivierung
        // mit der ALT-Taste (Mnemonic)
        kassensystem.setMnemonic('K');
        beenden = new JMenuItem("Beenden",'B');
        // Tastenkombination hinzufügen (Keybinding)
        beenden.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,KeyEvent.ALT_DOWN_MASK));
        beenden.addActionListener(this);
        kassensystem.add(beenden);
        // JMenu verwaltung
        verwaltung = new JMenu("Verwaltung");
        verwaltung.setMnemonic('V');
        speiseverwaltung = new JMenuItem("Speiseverwaltung",'S');
        speiseverwaltung.setAccelerator(KeyStroke.getKeyStroke("F2"));
        speiseverwaltung.addActionListener(this);
        getraenkeverwaltung = new JMenuItem("Getränkeverwaltung",'G');
        getraenkeverwaltung.setAccelerator(KeyStroke.getKeyStroke("F3"));
        getraenkeverwaltung.addActionListener(this);
        neuerMitarbeiter = new JMenuItem("Neuer Mitarbeiter",'N');
        neuerMitarbeiter.addActionListener(this);
        verwaltung.add(speiseverwaltung);
        verwaltung.add(getraenkeverwaltung);
        verwaltung.add(jsep);
        verwaltung.add(neuerMitarbeiter);
        // JMenu hilfe
        jm_hilfe = new JMenu("Hilfe");
        jm_hilfe.setMnemonic('H');
        jmi_hilfe = new JMenuItem("Hilfe",'H');
        jmi_hilfe.setAccelerator(KeyStroke.getKeyStroke("F1"));
        jmi_hilfe.addActionListener(this);
        jm_hilfe.add(jmi_hilfe);
        // JMenu-Items der JMenuBar hinzufügen
        jmb.add(kassensystem);
        jmb.add(verwaltung);
        jmb.add(jm_hilfe);
        return jmb;
    }
    
    /**
     * Erstellen des JPanels mit den Buttons
     * 
     * @return JPanel
     * @version 1.2
     */
    private JPanel getTischauswahl(){
        jp = new JPanel();
        jp.setLayout(null);
        int button_height = (int)(screenheight * 0.15);
        int button_width = (int)(screenwidth * 0.15);
        int x = 0, y = 0;
        for(int i = 1; i <= 20; i++){
            tische.add(new JButton("Tisch "+i));
            switch(i){
                case 1: 
                    x = (int) (screenwidth * 0.05);
                    y = (int) (screenheight * 0.04);
                    break;
                case 2:
                    x = (int) (screenwidth * 0.05);
                    y = (int) (screenheight * 0.23);
                    break;
                case 3:
                    x = (int) (screenwidth * 0.05);
                    y = (int) (screenheight * 0.42);
                    break;
                case 4:
                    x = (int) (screenwidth * 0.05);
                    y = (int) (screenheight * 0.61);
                    break;
                case 5:
                    x = (int) (screenwidth * 0.05);
                    y = (int) (screenheight * 0.8);
                    break;
                case 6:
                    x = (int) (screenwidth * 0.3); 
                    y = (int) (screenheight * 0.04);
                    break;
                case 7:
                    x = (int) (screenwidth * 0.3); 
                    y = (int) (screenheight * 0.23);
                    break;
                case 8:
                    x = (int) (screenwidth * 0.3); 
                    y = (int) (screenheight * 0.42);
                    break;
                case 9:
                    x = (int) (screenwidth * 0.3); 
                    y = (int) (screenheight * 0.61);
                    break;
                case 10:
                    x = (int) (screenwidth * 0.3); 
                    y = (int) (screenheight * 0.8);
                    break;
                case 11:
                    x = (int) (screenwidth * 0.55); 
                    y = (int) (screenheight * 0.04);
                    break;
                case 12:
                    x = (int) (screenwidth * 0.55); 
                    y = (int) (screenheight * 0.23);
                    break;
                case 13:
                    x = (int) (screenwidth * 0.55); 
                    y = (int) (screenheight * 0.42);
                    break;
                case 14:
                    x = (int) (screenwidth * 0.55); 
                    y = (int) (screenheight * 0.61);
                    break;
                case 15:
                    x = (int) (screenwidth * 0.55); 
                    y = (int) (screenheight * 0.8);
                    break;
                case 16:
                    x = (int) (screenwidth * 0.8); 
                    y = (int) (screenheight * 0.04);
                    break;
                case 17:
                    x = (int) (screenwidth * 0.8); 
                    y = (int) (screenheight * 0.23);
                    break;
                case 18:
                    x = (int) (screenwidth * 0.8); 
                    y = (int) (screenheight * 0.42);
                    break;
                case 19:
                    x = (int) (screenwidth * 0.8); 
                    y = (int) (screenheight * 0.61);
                    break;
                default:
                    x = (int) (screenwidth * 0.8); 
                    y = (int) (screenheight * 0.8);
                    break;
            }
            tische.get(i-1).setBounds(x,y,button_width,button_height);
            tische.get(i-1).addActionListener(this);
            jp.add(tische.get(i-1));
            tischanzeige.put("Tisch "+i, new Tischansicht("Tisch " +i));
            tischgetraenke.put("Tisch "+i, new ArrayList<Getraenk>());
            tischspeise.put("Tisch "+i, new ArrayList<Hauptkomponente>());
        }
        return jp;
    }
    
    /**
     * Prüfung, ob Instanz vorhanden und Rückgabe der Instanz
     * 
     * @return Instanz der Tischauswahl
     */
    public static Tischauswahl getInstance(){
        if(t == null){
            t = new Tischauswahl();
        }
        return t;
    }
    
    /**
     * Anzeige eine Abfragedialogfensters
     * 
     * @param nachricht Frage an den Benutzer
     * @param titel Eintrag in der Titelleiste des Dialogfensters
     * @return Wert des gedrückten Buttons (0 = ja, 1 = nein)
     * @version 1.0
     */
    private int showConfirmDialog(String nachricht, String titel){
        return JOptionPane.showConfirmDialog(null, nachricht, titel,
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    }
    
    /**
     * Methode zum Verlassen der Anwendung nach Anzeige Dialogfenster
     * zu verlassen.
     * @version 1.0
     */
    private void verlassen(){
        if(showConfirmDialog("Wollen Sie die Anwendung wirklich beenden",
                "Sicherheitsabfrage")==0){
            System.exit(0);
        }
    }

    /**
     * Eventhandling ActionListener
     *
     * @param e Auslösendes Objekt
     * @version 1.1
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JButton ausloeser = (JButton)e.getSource();
            Tischansicht ta = tischanzeige.get(ausloeser.getText());
            ta.getTischansicht();
        }
        catch(ClassCastException cce){}
        
        if(e.getSource() == beenden){
            verlassen();
        }
        
        if(e.getSource() == speiseverwaltung){
            new Verwaltung(Verwaltung.SPEISEVERWALTUNG);
        }
        
        if (e.getSource() == getraenkeverwaltung){
            new Verwaltung(Verwaltung.GETRAENKEVERWALTUNG);
        }
        
        if (e.getSource() == neuerMitarbeiter){
            new Verwaltung(Verwaltung.MITARBEITERVERWALTUNG);
        }
    }

    /**
     * Event, wenn Fenster sich geöffnet hat
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowOpened(WindowEvent e) {
        System.err.println("Tischauswahl WIndowOpenend");
    }

    /**
     * Event, wenn Fenster sich schließt
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowClosing(WindowEvent e) {
        verlassen();
    }

    /**
     * Event, wenn Fenster sich geschlossen hat
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowClosed(WindowEvent e) {
        System.err.println("Tischauswahl WindowClosed");
    }

    /**
     * Event, wenn Fenster minimiert wird
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowIconified(WindowEvent e) {
        tischauswahl.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Event, wenn Fenster aus Minimierung maximiert wird
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowDeiconified(WindowEvent e) {
        System.err.println("Tischauswahl windowDeiconified");
    }

    /**
     * Event, wenn Fenster aktiv ist
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowActivated(WindowEvent e) {
        for(int i = 0; i < 20; i++){
            if(tischanzeige.get("Tisch "+(i+1)).getBestellung_aktiv()){
                tische.get(i).setBackground(Color.red);
            }else{
                tische.get(i).setBackground(Color.green);
            }
        }
    }

    /**
     * Event, wenn Fenster nicht aktiv ist
     *
     * @param e Auslösendes Fenster
     * @version 1.0
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
}
