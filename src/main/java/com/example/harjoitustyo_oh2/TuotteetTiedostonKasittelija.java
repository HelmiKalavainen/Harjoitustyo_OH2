package com.example.harjoitustyo_oh2;

import javafx.collections.ObservableList;
import java.io.*;
import java.util.StringTokenizer;

public class TuotteetTiedostonKasittelija implements TiedostonKasittelija {
    private ObservableList<Tuote> tuoteLista;

    public TuotteetTiedostonKasittelija(ObservableList<Tuote> tuoteLista) {
        this.tuoteLista = tuoteLista;
    }

    @Override
    public void tallennaTiedostoon(Tuote uusiTuote, String s) {
        int tuotenro = uusiTuote.getTuotenro();
        String nimi = uusiTuote.getNimi();
        double hinta = uusiTuote.getHinta();
        int maara = uusiTuote.getMaara();


        tuoteLista.add(uusiTuote); // Using the instance variable tuoteLista

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s, true))) {
            writer.write(String.format("%s %d %s %d", uusiTuote.getNimi(), uusiTuote.getTuotenro(), Double.toString(uusiTuote.getHinta()), uusiTuote.getMaara()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public void lataaTiedostosta(ObservableList<Tuote> tuoteLista, String tiedosto, int tunnus) {

        try (BufferedReader reader = new BufferedReader(new FileReader(tiedosto))) {
            String rivi;
            while ((rivi = reader.readLine()) != null) {
                StringTokenizer ST = new StringTokenizer(rivi, " ");
                String nimi = ST.nextToken().toString();
                int tunnusnro = Integer.parseInt(ST.nextToken());
                double hinta = Double.parseDouble(ST.nextToken());
                int maara = Integer.parseInt(ST.nextToken());
                if (tunnus==tunnusnro){
                    tuoteLista.add(new Tuote(tunnus, nimi, hinta, maara));
            }
            }
        } catch (IOException ex) {
            System.out.println("Tiedostoa ei löytynyt!");
            throw new RuntimeException(ex);
        }
    }
}