package com.example.harjoitustyo_oh2;

import javafx.collections.ObservableList;
public interface TiedostonKasittelija {

    void tallennaTiedostoon(Tuote uusiTuote, String s);


    void lataaTiedostosta(ObservableList<Tuote> tuoteLista, String tiedosto, int tunnus);

    }


