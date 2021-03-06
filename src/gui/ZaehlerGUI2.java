/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author GürgensRobert
 */
public class ZaehlerGUI2 {
    private JPanel zeichenflaeche;
    private Zaehler zaehler;
    private JLabel kmStandAnzeige;
    private JTextField deltaTextFeld;
    
    public ZaehlerGUI2() {
        initGUI();
    }
    private void initGUI() {
        zaehler = new Zaehler();
        LAFEinstellung.setJavaLookandFeel();
        
        zeichenflaeche = new Zeichenflaeche(new Dimension(200, 320),
                new Point(25, 25), Color.LIGHT_GRAY, "KM-Stand");
        //zeichenflaeche.setBounds(25, 25, 200, 230);
        //zeichenflaeche.setBackground(Color.LIGHT_GRAY);
        zeichenflaeche.setLayout(null);
        JLabel  ueberschrift = new JLabel("KM-Stand");
        ueberschrift.setBounds(25, 25, 150, 20);
        ueberschrift.setForeground(Color.BLUE);
        ueberschrift.setHorizontalAlignment(SwingConstants.CENTER);
        zeichenflaeche.add(ueberschrift);
        
        kmStandAnzeige = new JLabel(Integer.toString(zaehler.getZaehlerstand()));
        kmStandAnzeige.setBounds(25,100,150,20);
        kmStandAnzeige.setForeground(Color.WHITE);
        kmStandAnzeige.setHorizontalAlignment(SwingConstants.CENTER);
        zeichenflaeche.add(kmStandAnzeige);
        
        JButton erhoeheUmEinsKnopf = new JButton("Erhöhe um 1");
    erhoeheUmEinsKnopf.setBounds(25,175,150,30);
    erhoeheUmEinsKnopf.setBackground(Color.YELLOW);
    zeichenflaeche.add(erhoeheUmEinsKnopf);
    
    JButton setzeAufNullKnopf = new JButton("Setze auf 0");
    setzeAufNullKnopf.setBounds(25, 225, 150, 30);
    setzeAufNullKnopf.setBackground(Color.YELLOW);
    zeichenflaeche.add(setzeAufNullKnopf);
    
    JButton erhoeheUmXKnopf = new JButton("Erhöhe um:");
    erhoeheUmXKnopf.setBounds(25, 275, 100, 30);
    erhoeheUmXKnopf.setBackground(Color.YELLOW);
    zeichenflaeche.add(erhoeheUmXKnopf);
    
    deltaTextFeld = new JTextField();
    deltaTextFeld.setBounds(130,275,45,30);
    zeichenflaeche.add(deltaTextFeld);
    
    //Ereignisse verbeiten mit Lambda-Ausdrücken
    erhoeheUmEinsKnopf.addActionListener(
            event -> {
                zaehler.erhoeheUmeins();
                kmStandAnzeige.setText(Integer.toString(zaehler.getZaehlerstand()));
            }
    );
    
    setzeAufNullKnopf.addActionListener(
            event -> {
                zaehler.setzeAufNull();
                kmStandAnzeige.setText(Integer.toString(zaehler.getZaehlerstand()));
            }
    );
    erhoeheUmXKnopf.addActionListener(
    event -> {
        try {
            int delta = Integer.parseInt(deltaTextFeld.getText());
            zaehler.erhoeheUm(delta);
            kmStandAnzeige.setText(Integer.toString(zaehler.getZaehlerstand()));
        } catch (NumberFormatException ausnahme) {
            Toolkit.getDefaultToolkit().beep();
            deltaTextFeld.setText("");
        }
    }
    );
    }
    public JPanel gibZeichenflaeche() {
        return zeichenflaeche;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ZaehlerGUI2 zGUI = new ZaehlerGUI2();
                JFrame einFenster = FensterBauerKom.getFenster("Kilometerzähler",
                        new Dimension(260, 410), new Point(25,25), false, true, Stil.JAVA);
                einFenster.getContentPane().setLayout(null);
                einFenster.add(zGUI.gibZeichenflaeche());
                einFenster.setVisible(true);
            }
        });
        
    }
}
