package restaurantkassensystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import javax.swing.*;
import restaurantkassensystem.Hauptkomponente.Hauptkomponente;

/**
 * Verwaltung des Restaurantkassensystems
 *
 * @author tdimitrova
 * @version 1.1
 */
public class Verwaltung extends JDialog implements ActionListener {

    public static final int SPEISEVERWALTUNG = 0;
    public static final int GETRAENKEVERWALTUNG = 1;
    public static final int MITARBEITERVERWALTUNG = 2;
    private int verwaltungsakt, auswahl;
    private String fehler;
    private ArrayList<Mitarbeiter> mitarbeiterliste;
    private ArrayList<Getraenk> getraenkeliste;
    private ArrayList<Hauptkomponente> hauptkomponentenliste;
    private HashMap<String, Double> beilagenliste;
    private String[] beilage;
    private int index, gv_laenge, sv_h_laenge, sv_b_laenge;
    // Hauptfenster Verwaltung
    private JDialog verwaltung;
    // JPanel für Authentifizierung
    private JPanel anmeldung;
    private JLabel benutzername, passwort;
    private JTextField txt_benutzername;
    private JPasswordField jpf_passwort;
    private JButton bt_anmelden, bt_abbrechen;
    // JPanel für Speiseverwaltung
    private JPanel speiseverwaltung;
    private JRadioButton rbt_haupt, rbt_beilage;
    private JLabel sv_bezeichnung, sv_preis;
    private JTextField txt_sv_bezeichnung, txt_sv_preis;
    private JButton bt_sv_first, bt_sv_before, bt_sv_next,
            bt_sv_last, bt_sv_break;
    private ButtonGroup radiobuttons;
    // JPanel für Getraenkeverwaltung
    private JPanel getraenkeverwaltung;
    private JLabel gv_bezeichnung, gv_volumen, gv_preis;
    private JTextField txt_gv_bezeichnung, txt_gv_volumen, txt_gv_preis;
    private JButton bt_gv_first, bt_gv_before, bt_gv_next,
            bt_gv_last, bt_gv_new, bt_gv_break, bt_gv_save;
    // JPanel für Neuer Mitarbeiter
    private JPanel mitarbeiterverwaltung;
    private JButton bt_mav_break, bt_mav_save;
    private JLabel mav_benutzername, mav_passwort, mav_rep_passwort;
    private JTextField jtf_mav_benutzername;
    private JPasswordField jpf_mav_passwort, jpf_mav_rep_passwort;

    /**
     * Erstellen des Fensters für die Verwaltung
     *
     * @param verwaltungsakt
     * @version 1.1
     */
    public Verwaltung(int verwaltungsakt) {
        this.verwaltungsakt = verwaltungsakt;
        verwaltung = new JDialog();
        verwaltung.setTitle("Verwaltung");
        // setModal(true) --> Nur das aktuelle Fenster kann
        // Aktionen ausführen. 
        verwaltung.setModal(true);
        verwaltung.setSize(350, 250);
        verwaltung.setLocationRelativeTo(null);
        verwaltung.setIconImage(Tischauswahl.ICON.getImage());
        //verwaltung.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        verwaltung.add(getAuthentifizierung());
        verwaltung.setVisible(true);
        verwaltung.toFront();
    }

