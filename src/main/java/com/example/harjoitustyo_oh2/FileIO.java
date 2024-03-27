package com.example.harjoitustyo_oh2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class FileIO{
    public static void main(String[] args) {
        {
            try{
                File file = new File("myFile.txt");
                Scanner scanner = new Scanner(file);
                //onko tiedosto olemassa???
                if (!file.exists()) {
                    System.out.println("Tiedosto on olemassa.");
                }
            }
            catch (IOException e) {


            }
        }

    }
}
