package com.example.harjoitustyo_oh2;

import javafx.collections.ObservableList;


public interface TiedostonKasittelija {
    /**
     * Tiedostonkäsittely rajapinta
     * @param uusiTuote
     * @param s
     */

    void tallennaTiedostoon(Tuote uusiTuote, String s);

    /**
     *
     * @param tuoteLista lista tuotteille
     * @param tiedosto tiedosto josta tuotteet ladataan
     * @param tunnus tunnus jolla tuotteet ladataan
     */
    void lataaTiedostosta(ObservableList<Tuote> tuoteLista, String tiedosto, int tunnus);
    }