    /**
     * Erstellen JPanel für die Speiseverwaltung
     *
     * @return Speiseverwaltung mit Bedienelementen
     * @version 1.1
     */
    private JPanel getSpeiseverwaltung() {
        speiseverwaltung = new JPanel();
        speiseverwaltung.setLayout(null);
        radiobuttons = new ButtonGroup();
        rbt_haupt = new JRadioButton("Hauptkomponente");
        rbt_haupt.addActionListener(this);
        rbt_haupt.setBounds(50, 50, 145, 25);
        rbt_beilage = new JRadioButton("Beilagen");
        rbt_beilage.addActionListener(this);
        rbt_beilage.setBounds(205, 50, 145, 25);
        sv_bezeichnung = new JLabel("Bezeichnung");
        sv_bezeichnung.setBounds(50, 150, 100, 25);
        sv_preis = new JLabel("Preis");
        sv_preis.setBounds(50, 180, 100, 25);
        txt_sv_bezeichnung = new JTextField();
        txt_sv_bezeichnung.setBounds(175, 150, 175, 25);
        txt_sv_bezeichnung.setEditable(false);
        txt_sv_preis = new JTextField();
        txt_sv_preis.setBounds(175, 180, 175, 25);
        txt_sv_preis.setEditable(false);
        bt_sv_first = new JButton("<<");
        bt_sv_first.setBounds(50, 210, 50, 25);
        bt_sv_first.addActionListener(this);
        bt_sv_first.setEnabled(false);
        bt_sv_before = new JButton("<");
        bt_sv_before.setBounds(100, 210, 50, 25);
        bt_sv_before.addActionListener(this);
        bt_sv_before.setEnabled(false);
        bt_sv_next = new JButton(">");
        bt_sv_next.setBounds(150, 210, 50, 25);
        bt_sv_next.addActionListener(this);
        bt_sv_next.setEnabled(false);
        bt_sv_last = new JButton(">>");
        bt_sv_last.setBounds(200, 210, 50, 25);
        bt_sv_last.addActionListener(this);
        bt_sv_last.setEnabled(false);
        bt_sv_break = new JButton("Abbrechen");
        bt_sv_break.setBounds(250, 210, 100, 25);
        bt_sv_break.addActionListener(this);
        speiseverwaltung.add(rbt_haupt);
        speiseverwaltung.add(rbt_beilage);
        speiseverwaltung.add(sv_bezeichnung);
        speiseverwaltung.add(sv_preis);
        speiseverwaltung.add(txt_sv_bezeichnung);
        speiseverwaltung.add(txt_sv_preis);
        speiseverwaltung.add(bt_sv_first);
        speiseverwaltung.add(bt_sv_before);
        speiseverwaltung.add(bt_sv_next);
        speiseverwaltung.add(bt_sv_last);
        speiseverwaltung.add(bt_sv_break);
        radiobuttons.add(rbt_haupt);
        radiobuttons.add(rbt_beilage);
        return speiseverwaltung;
    }

    /**
     * Erstellen des Formulars für die Getränkeverwaltung
     *
     * @return Getränkeverwaltung mit Bedienelementen
     * @version 1.1
     */
    private JPanel getGetraenkeverwaltung() {
        getraenkeverwaltung = new JPanel();
        getraenkeverwaltung.setLayout(null);
        gv_bezeichnung = new JLabel("Bezeichnung");
        gv_bezeichnung.setBounds(50, 50, 100, 25);
        gv_volumen = new JLabel("Volumen");
        gv_volumen.setBounds(50, 80, 100, 25);
        gv_preis = new JLabel("Preis");
        gv_preis.setBounds(50, 110, 100, 25);
        txt_gv_bezeichnung = new JTextField();
        txt_gv_bezeichnung.setBounds(175, 50, 275, 25);
        txt_gv_bezeichnung.setEditable(false);
        txt_gv_volumen = new JTextField();
        txt_gv_volumen.setBounds(175, 80, 275, 25);
        txt_gv_preis = new JTextField();
        txt_gv_preis.setBounds(175, 110, 275, 25);
        bt_gv_first = new JButton("<<");
        bt_gv_first.setBounds(50, 140, 50, 25);
        bt_gv_first.addActionListener(this);
        bt_gv_before = new JButton("<");
        bt_gv_before.setBounds(100, 140, 50, 25);
        bt_gv_before.addActionListener(this);
        bt_gv_next = new JButton(">");
        bt_gv_next.setBounds(150, 140, 50, 25);
        bt_gv_next.addActionListener(this);
        bt_gv_last = new JButton(">>");
        bt_gv_last.setBounds(200, 140, 50, 25);
        bt_gv_last.addActionListener(this);
        bt_gv_new = new JButton("Neu");
        bt_gv_new.setBounds(250, 140, 50, 25);
        bt_gv_new.addActionListener(this);
        bt_gv_break = new JButton("Abbrechen");
        bt_gv_break.setBounds(300, 140, 150, 25);
        bt_gv_break.addActionListener(this);
        bt_gv_save = new JButton("Speichern");
        bt_gv_save.setBounds(460, 140, 100, 25);
        bt_gv_save.addActionListener(this);
        bt_gv_save.setVisible(false);
        getraenkeverwaltung.add(gv_bezeichnung);
        getraenkeverwaltung.add(gv_volumen);
        getraenkeverwaltung.add(gv_preis);
        getraenkeverwaltung.add(txt_gv_bezeichnung);
        getraenkeverwaltung.add(txt_gv_volumen);
        getraenkeverwaltung.add(txt_gv_preis);
        getraenkeverwaltung.add(bt_gv_first);
        getraenkeverwaltung.add(bt_gv_before);
        getraenkeverwaltung.add(bt_gv_next);
        getraenkeverwaltung.add(bt_gv_last);
        getraenkeverwaltung.add(bt_gv_new);
        getraenkeverwaltung.add(bt_gv_break);
        getraenkeverwaltung.add(bt_gv_save);
        return getraenkeverwaltung;
    }

