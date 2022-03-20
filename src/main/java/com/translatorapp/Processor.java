package com.translatorapp;

import java.util.Map;
import java.util.TreeMap;

public class Processor {

    private Reader reader;
    private Map<Integer, TranslationSegment> parsedLines = new TreeMap<>();

    public Processor(Reader reader) {
        this.reader = reader;
//        this.parseReader(reader);
    }

    public void parseReader(){

        Map<Integer, String> textLines = this.reader.textLines;

        String line;
        int counter = 0;

        for (int i=0; i < textLines.size(); i++) {
            line = textLines.get(i);

            // Regex will split lines at .!? characters.
            // Will not split at . after "Mr.", "Ms.", "Mrs.", or "Esq."

            String[] splitLine = line.split("(?<=(?<!(Mr?s?)|(Esq)|( [A-Z]))[/.!?]\\\"?)");

            for (String sentence : splitLine) {
                // Get each segment
                sentence = sentence.trim();

                if (sentence != "") {
                    TranslationSegment newSegment = new TranslationSegment();
                    newSegment.setSegmentID(counter);
                    newSegment.setSourceText(sentence);

                    // Add the line to the parseLines processor.
                    this.parsedLines.put(counter, newSegment);
                    counter++;
                }
            }
        }
    }

    public Map<Integer, TranslationSegment> getParsedLines() {
        return parsedLines;
    }
}
