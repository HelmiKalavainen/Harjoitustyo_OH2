package com.example.harjoitustyo_oh2;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.io.*;
import java.util.StringTokenizer;

/**
 * Luokka Kayttoliittyma toteuttaa yksinkertaisen tuotteiden tallentimen
 *
 */
public class Kayttoliittyma extends Application {

    /**
     * Tuotteen nimi teksitkenttä.
     */
    private TextField tfTuote = new TextField();
    /**
     * Tuotteen tunnus tekstikenttä.
     */
    private TextField tfTunnus = new TextField();
    /**
     * Tuotteen hinta tekstikenttä.
     */
    private TextField tfHinta = new TextField();
    /**
     * Kappalemäärä tekstikenttä.
     */
    private TextField tfMaara = new TextField();
    /**
     * Tallenna tuote painike.
     */
    private Button btTallenna = new Button("Tallenna");
    /**
     * Hae tuotetta painike.
     */

    private Button btHae = new Button("Hae tuotetta");

    /**
     * @param args
     * Testipääohjelma, ei parametreja
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @param primaryStage
     * Ohjelmaikkunan kaynnistyksen ja toiminnallisuuden maarittely
     */
    @Override
    public void start(Stage primaryStage) {

        //paneeli
        GridPane paneeli = new GridPane();
        final VBox vbox = new VBox();

        //asetetaan tilaa solujen välille
        vbox.setPadding(new Insets(10, 10, 10, 10));
        paneeli.setHgap(10);
        paneeli.setVgap(10);
        //asetetaan tekstikentät koordinaatein paneeliin

        /**
         * tekstialue tuote tekstikentälle
         */
        paneeli.add(new Label("Tuote:"), 0, 0);
        paneeli.add(tfTuote, 1, 0);
        /**
         * tekstialue tunnus tekstikentälle
         */

        paneeli.add(new Label("Tunnus:"), 0, 1);
        paneeli.add(tfTunnus, 1, 1);

        paneeli.add(new Label("Hinta(€):"), 0, 2);
        paneeli.add(tfHinta, 1, 2);

        paneeli.add(new Label("Määrä:"), 0, 3);
        paneeli.add(tfMaara, 1, 3);

        ObservableList<Tuote> tuoteLista = FXCollections.observableArrayList();

        //table view hakutulokselle
        TableView tvTulos = new TableView<Tuote>();

        //taulukon sarakkeet ja niille asetetut datatyypit
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


/**
 * Tallentaa tuotteiden tiedot tiedostoon tuotteet.txt.
 *
 */
        TuotteetTiedostonKasittelija tiedostonKasittelija = new TuotteetTiedostonKasittelija(tuoteLista);

        btTallenna.setOnAction(ActionEvent -> {

            // Oletus värit, css tiedoston stylesheet:stä
            tfTuote.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");
            tfTunnus.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");
            tfHinta.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");
            tfMaara.setStyle("-fx-border-color: blue; -fx-border-width: 1px ;");

            boolean error = false;

            if (tfTuote.getText().isEmpty()) {
                tfTuote.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }


            // Tarkistetaan onko arvo numero, opetusavustaja auttoi tässä.
            // Lähde: https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters
            if (!isNumeric(tfTunnus.getText())) {
                tfTunnus.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }

            if (!isNumeric(tfHinta.getText())) {
                tfHinta.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
                error = true;
            }

            if (!isNumeric(tfMaara.getText())) {
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
            tiedostonKasittelija.tallennaTiedostoon(uusiTuote, "tuotteet.txt");

        });


             /**try (BufferedWriter writer = new BufferedWriter(new FileWriter("tuotteet.txt", true))) {
                writer.write(String.format("%s %d %s %d", uusiTuote.getNimi(), uusiTuote.getTuotenro(), Double.toString(uusiTuote.getHinta()), uusiTuote.getMaara()));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
              */



        //Huom! jos tiedostossa on tyhjä rivi, se aiheuttaa virheen tuotetta hakiessa

        btHae.setOnAction(e -> {
            int tunnus = Integer.parseInt(tfTunnus.getText());
            tuoteLista.clear();
            String tiedosto = "tuotteet.txt";

            tiedostonKasittelija.lataaTiedostosta(tuoteLista, "tuotteet.txt", tunnus);


            /**try (BufferedReader reader = new BufferedReader(new FileReader(tiedosto))) {
                String rivi;
                while ((rivi = reader.readLine()) != null) {
                    StringTokenizer ST = new StringTokenizer(rivi, " ");
                    String nimi = ST.nextToken().toString();
                    int tunnusnro = Integer.parseInt(ST.nextToken());
                    double hinta = Double.parseDouble(ST.nextToken());
                    int maara = Integer.parseInt(ST.nextToken());
                    if (tunnus == tunnusnro) tuoteLista.add(new Tuote(tunnus, nimi, hinta, maara));
                }
                /**
                 * Poikkeus jos tiedostoa ei löydy


            } catch (IOException ex) {
                System.out.println("Tiedostoa ei löytynyt!");
                throw new RuntimeException(ex);
            }
            */

            //popup ikkuna jos haetulla tuotenumerolla ei löydy tuotteita
                Popup virhe = new Popup();
                Label lbVirhe = new Label("Virhe! Tuotteita ei löytynyt tai tuotenumero on väärä.");
                lbVirhe.setStyle("-fx-background-color: white; ");
                lbVirhe.setStyle("-fx-border-color: red; -fx-border-width: 1px ;");
                virhe.getContent().add(lbVirhe);
                lbVirhe.setMinWidth(100);
                lbVirhe.setMinHeight(80);
                virhe.setAutoHide(true);
            if (tuoteLista.isEmpty()) {
                virhe.show(primaryStage);
            }
        });

        /**ChoiceBox<String> cbKategoria = new ChoiceBox<>();
        cbKategoria.getItems().addAll("Elektroniikka", "Käyttötavara", "Elintarvike");

        // Lisää tapahtumankäsittelijä, joka asettaa valitun kategorian tuotteelle
        cbKategoria.setOnAction(event -> {
            Tuote selectedTuote = (Tuote) tvTulos.getSelectionModel().getSelectedItem();
            if (selectedTuote != null) {
                selectedTuote.setKategoria(cbKategoria.getValue());
            }
        });
         */


        paneeli.add(tvTulos, 1, 5);
        tvTulos.setEditable(true);

        // lisätään tallenna painike
        paneeli.add(btTallenna, 1, 4);

        //lisätään hae tuotetta- painike
        paneeli.add(btHae, 2, 1);

        //lisätään kategoria valikko
        paneeli.add(new Label("Kategoria:"), 0, 4);

        vbox.getChildren().add(paneeli);

        Scene scene = new Scene(vbox, 800, 800);
        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("Tuote tallennin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        /**
         * @author https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
         *
         * */
        public static boolean isNumeric (String str){
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Virhe!");
                return false;
            }
        }
    }
