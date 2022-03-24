package com.translatorapp;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PostProcessor {

    private Reader reader;
    private PreProcessor preProcessor;
    private Map<Integer, TranslationSegment> parsedLines = new TreeMap<>();
    private String filetype;
    private String sourceFilename;

    private PrintWriter out;
    private String outputFilename;


    public PostProcessor(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
        this.reader = preProcessor.getReader();
        this.parsedLines = preProcessor.getParsedLines();
        this.filetype = this.reader.getFiletype();
        this.sourceFilename = this.reader.getFilename();
    }


    public void exportTarget() {
        if (this.filetype == null) {
            System.out.println("Joe Momma");
            return;
        }

        if (this.filetype.equals("txt")) {
            this.exportTXT();
        }

    }

    private void exportTXT() {
        // Check for null/empty input, or if "write to" file is different from the current one.
        // and set the Logger object's filename to the filename passed in the parameter
        if (this.outputFilename == null || this.outputFilename == "" ||
                !this.outputFilename.equals(outputFilename)) {
            this.outputFilename = "target_" + this.sourceFilename;
//            System.out.println("Stu gots");
        }
//        this.outputFilename = "target_" + this.sourceFilename;

//        try {
//            // Create a file based on the current filename.
//            File targetFile = new File(this.outputFilename);
//
//            // If the output log file does not exist, create a new one,
//            // otherwise, append to the existing output log file.
//            if (!targetFile.exists()) {
//                targetFile.createNewFile();
//            }
//            FileWriter fWriter = new FileWriter(targetFile, false);
//            BufferedWriter bWriter = new BufferedWriter(fWriter);
//            out = new PrintWriter(bWriter);
//
//            // Write to the appropriate output file.
//            for (int i=0; i < this.parsedLines.size(); i++) {
//                String targetText = this.parsedLines.get(i).getTargetText();
//                out.println(targetText);
//            }
//
//            out.flush();
//            System.out.println(this.outputFilename);
//        }
        try {
            //Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(new File(this.sourceFilename));
            //instantiating the StringBuffer class
            StringBuffer buffer = new StringBuffer();
            //Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine()+System.lineSeparator());
            }
            String fileContents = buffer.toString();
            System.out.println("Contents of the file: "+fileContents);

            //closing the Scanner object
            sc.close();

            //Replacing the old line with new line
                        // Write to the appropriate output file.
            for (int i=0; i < this.parsedLines.size(); i++) {
                String sourceText = this.parsedLines.get(i).getSourceText();
                String targetText = this.parsedLines.get(i).getTargetText();
                fileContents = fileContents.replaceAll(sourceText, targetText);
            }


            //instantiating the FileWriter class
            FileWriter writer = new FileWriter(this.outputFilename);
            System.out.println("");
            System.out.println("new data: "+fileContents);
            writer.append(fileContents);
            writer.flush();
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }

}



