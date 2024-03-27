module com.example.harjoitustyo_oh2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.harjoitustyo_oh2 to javafx.fxml;
    exports com.example.harjoitustyo_oh2;
}