    /**
     * Erstellen des Formulars für die Mitarbeiterverwaltung
     *
     * @return Mitarbeiterverwaltung mit Bedienelementen
     * @version 1.1
     */
    private JPanel getMitarbeiterverwaltung() {
        mitarbeiterverwaltung = new JPanel();
        mitarbeiterverwaltung.setLayout(null);
        mav_benutzername = new JLabel("Benutzername");
        mav_benutzername.setBounds(50, 50, 150, 25);
        mav_passwort = new JLabel("Passwort");
        mav_passwort.setBounds(50, 80, 150, 25);
        mav_rep_passwort = new JLabel("Passwortwiederholung");
        mav_rep_passwort.setBounds(50, 110, 150, 25);
        jtf_mav_benutzername = new JTextField();
        jtf_mav_benutzername.setBounds(200, 50, 150, 25);
        jpf_mav_passwort = new JPasswordField();
        jpf_mav_passwort.setBounds(200, 80, 150, 25);
        jpf_mav_rep_passwort = new JPasswordField();
        jpf_mav_rep_passwort.setBounds(200, 110, 150, 25);
        bt_mav_save = new JButton("Speichern");
        bt_mav_save.setBounds(50, 140, 140, 25);
        bt_mav_save.addActionListener(this);
        bt_mav_break = new JButton("Abbrechen");
        bt_mav_break.setBounds(200, 140, 140, 25);
        bt_mav_break.addActionListener(this);
        mitarbeiterverwaltung.add(bt_mav_break);
        mitarbeiterverwaltung.add(bt_mav_save);
        mitarbeiterverwaltung.add(mav_benutzername);
        mitarbeiterverwaltung.add(mav_passwort);
        mitarbeiterverwaltung.add(mav_rep_passwort);
        mitarbeiterverwaltung.add(jtf_mav_benutzername);
        mitarbeiterverwaltung.add(jpf_mav_passwort);
        mitarbeiterverwaltung.add(jpf_mav_rep_passwort);
        return mitarbeiterverwaltung;
    }

    /**
     * Erstellen des Formulars für die Authentifizierung
     *
     * @return Authentifizierung mit Bedienelementen
     * @version 1.1
     */
    private JPanel getAuthentifizierung() {
        anmeldung = new JPanel();
        anmeldung.setLayout(null);
        benutzername = new JLabel("Benutzername");
        benutzername.setBounds(50, 50, 100, 25);
        passwort = new JLabel("Passwort");
        passwort.setBounds(50, 80, 100, 25);
        txt_benutzername = new JTextField();
        txt_benutzername.setBounds(175, 50, 100, 25);
        jpf_passwort = new JPasswordField();
        jpf_passwort.setBounds(175, 80, 100, 25);
        bt_anmelden = new JButton("Anmelden");
        bt_anmelden.setBounds(50, 110, 100, 25);
        bt_anmelden.addActionListener(this);
        bt_abbrechen = new JButton("Abbrechen");
        bt_abbrechen.setBounds(175, 110, 100, 25);
        bt_abbrechen.addActionListener(this);
        anmeldung.add(benutzername);
        anmeldung.add(passwort);
        anmeldung.add(txt_benutzername);
        anmeldung.add(jpf_passwort);
        anmeldung.add(bt_anmelden);
        anmeldung.add(bt_abbrechen);
        return anmeldung;
    }

