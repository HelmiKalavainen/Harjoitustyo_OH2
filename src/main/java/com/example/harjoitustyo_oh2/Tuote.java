package com.example.harjoitustyo_oh2;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Luokka toteuttaa Tuotteen jolla on tuotenumero, hinta, nimi ja määrä.
 */

 public class Tuote{

    /**
     * tuotenumero kokonaislukuna
     */
    private int tuotenro;

    /**
     * hinta desimaalilukuna
     */

    private double hinta;

    /**
     * nimi merkkijonona
     */

    private String nimi;

    /**
     * kappalemäärä kokonaislukuna.
     */

    private int maara;

    /**
     * Tuote alustaja
     *
     * @param tuotenro tuote kohtainen ID numero
     * @param nimi     tuotteen nimi
     * @param hinta    tuotteen kappale hinta
     * @param maara    tuotteiden määrä
     */
    public Tuote(int tuotenro, String nimi, double hinta, int maara) {
        this.tuotenro = tuotenro;
        this.hinta = hinta;
        this.nimi = nimi;
        this.maara = maara;
    }
    /**
     * palauttaa nimen
     *
     * @return String
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * @param nimi asettaa nimen
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * @return palauttaa hinnan
     */
    public double getHinta() {
        return hinta;
    }

    /**
     * @param hinta asettaa hinnan
     */
    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    /**
     * @return palauttaa tuotenumeron
     */
    public int getTuotenro() {
        return tuotenro;
    }

    /**
     * @param tuotenro asettaa tuotenumeron
     */
    public void setTuotenro(int tuotenro) {
        this.tuotenro = tuotenro;
    }

    /**
     * @return palauttaa määrän
     */
    public int getMaara() {
        return maara;
    }

    /**
     * @param maara asettaa määrän
     */
    public void setMaara(int maara) {
        this.maara = maara;
    }
}

