package com.example.harjoitustyo_oh2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Scanner;

public class Kayttoliittyma extends Application {
    private TextField tfTuote = new TextField();
    private TextField tfTunnus = new TextField();
    private TextField tfHinta = new TextField();

    private TableView tvTulos = new TableView();

    TableColumn tcTuote = new TableColumn("Tuote");
    TableColumn tcTunnus = new TableColumn("Tunnus");
    TableColumn tcHinta = new TableColumn("Hinta");
    TableColumn tcMaara = new TableColumn("Määrä");

    private Button btTallenna = new Button("Tallenna");
    private Button btHae = new Button("Hae tuotetta");


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane paneeli = new GridPane();
        final VBox vbox = new VBox();
        vbox.setPadding(new Insets(10, 10, 10, 10));
        paneeli.setHgap(10);
        paneeli.setVgap(10);
        paneeli.add(new Label("Tuote:"),0,0);
        paneeli.add(tfTuote, 1,0);

        paneeli.add(new Label("Tunnus:"),0,1);
        paneeli.add(tfTunnus, 1,1);

        paneeli.add(new Label("Hinta:"), 0,2);
        paneeli.add(tfHinta,1,2);

        tvTulos.getColumns().addAll(tcTuote, tcTunnus, tcHinta, tcMaara);
        paneeli.add(new Label("Tulos:"), 1, 5);
        ScrollPane Scroll = new ScrollPane(tvTulos);
        paneeli.add(tvTulos, 1, 5);
        tvTulos.setEditable(true);

        paneeli.add(btTallenna, 1,3);
        paneeli.add(btHae, 2, 1 );

        vbox.getChildren().add(paneeli);

        Scene scene = new Scene(vbox, 600,500);
        primaryStage.setTitle("Skannaa tuote");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    /** Luokka toteuttaa Tuotteen jolla on tuotenumero, hinta ja nimi
     *
     */

    public static class Tuote implements Serializable {


        /** tuotenumero kokonaislukuna
         *
         */
        private int tuotenro;

        /** hinta desimaalilukuna
         *
         */

        private double hinta;

        /** nimi merkkijonona
         *
         */

        private String nimi;

        private int maara;

        public Tuote(int tuotenro, String nimi, double hinta, int maara){
            this.tuotenro = tuotenro;
            this.hinta = hinta;
            this.nimi = nimi;
            this.maara = maara;
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
        public Tuote(int tuotenro, double hinta, String nimi){

        }

        public static void main(String[] args) {
            Scanner lukija = new Scanner(System.in);

            System.out.print("Tuotenumero: ");
            int tuotenro = lukija.nextInt();

            lukija.nextLine(); // Kuluttaa rivinvaihdon, joka jäi nextInt():n jälkeen(???)

            System.out.print("Tuotteen nimi: ");
            String nimi = lukija.nextLine();

            System.out.print("Hinta: ");
            double hinta = lukija.nextDouble();

            Tuote tuote = new Tuote(122, "Sprite", 1.5,55);

            System.out.println("Tuote luotu: " + tuote.getNimi() + ", Tuotenro: " + tuote.getTuotenro() + ", Hinta: " + tuote.getHinta());

        }
    }
}
