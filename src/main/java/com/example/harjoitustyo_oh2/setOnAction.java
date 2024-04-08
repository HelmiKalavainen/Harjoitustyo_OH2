//package com.example.harjoitustyo_oh2;
/*
*
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class setOnAction {
    private TextField tfTuote;
    private TextField tftunnus;




    private void saveDataToTable() {
        String data = tfTuote.getText();
        if (!data.isEmpty()) {
            // Add data to table
            tableModel.addRow(new Object[]{data});

            // Save data to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt", true))) {
                writer.write(data + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showmessageDialog(frame, "Error occurred while saving data to file.");
            }

            textField.setText(""); // Clear the text field after saving
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter some data.");
        }
    }
}

 */