    /**
     * Prüfung der Authentifizierung
     *
     * @return true Anmeldedaten korrekt
     * @version 1.1
     */
    private boolean pruefeAuthentifizierung() {
        boolean pruef = true;
        mitarbeiterliste = KassensystemIO.getIOInstanz().getMitarbeiterliste();
        // @todo Prüfalgorithmen
        // Was muss geprüft werden?
        // - Ist den ein Benutzername und ein Passwort eingetragen?
        // - Existiert der Benutzer in der Datenherkunft.
        // - Stimmt das eingetragene PW mit dem gespeicherten?
        if (txt_benutzername.getText().trim().isEmpty()
                || jpf_passwort.getPassword().length == 0) {
            pruef = false;
            fehler = "Nicht alle benötigten Felder ausgefüllt.";
        }
        if (pruef) {
            boolean treffer = false;
            String ben = "";
            String pw = "";
            try {
                ben = EncryptPassword.
                        SHA512(txt_benutzername.getText().trim());
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            }
            for (Mitarbeiter ma : mitarbeiterliste) {
                // 2 Strings werden mit der Methode equals verglichen 
                // (auf Gleichheit)
                if (ma.getBenutzer().equals(ben)) {
                    treffer = true;
                    pw = ma.getPasswort();
                }
            }
            if (!treffer) {
                pruef = false;
                fehler = ("Benutzer nicht bekannt");
            } else {
                try {
                    if (!(EncryptPassword.SHA512(Arrays.toString(jpf_passwort.
                            getPassword())).equals(pw))) {
                        pruef = false;
                        fehler = "Passwort nicht korrekt.";
                    }
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                }
            }
        }
        return pruef;
    }

    /**
     * Einblenden des JPanels je nach Auswahl im Menü
     *
     * @version 1.1
     */
    private void verwaltungsaktEinblenden() {
        verwaltung.setSize(Tischauswahl.screenwidth + 10,
                Tischauswahl.screenheight + 10);
        verwaltung.setLocation(-5, 0);
        verwaltung.remove(anmeldung);
        switch (verwaltungsakt) {
            case 0:
                verwaltung.add(getSpeiseverwaltung());
                hauptkomponentenliste = KassensystemIO.getIOInstanz().getHauptkomponente();
                beilagenliste = KassensystemIO.getIOInstanz().getBeilagenliste();
                break;     
            case 1:
                verwaltung.add(getGetraenkeverwaltung());
                getraenkeliste
                        = KassensystemIO.getIOInstanz().getGetraenkeliste();
                index = 0;
                gv_laenge = getraenkeliste.size();
                if (gv_laenge <= 1) {
                    bt_gv_next.setEnabled(false);
                }
                bt_gv_before.setEnabled(false);
                showGetraenk();
                break;
            case 2:
                verwaltung.add(getMitarbeiterverwaltung());
                break;
        }
        verwaltung.repaint();
    }

    /**
     * Prüfung, ob die Passwörter bei Neuanmeldung identisch sind
     *
     * @return true, wenn Passwörter identisch
     * @version 1.1
     */
    private boolean pruefePasswortIdentisch() {
        boolean pruef = true;
        if (jpf_mav_passwort.getPassword().length != jpf_mav_rep_passwort.getPassword().length) {
            pruef = false;
        } else {
            for (int i = 0; i < jpf_mav_passwort.getPassword().length; i++) {
                if (jpf_mav_passwort.getPassword()[i] != jpf_mav_rep_passwort.getPassword()[i]) {
                    pruef = false;
                    break;
                }
            }
            // Möglichkeit 2
//            if (!Arrays.toString(jpf_mav_passwort.getPassword()).
//                    equals(jpf_mav_rep_passwort.getPassword())){
//                pruef = false;
//            }
        }
        if (!pruef) {
            fehler = "Passwort und Passwortwiederholung stimmen nicht überein.";
        }
        return pruef;
    }

    /**
     * Prüfung, ob bei Neuanmeldung alle Eingabefelder befüllt sind
     *
     * @return true, wenn alle Felder befüllt sind
     * @version 1.1
     */
    private boolean pruefeEingabe() {
        boolean pruef = true;
        if (jtf_mav_benutzername.getText().trim().isEmpty() 
                || jpf_mav_passwort.getPassword().length == 0 
                || jpf_mav_rep_passwort.getPassword().length == 0) {
            fehler = "Sie haben nicht alle Felder ausgefüllt.";
            pruef = false;
        }
        if (pruef) {
            pruef = pruefePasswortIdentisch();
        }
        return pruef;
    }
    
