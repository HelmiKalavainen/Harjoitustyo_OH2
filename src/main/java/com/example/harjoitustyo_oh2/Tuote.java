package com.example.harjoitustyo_oh2;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Luokka toteuttaa Tuotteen jolla on tuotenumero, hinta ja nimi
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

    private int maara;

    //tuote- luokka
    public Tuote(int tuotenro, String nimi, double hinta, int maara) {
        this.tuotenro = tuotenro;
        this.hinta = hinta;
        this.nimi = nimi;
        this.maara = maara;
    }

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);

        System.out.print("Tuotenumero: ");
        int tuotenro = lukija.nextInt();

        lukija.nextLine(); // rivinvaihdon, joka jäi nextInt():n jälkeen(???)

        System.out.print("Tuotteen nimi: ");
        String nimi = lukija.nextLine();

        System.out.print("Hinta: ");
        double hinta = lukija.nextDouble();

        Tuote tuote = new Tuote(122, "Sprite", 1.5, 55);

        System.out.println("Tuote luotu: " + tuote.getNimi() + ", Tuotenro: " + tuote.getTuotenro() + ", Hinta: " + tuote.getHinta());

    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public int getTuotenro() {
        return tuotenro;
    }

    public void setTuotenro(int tuotenro) {
        this.tuotenro = tuotenro;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }
}
