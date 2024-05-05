package com.example.harjoitustyo_oh2;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.StringTokenizer;
/**
 * Luokka `TuotteetTiedostonKasittelija` vastaa tuotetietojen tallentamisesta ja lataamisesta tiedostosta.
 * Siinä on metodit tuotteiden tallentamiseen ja lataamiseen.
 */
public class TuotteetTiedostonKasittelija implements TiedostonKasittelija {
    private ObservableList<Tuote> tuoteLista;

    /**
     * Luo uuden `TuotteetTiedostonKasittelija`-olion annetulla tuotelistalla.
     * @param tuoteLista
     */

    public TuotteetTiedostonKasittelija(ObservableList<Tuote> tuoteLista) {
        this.tuoteLista = tuoteLista;
    }

    /**
     *
     * @param uusiTuote tallennettava tuote
     * @param s tiedosto
     */

    @Override
    public void tallennaTiedostoon(Tuote uusiTuote, String s) {
        int tuotenro = uusiTuote.getTuotenro();
        String nimi = uusiTuote.getNimi();
        double hinta = uusiTuote.getHinta();
        int maara = uusiTuote.getMaara();


        tuoteLista.add(uusiTuote);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s, true))) {
            writer.write(String.format("%s %d %s %d", uusiTuote.getNimi(), uusiTuote.getTuotenro(), Double.toString(uusiTuote.getHinta()), uusiTuote.getMaara()));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    /**
     *
     * @param tuoteLista lista tuotteille
     * @param tiedosto tiedosto josta tuotteet ladataan
     * @param tunnus tunnus jolla tuotteet ladataan
     * Poikkeus jos tiedostoa ei löydy
     */

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