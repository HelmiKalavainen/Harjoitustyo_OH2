package com.example.harjoitustyo_oh2;
import com.example.harjoitustyo_oh2.Tuote;

import java.util.Scanner;
 public class Main {
     /**
      * Testipääohjelma, ei parametreja
      *
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

         System.out.println("Tuote luotu: "+tuote.getNimi()+", Tuotenro: "+tuote.getTuotenro()+", Hinta: "+tuote.getHinta());
 }
}