    /**
     * Anzeigen des Dialogfensters für die Fehlermeldung
     *
     * @param fehlermeldung anzuzeigende Fehlermeldung
     * @version 1.1
     */
    private void errorMessage(String fehlermeldung) {
        JOptionPane.showMessageDialog(null, fehlermeldung, "Datenproblem", 
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Getränkeliste speichern
     *
     * @version 1.1
     */
    private void gv_speichern() {
        KassensystemIO.getIOInstanz().updateGetraenkeliste(getraenkeliste);
    }

    /**
     * Getränk in Formular anzeigen
     *
     * @version 1.1
     */
    private void showGetraenk() {
        txt_gv_bezeichnung.setText(getraenkeliste.get(index).getBezeichnung());
        txt_gv_volumen.setText(String.valueOf(getraenkeliste.
                get(index).getVolumen()));
        txt_gv_preis.setText(String.valueOf(getraenkeliste.
                get(index).getPreis()));
    }
    
    /**
     * Anzeige der Speise im Formular
     *
     * @param haupt true für Hauptkomponente, false für Beilage
     * @version 1.1
     */
    private void showSpeise(boolean haupt) {
        if (haupt) {
            for (Hauptkomponente h : hauptkomponentenliste){
                System.out.println(h.getBeschreibung());
            }
            txt_sv_bezeichnung.setText(hauptkomponentenliste.get(index).getBeschreibung());
            txt_sv_preis.setText(String.valueOf(hauptkomponentenliste.get(index).getPreis()));
        } else {
            // Zählvariable für String-Array-Index
            int k = 0;
            // Collation Set temp wird befüllt mit den Keys aus 
            // der HashMap beilagenliste.
            Set<String> temp = beilagenliste.keySet();
            // Beilage ist ein String-Array (Zugriff über die ganze
            // Klasse. Die Größe wird anhand des Set's ermittelt, d. h.
            // die Information, wieviele Elemente in dieses String-
            // Array gepackt werden sollen.
            beilage = new String[temp.size()];
            // Befüllen des Stringarrays mit den einzelnen
            // Keys(Eierreis, Gebratene Nudeln, usw.)
            for (String s : temp) {
                beilage[k++] = s;
            }
            // Bezeichnung ist der jeweilige Schlüssel, den wir
            // nun im String-Array haben.
            txt_sv_bezeichnung.setText(beilage[index]);
            // Auslesen des Preises über den ermittelten Schlüssel
            // aus dem String-Array
            txt_sv_preis.setText(String.valueOf(beilagenliste.
                    get(beilage[index])));
        }
        txt_sv_preis.setEditable(true);
    }

    /**
     * Prüfung, ob Einträge in der Getränkeverwaltung vollständig sind
     *
     * @return true, wenn Einträge vollständig
     * @version 1.1
     */
    private boolean gv_eintrag_vollstaendig() {
        boolean pruef = true;
        if (txt_gv_volumen.getText().trim().isEmpty()
                || txt_gv_preis.getText().trim().isEmpty()) {
            pruef = false;
            fehler = "Einträge unvollständig.";
        }
        if (pruef) {
            try {
                Double.valueOf(txt_gv_volumen.getText().trim());
                Double.valueOf(txt_gv_preis.getText().trim());
            } catch (Exception ex) {
                pruef = false;
                fehler = "Falsches Zahlformat";
            }
        }
        return pruef;
    }

    /**
     * Speichern der Speisen
     *
     * @param auswahl 0 für Hauptkomponente, 1 für Beilage
     * @version 1.1
     */
    private void svSpeichern(int auswahl) {
        switch (auswahl) {
            case 0:
                KassensystemIO.getIOInstanz().updateHauptkomponentenliste(hauptkomponentenliste);
                break;
            case 1:
                KassensystemIO.getIOInstanz().updateBeilagenliste(beilage, beilagenliste);
                break;
            default:
                break;
        }
    }
    
    /**
     * Update und Speichern der Getränke
     *
     * @version 1.1
     */
    private void updateAndSave() {
        getraenkeliste.get(index).setPreis(Double.valueOf(txt_gv_preis.getText().trim()));
        getraenkeliste.get(index).setVolumen(Double.valueOf(txt_gv_volumen.getText().trim()));
        gv_speichern();
    }

    /**
     * Update und Speichern der Speisen
     *
     * @param auswahl (0 = Hauptkomponente, 1 = Beilage)
     */
    private void updateAndSave(int auswahl) {
        double preis = Double.valueOf(txt_sv_preis.getText().trim());
        switch (auswahl) {
            case 0:
                hauptkomponentenliste.get(index).setPreis(preis);
                svSpeichern(auswahl);
                break;
            case 1:
                beilagenliste.put(beilage[index], preis);
                svSpeichern(auswahl);
                break;
            default:
                break;
        }
    }
    
    /**
     * Prüfen des Preises bei der Speisenverwaltung
     *
     * @return true, wenn Preis korrekt
     * @version 1.1
     */
    private boolean pruefePreis() {
        try {
            Double.valueOf(txt_sv_preis.getText().trim());
            if (txt_sv_preis.getText().trim().isEmpty()) {
                fehler = "Kein Preis eingetragen";
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            fehler = "Falsches Preisformat 0.0";
            return false;
        }
    }
//    
//    private int getSelectedRadiobutton(){
//        int radio = 0;
//        if (rbt_haupt.isSelected()){
//            radio = 1;
//        }
//        if (rbt_beilage.isSelected()){
//            radio = 2;
//        }
//        return radio;
//    }
    
    /**
     * Eventhandling ActionListener
     *
     * @param e auslösendes Objekt
     * @version 1.1
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt_abbrechen
                || e.getSource() == bt_mav_break
                || e.getSource() == bt_gv_break
                || e.getSource() == bt_sv_break) {
            verwaltung.dispose();
        }

    // Eventhandling Speisenverwaltung
        if (e.getSource() == bt_sv_first
                || e.getSource() == bt_sv_last
                || e.getSource() == bt_sv_before
                || e.getSource() == bt_sv_next) {
            boolean pruef = true;
            pruef = pruefePreis();
            if (pruef) {
                updateAndSave(auswahl);

            } else {
                errorMessage(fehler);
            }
            if (e.getSource() == bt_sv_first) {
                index = 0;
                bt_sv_before.setEnabled(false);
                if (auswahl == 0) {
                    if (sv_h_laenge <= 1) {
                        bt_sv_next.setEnabled(false);
                    } else {
                        bt_sv_next.setEnabled(true);
                    }
                } else {
                    if (sv_b_laenge <= 1) {
                        bt_sv_next.setEnabled(false);
                    } else {
                        bt_sv_next.setEnabled(true);
                    }
                }
            } else if (e.getSource() == bt_sv_last) {
                switch (auswahl) {
                    case 0:
                        index = sv_h_laenge - 1;
                        if (index < 1) {
                            bt_sv_before.setEnabled(false);
                        } else {
                            bt_sv_before.setEnabled(true);
                        }
                        break;
                    case 1:
                        index = sv_b_laenge - 1;
                        if (index < 1) {
                            bt_sv_before.setEnabled(false);
                        } else {
                            bt_sv_before.setEnabled(true);
                        }
                        break;
                    default:
                        break;
                }
                bt_sv_next.setEnabled(false);
            } else if (e.getSource() == bt_sv_next) {
                index++;
                bt_sv_before.setEnabled(true);
                switch (auswahl) {
                    case 0:
                        if (index == sv_h_laenge - 1) {
                            bt_sv_next.setEnabled(false);
                        } else {
                            bt_sv_next.setEnabled(true);
                        }
                        break;
                    case 1:
                        if (index == sv_b_laenge - 1) {
                            bt_sv_next.setEnabled(false);
                        } else {
                            bt_sv_next.setEnabled(true);
                        }
                        break;
                    default:
                        break;
                }
            } else if (e.getSource() == bt_sv_before) {
                index--;
                bt_sv_next.setEnabled(true);
                if (index == 0) {
                    bt_sv_before.setEnabled(false);
                } else {
                    bt_sv_before.setEnabled(true);
                }
                switch (auswahl) {
                    case 0:
                        if (sv_h_laenge <= 1) {
                            bt_sv_next.setEnabled(false);
                        } else {
                            bt_sv_next.setEnabled(true);
                        }
                        break;
                    case 1:
                        if (sv_h_laenge <= 1) {
                            bt_sv_next.setEnabled(false);
                        } else {
                            bt_sv_next.setEnabled(true);
                        }
                        break;
                    default:
                        break;
                }
            }
            if (auswahl == 0) {
                showSpeise(true);
            } else {
                showSpeise(false);
            }
        }
        // 0 = Hauptkomponente
        // 1 = Beilage
        if (e.getSource() == rbt_haupt || e.getSource() == rbt_beilage) {
            index = 0;
            if (e.getSource() == rbt_haupt) {
                auswahl = 0;
                sv_h_laenge = hauptkomponentenliste.size();
                if (sv_h_laenge <= 1) {
                    bt_sv_next.setEnabled(false);
                } else {
                    bt_sv_next.setEnabled(true);
                }
                showSpeise(true);
            } else {
                sv_b_laenge = beilagenliste.size();
                auswahl = 1;
                showSpeise(false);
                if (sv_b_laenge <= 1) {
                    bt_sv_next.setEnabled(false);
                } else {
                    bt_sv_next.setEnabled(true);
                }
            }
            bt_sv_first.setEnabled(true);
            bt_sv_last.setEnabled(true);
            bt_sv_before.setEnabled(false);

        }    
       
        // Eventhandling Getränkeverwaltung
        if (e.getSource() == bt_gv_new) {
            txt_gv_bezeichnung.setText("");
            txt_gv_bezeichnung.setEditable(true);
            txt_gv_volumen.setText("");
            txt_gv_preis.setText("");
            bt_gv_save.setVisible(true);
        }
        if (e.getSource() == bt_gv_next
                || e.getSource() == bt_gv_first
                || e.getSource() == bt_gv_last
                || e.getSource() == bt_gv_before) {
            boolean pruef = true;
            pruef = gv_eintrag_vollstaendig();
            if (pruef) {
                updateAndSave();
                if (e.getSource() == bt_gv_next) {
                    index++;
                    bt_gv_before.setEnabled(true);
                    if (index == gv_laenge - 1) {
                        bt_gv_next.setEnabled(false);
                    }else{
                        bt_gv_next.setEnabled(true);
                    }
                } else if (e.getSource() == bt_gv_first) {
                    index = 0;
                    bt_gv_before.setEnabled(false);
                    if (gv_laenge > 1) {
                        bt_gv_next.setEnabled(true);
                    }
                }else if (e.getSource() == bt_gv_last){
                    index = gv_laenge - 1;
                    bt_gv_next.setEnabled(false);
                    if (gv_laenge > 1){
                        bt_gv_before.setEnabled(true);
                    }
                }else if (e.getSource() == bt_gv_before){
                    index--;
                    bt_gv_next.setEnabled(true);
                    if (index != 0){
                        bt_gv_before.setEnabled(true);
                    }else{
                        bt_gv_before.setEnabled(false);
                    }
                }
                showGetraenk();

            } else {
                errorMessage(fehler);
            }
        }

        if (e.getSource() == bt_gv_save) {
            boolean pruef = true;
            double vol = 0.0, preis = 0.0;
            if (txt_gv_bezeichnung.getText().trim().isEmpty()
                    || txt_gv_volumen.getText().trim().isEmpty()
                    || txt_gv_preis.getText().trim().isEmpty()) {
                pruef = false;
                fehler = "Nicht alle Felder ausgefüllt";
            }
            if (pruef) {
                try {
                    vol = Double.valueOf(txt_gv_volumen.getText().trim());
                    preis = Double.valueOf(txt_gv_preis.getText().trim());
                } catch (Exception ex) {
                    pruef = false;
                    fehler = "Preis oder Volumenformat unkorrekt";

                }
            }
            if (pruef) {
                getraenkeliste.add(new Getraenk(txt_gv_bezeichnung.
                        getText().trim(), vol, preis));
                gv_laenge = getraenkeliste.size();
                index = gv_laenge - 1;
                gv_speichern();
                bt_gv_save.setVisible(false);
                txt_gv_bezeichnung.setEditable(false);
            }
        }

        // Eventhandling Authentifizierung
        if (e.getSource() == bt_anmelden) {
            boolean pruef = pruefeAuthentifizierung();
            pruef = true;
            if (pruef) {
                verwaltungsaktEinblenden();
            } else {
                errorMessage(fehler);
            }
        }

        // Eventhandlich Mitarbeiterverwaltung
        if (e.getSource() == bt_mav_save) {
            boolean pruef = pruefeEingabe();
            if (pruef) {
                try {
                    String benutzerSHA512 = EncryptPassword.SHA512(jtf_mav_benutzername.getText().trim());
                    String passwortSHA512 = EncryptPassword.SHA512(Arrays.toString(jpf_mav_passwort.getPassword()));
                    mitarbeiterliste.add(new Mitarbeiter(benutzerSHA512, passwortSHA512));
                    KassensystemIO.getIOInstanz().updateMitarbeiterliste(mitarbeiterliste);
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    errorMessage("Daten können nicht verschlüsselt werden.");
                }
            } else {
                errorMessage(fehler);
            }
        }
    }    
}
