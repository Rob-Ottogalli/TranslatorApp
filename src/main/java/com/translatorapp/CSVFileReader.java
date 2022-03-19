package com.translatorapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CSVFileReader {

    private BufferedReader buffReader;

    public CSVFileReader(String filename){

        try {
            FileReader reader = new FileReader(filename);
            buffReader = new BufferedReader(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Getter Method <p>
     * Gets the CSV object as BufferedReader
     * @return BufferedReader
     */
    public BufferedReader getCSV(){
        return buffReader;
    }

}
