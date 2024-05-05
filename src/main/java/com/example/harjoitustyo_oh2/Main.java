package com.example.harjoitustyo_oh2;

import java.util.Scanner;
 public class Main {
     /**
      * Testipääohjelma, ei parametreja
      *
      * @param args
      */

     public static void main(String[] args) {
         Scanner lukija = new Scanner(System.in);

         //syöte tuotteen tunnukselle
         System.out.print("Tuotenumero: ");
         int tuotenro = lukija.nextInt();

         lukija.nextLine();
        //syöte tuoetteen nimelle

         System.out.print("Tuotteen nimi: ");
         String nimi = lukija.nextLine();

        //Syöte tuotteen hinnalle
         System.out.print("Hinta: ");
         double hinta = lukija.nextDouble();

         //tehdään tuote objekti
         Tuote tuote = new Tuote(122, "Sprite", 1.5, 55);

         //Näytetään tuotteen tiedot
         System.out.println("Tuote luotu: "+tuote.getNimi()+", Tuotenro: "+tuote.getTuotenro()+", Hinta: "+tuote.getHinta());
 }
}