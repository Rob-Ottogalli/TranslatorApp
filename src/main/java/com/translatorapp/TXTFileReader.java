package com.translatorapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TXTFileReader {

        private BufferedReader buffReader;

        public TXTFileReader(String filename){
            try {
                FileReader reader = new FileReader(filename);
                buffReader = new BufferedReader(reader);

                // ** TEST PRINTS ***
                //System.out.println(buffReader.toString());
                //System.out.println("\n'reader' type -> " + buffReader.getClass());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        /** Getter Method <p>
         * Gets the Text object as a BufferedReader
         * @return BufferedReader
         */
        public BufferedReader getTxt(){
            return buffReader;
        }
    }


