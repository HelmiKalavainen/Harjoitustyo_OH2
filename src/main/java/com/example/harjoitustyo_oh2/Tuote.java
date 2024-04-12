package com.example.harjoitustyo_oh2;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Luokka toteuttaa Tuotteen jolla on tuotenumero, hinta, nimi ja määrä.
 */

public class Tuote implements Serializable {


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
     * @param tuotenro tuote kohtainen ID numero
     * @param nimi tuotteen nimi
     * @param hinta tuotteen kappale hinta
     * @param maara tuotteiden määrä
     */
    public Tuote(int tuotenro, String nimi, double hinta, int maara) {
        this.tuotenro = tuotenro;
        this.hinta = hinta;
        this.nimi = nimi;
        this.maara = maara;
    }

    /**
     * Testipääohjelma, ei parametreja
     * @param args
     */
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        System.out.print("Tuotenumero: ");
        int tuotenro = lukija.nextInt();

        lukija.nextLine();

        System.out.print("Tuotteen nimi: ");
        String nimi = lukija.nextLine();

        System.out.print("Hinta: ");
        double hinta = lukija.nextDouble();

        Tuote tuote = new Tuote(122, "Sprite", 1.5, 55);

        System.out.println("Tuote luotu: " + tuote.getNimi() + ", Tuotenro: " + tuote.getTuotenro() + ", Hinta: " + tuote.getHinta());

    }

    /**
     * palauttaa nimen
     * @return String
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * @param nimi
     * asettaa nimen
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    /**
     * @return
     * palauttaa hinnan
     */
    public double getHinta() {
        return hinta;
    }

    /**
     * @param hinta
     *  asettaa hinnan
     */
    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    /**
     * @return
     *  palauttaa tuotenumeron
     */
    public int getTuotenro() {
        return tuotenro;
    }

    /**
     * @param tuotenro
     * asettaa tuotenumeron
     */
    public void setTuotenro(int tuotenro) {
        this.tuotenro = tuotenro;
    }

    /**
     * @return
     * palauttaa määrän
     */
    public int getMaara() {
        return maara;
    }

    /**
     * @param maara
     *  asettaa määrän
     */
    public void setMaara(int maara) {
        this.maara = maara;
    }
}
