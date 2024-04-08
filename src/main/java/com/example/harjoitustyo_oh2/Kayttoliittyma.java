package com.example.harjoitustyo_oh2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.StringTokenizer;

public class Kayttoliittyma extends Application {
    //textfieldit joihin syötetään tuote sen tunnus ja hinta
     private TextField tfTuote = new TextField();
     private TextField tfTunnus = new TextField();
     private TextField tfHinta = new TextField();

     private TextField tfMaara = new TextField();

    //table view hakutulokselle
     TableView tvTulos = new TableView();

    //taulukon sarakkeet
    TableColumn tcTuote = new TableColumn("Tuote");
    TableColumn tcTunnus = new TableColumn("Tunnus");
    TableColumn tcHinta = new TableColumn("Hinta");
    TableColumn tcMaara = new TableColumn("Määrä");

    //tallennus- ja haku napit
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

        paneeli.add(new Label("Hinta(€):"), 0,2);
        paneeli.add(tfHinta,1,2);

        paneeli.add(new Label("Määrä:"), 0,3);
        paneeli.add(tfMaara, 1, 3);

        tvTulos.getColumns().addAll(tcTuote, tcTunnus, tcHinta, tcMaara);
        paneeli.add(new Label("Tulos:"), 1, 5);

        //scrollpane ei ehkä tarvita sillä se tulee automaattisesti kun dataa on tarpeeksi
        ScrollPane Scroll = new ScrollPane(tvTulos);

        btTallenna.setOnAction(ActionEvent -> {

            String tuote = tfTuote.getText();
            int tunnus = Integer.parseInt(tfTunnus.getText());
            double hinta = Double.parseDouble(tfHinta.getText());
            int maara = Integer.parseInt(tfMaara.getText());

            // luodaan tuote-olio syötetyillä tiedoilla
            Tuote uusiTuote = new Tuote(7882388, "Sprite", 1.95, 55);
            tvTulos.getItems().add(uusiTuote);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("tuotteet.txt", true))) {
                writer.write(uusiTuote.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Puuttuvia tietoja!");
            }
        });

        btHae.setOnAction(e-> {
            int tunnus = Integer.parseInt(tfTunnus.getText());
            String tiedosto = "tuotteet.txt";
            try(BufferedReader reader = new BufferedReader(new FileReader(tiedosto))){
                String rivi;
                while ((rivi = reader.readLine())!= null){
                    StringTokenizer ST = new StringTokenizer(rivi," ");
                    int tunnusnro = Integer.parseInt(ST.nextToken());
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        paneeli.add(tvTulos, 1, 5);
        tvTulos.setEditable(true);

        paneeli.add(btTallenna, 1,4);
        paneeli.add(btHae, 2, 1 );

        vbox.getChildren().add(paneeli);

        Scene scene = new Scene(vbox, 600,500);
        primaryStage.setTitle("Skannaa tuote");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
