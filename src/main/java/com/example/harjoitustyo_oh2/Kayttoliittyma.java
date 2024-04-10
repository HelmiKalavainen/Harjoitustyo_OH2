package com.example.harjoitustyo_oh2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

        paneeli.add(new Label("Tuote:"), 0, 0);
        paneeli.add(tfTuote, 1, 0);

        paneeli.add(new Label("Tunnus:"), 0, 1);
        paneeli.add(tfTunnus, 1, 1);

        paneeli.add(new Label("Hinta(€):"), 0, 2);
        paneeli.add(tfHinta, 1, 2);

        paneeli.add(new Label("Määrä:"), 0, 3);
        paneeli.add(tfMaara, 1, 3);

        ObservableList<Tuote> tuoteLista = FXCollections.observableArrayList();

        //table view hakutulokselle
        TableView tvTulos = new TableView<Tuote>();
        //taulukon sarakkeet
        TableColumn tcTuote = new TableColumn<Tuote, String>("Tuote");
        TableColumn tcTunnus = new TableColumn<Tuote, Integer>("Tunnus");
        TableColumn tcHinta = new TableColumn<Tuote, Double>("Hinta");
        TableColumn tcMaara = new TableColumn<Tuote, Integer>("Määrä");

        tcTuote.setCellValueFactory(new PropertyValueFactory<Tuote, String>("nimi"));
        tcTunnus.setCellValueFactory(new PropertyValueFactory<Tuote, Integer>("tuotenro"));
        tcHinta.setCellValueFactory(new PropertyValueFactory<Tuote, Double>("hinta"));
        tcMaara.setCellValueFactory(new PropertyValueFactory<Tuote, Integer>("maara"));
        tvTulos.setItems(tuoteLista);
        tvTulos.getColumns().addAll(tcTuote, tcTunnus, tcHinta, tcMaara);

        btTallenna.setOnAction(ActionEvent -> {

            // Oletus värit
            tfTuote.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");
            tfTunnus.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");
            tfHinta.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");
            tfMaara.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");

            boolean error = false;

            if (tfTuote.getText().isEmpty()) {
                tfTuote.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }


            // Tarkistetaan onko arvo numero, opetusavustaja auttoi.
            // Lähde: https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
            if (!isNumeric(tfTunnus.getText())) {
                tfTunnus.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }

            if (!isNumeric(tfHinta.getText())) {
                tfHinta.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }

            if (!isNumeric(tfHinta.getText())) {
                tfMaara.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }

            if (error) return;

            String tuote = tfTuote.getText();
            int tunnus = Integer.parseInt(tfTunnus.getText());
            double hinta = new Double(tfHinta.getText());
            int maara = Integer.parseInt(tfMaara.getText());

            // luodaan tuote-olio syötetyillä tiedoilla
            Tuote uusiTuote = new Tuote(tunnus, tuote, hinta, maara);
            tuoteLista.add(uusiTuote);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("tuotteet.txt", true))) {
                writer.write(String.format("%s %d %s %d", uusiTuote.getNimi(), uusiTuote.getTuotenro(), Double.toString(uusiTuote.getHinta()), uusiTuote.getMaara()));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btHae.setOnAction(e -> {
            int tunnus = Integer.parseInt(tfTunnus.getText());
            tuoteLista.clear();
            String tiedosto = "tuotteet.txt";
            try (BufferedReader reader = new BufferedReader(new FileReader(tiedosto))) {
                String rivi;
                while ((rivi = reader.readLine()) != null) {
                    StringTokenizer ST = new StringTokenizer(rivi, " ");
                    String nimi = ST.nextToken().toString();
                    int tunnusnro = Integer.parseInt(ST.nextToken());
                    double hinta = Double.parseDouble(ST.nextToken());
                    int maara = Integer.parseInt(ST.nextToken());
                    if (tunnus == tunnusnro) tuoteLista.add(new Tuote(tunnus, nimi, hinta, maara));
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });


        paneeli.add(tvTulos, 1, 5);
        tvTulos.setEditable(true);

        paneeli.add(btTallenna, 1, 4);
        paneeli.add(btHae, 2, 1);

        vbox.getChildren().add(paneeli);

        Scene scene = new Scene(vbox, 600, 500);
        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("Tuote tallennin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


        /**
         * @author https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
         * */
        public static boolean isNumeric (String str){
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
