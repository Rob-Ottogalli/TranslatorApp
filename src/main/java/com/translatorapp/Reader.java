package com.translatorapp;

import java.util.*;

public class Reader {

    private Map<Integer, String> textLines = new TreeMap<>();

    private String filename;
    private String filetype;

    /** PreProcessor of files for type .csv, .txt
     *
     * Take in a filename and filetype (String of extension type)
     * and then selects the correct process to extract the relevant data
     * from that type of file.
     */
    public Reader(String filename){
        this.filename = filename;
        this.identifyFileType();
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
        this.identifyFileType();
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

    public void identifyFileType() {
        String[] filenameSplit = this.filename.split("\\.");
        String fileExtension = filenameSplit[filenameSplit.length-1];

        this.filetype = fileExtension;
        System.out.println("Reader file type: " + this.filetype);
    }

    public String getFilename() {
        return this.filename;
    }

    public String getFiletype() {
        return this.filetype;
    }

    public Map<Integer, String> getTextLines() {
        return textLines;
    }
}
