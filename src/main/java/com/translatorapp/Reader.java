package com.translatorapp;

import java.util.*;

public class Reader {

    public Map<Integer, String> textLines = new TreeMap<>();

    String filename;

    /** PreProcessor of files for type .csv, .txt
     *
     * Take in a filename and filetype (String of extension type)
     * and then selects the correct process to extract the relevant data
     * from that type of file.
     */
    public Reader(String filename){
        this.filename = filename;
    }

    public Reader() {

    }

    /** Helper method to process a TXT file <p>
     *
     * Uses the correct reader for TXT, iterates through reader object line-by-line. <p>
     *
     * If a flu tweet is found, continues processing by parsing out the tweet's text,
     * and coordinates, creates a new Tweet object to hold the data, and places that
     * Tweet object into an ArrayList
     *
     * @param filename - String of TXT filename
     */
    private void processTxt(String filename){

        this.filename = filename;
        TXTFileReader txtReader = new TXTFileReader(filename);
        String line;
        int counter = 0;

        try {
            while((line = txtReader.getTxt().readLine().toString()) != null){
                this.textLines.put(counter, line);
                counter++;
            }
        } catch (Exception e) { }

    }

    public void process(String filename) {
        String fileExtension = filename.substring(filename.length()-4, filename.length());
        System.out.println(fileExtension);
        try {
          if (fileExtension.equals(".txt")) {
                this.processTxt(filename);
            }
        } catch (Exception e) {

        }
    }

    public String getFilename() {
        return this.filename;
    }

}
